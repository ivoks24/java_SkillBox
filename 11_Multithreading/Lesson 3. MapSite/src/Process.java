import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Process {

    private final ExecutorService executorService;
    private final Set<String> treeSite = Collections.synchronizedNavigableSet(new TreeSet<>());
    private final List<Future<?>> tasks = new ArrayList<>();

    public Process(String url) {

        int cores = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(cores);
        parseURL(url);

        for (Future<?> f : tasks) {
            try {
                f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }

    private synchronized void parseURL(String searchUrl) {

        Future<?> submit = executorService.submit(new Thread(() -> {
            try {
                Thread.sleep(100);

                Jsoup.connect(searchUrl).get() //.proxy(getProxy()).get()
//                Jsoup.parse(new File("data/LentaRU.html"), "UTF-8")
                        .select("a").forEach(el -> {

                    String path = el.absUrl("href");
                    if (path.contains(searchUrl) && !treeSite.contains(path)) {
                        treeSite.add(path);
                        parseURL(path);
                    }
                });
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }));
        tasks.add(submit);
    }

    public synchronized List<String> getTreeSite() {

        List<String> finalSites = new ArrayList<>();
        for (String str : treeSite) {
            finalSites.add("\n" + "\t".repeat(str.split("/").length - 3) + str);
        }
        return finalSites;
    }

    private Proxy getProxy() {

        String proxyAddress = "127.0.0.1"; //127.0.0.1:51080
        int proxyPort = 3213;
        InetSocketAddress sa = new InetSocketAddress(proxyAddress, proxyPort);

        return new Proxy(Proxy.Type.HTTP, sa);
    }
}