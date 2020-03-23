import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) {

        Document htmlFile = null;
        Elements img = null;
        try {
            htmlFile = Jsoup.parse(new File("data/lentaRU.html"), "UTF-8");
            img = htmlFile.select("img");
            img.forEach(i -> downloadImage(i.attr("abs:src")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadImage(String strImageURL){

        //get file name from image path
        String strImageName = strImageURL.substring(strImageURL.lastIndexOf("/") + 1);

        System.out.printf("Saving: %.15s, from: %s", strImageName, strImageURL);

        try {

            URL urlImage = new URL(strImageURL);

            String proxyAddress = "185.236.67.91";
            int proxyPort = 8080;
            InetSocketAddress sa = new InetSocketAddress(proxyAddress, proxyPort);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, sa);
            URLConnection conn = urlImage.openConnection(proxy);

            //open the stream from URL
            InputStream in = conn.getInputStream();

            byte[] buffer = new byte[4096];
            int n = -1;

            OutputStream os = new FileOutputStream( "images/" + strImageName );

            //write bytes to the output stream
            while ( (n = in.read(buffer)) != -1 ){
                os.write(buffer, 0, n);
            }

            //close the stream
            os.close();
            System.out.println("Image saved");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
