import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final Metro metro = new Metro();
    public static Line line;
    public static Station station;

    public static void main(String[] args) {

        Document htmlFile;
        Elements tables;

        try {
            htmlFile = Jsoup.parse(new File("data/metro.html"), "UTF-8");
            tables = htmlFile.select("table");

            tables.select("tr").stream().filter(tr ->
                    (tr.select("td").size() == 8) || (tr.select("td").size() == 7)).forEach(Main::parse);
//                rows.stream().filter(r -> !r.select("td").get(3).text().equals("")).forEach(Main::addMetroConnection); //attr("data-sort-value").equals("Infinity")
//            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        String s = metro.obtainJSONObject();
        System.out.println(s);



    }

    private static void parse(Element row) {

        Elements columns = row.select("td");

        String numLine = columns.get(0).attr("data-sort-value");
        String nameLine = columns.get(0).select("span").attr("title");
        String nameStation = columns.get(1).text();

        addMetroLine(numLine, nameLine);
        addMetroStation(nameStation, numLine);
        addMetroConnection(columns, nameStation);
    }

    private static void addMetroConnection(Elements columns, String nameStation) {

        Elements connect = columns.select("td").get(3).select("span");
        List<Station> connects = new ArrayList<>();
        connects.add(metro.getStation(nameStation));

        for (int i = 1; i <= connect.size(); i += 2) {

            String str = connect.get(i).attr("title");

            metro.findStation().forEach(name -> {
                if (str.contains(name)) {
                    Station station = metro.getStation(name);
                    if (station.getName().equals(nameStation)) {

                    } else {
                        connects.add(station);
                    }
                }
            });
        }

        if (connects.size() > 1) {
            metro.addConnection(connects);
        }
    }

    private static void addMetroLine(String num, String name) {
        Line line = new Line(num, name);
        metro.addLine(line);
    }

    private static void addMetroStation(String name, String numLine) {

        Line line = metro.getLine(numLine);
        Station station = new Station(line, name);

        if (metro.getStation(name) == null) {
            metro.addStations(station);
        } else {
            station = metro.getStation(name);
            station.addLine(line);
        }
        line.addStations(station);

//        System.out.println(name);
    }
}
