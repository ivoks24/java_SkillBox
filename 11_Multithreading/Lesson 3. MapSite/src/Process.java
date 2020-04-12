import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Process {

    private ExecutorService executorService;
    private Set<String> treeSite = Collections.synchronizedNavigableSet(new TreeSet<>());

    public Process(String url) {

        int cores = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(cores);
        parseURL(url);

    }

    private synchronized void parseURL(String searchUrl) {

        executorService.submit(new Thread(() -> {
            try {
                Thread.sleep(100);

//                Jsoup.connect(searchUrl).proxy(getProxy()).get()
                Jsoup.parse(new File("data/LentaRU.html"), "UTF-8")
                        .select("a").forEach(el -> {

                    String path = el.absUrl("href");
                    if (path.contains(searchUrl) && !treeSite.contains(path)) {
                        treeSite.add(path);
                        parseURL(path);
                    }
                });

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }

    public synchronized List<String> getTreeSite() {

         List<String> finalSites = new ArrayList<>();
        for (String str : treeSite) {
            StringBuilder tab = new StringBuilder();
            tab.append("\t".repeat(str.split("/").length - 2));
            finalSites.add("\n" + str);
        }
        return finalSites;
    }

    private Proxy getProxy() {

        String proxyAddress = "185.36.157.30"; //127.0.0.1:51080
        int proxyPort = 8080;
        InetSocketAddress sa = new InetSocketAddress(proxyAddress, proxyPort);

        return new Proxy(Proxy.Type.HTTP, sa);
    }
}