import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.*;

public class Process {

    private String searchUrl; //https://lenta.ru
    private Set<String> treeSite = new TreeSet<>();
    private List<String> finalSites = new ArrayList<>();

    public Process(String url) {

        searchUrl = url;
        parseURL(url);

        finalSites.forEach(this::action);
        finalSites = new ArrayList<>();
        StringBuilder tab;
        for (String str : treeSite) {
            tab = new StringBuilder();
            tab.append("\t".repeat(str.split("/").length - 3));
            finalSites.add("\n" + tab + str);
        }
    }

    public List<String> getFinalSites() {
        return finalSites;
    }

    private void parseURL(String searchUrl) {

        treeSite.add(searchUrl + "/");

        Document htmlFile;
        try {

            String proxyAddress = "185.236.67.91";
            int proxyPort = 8080;
            InetSocketAddress sa = new InetSocketAddress(proxyAddress, proxyPort);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, sa);

//            URL url = new URL(searchUrl);
//            URLConnection conn = url.openConnection(proxy);

            htmlFile = Jsoup.parse(new File("data/LentaRU.html"), "UTF-8");

//            htmlFile = Jsoup.connect(searchUrl).proxy(proxy).get(); // https://lenta.ru
            htmlFile.select("a").forEach(el -> finalSites.add(el.attr("href")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finalSites.removeIf(site -> site.indexOf("/") != 0 || site.length() < 2);
    }

    private void action(String site) {

        String[] path = site.split("/");
        StringBuilder stringBuilder = new StringBuilder();

        for (String dir : path) {
            stringBuilder.append(dir).append("/");
            treeSite.add(searchUrl + stringBuilder);
        }
    }
}