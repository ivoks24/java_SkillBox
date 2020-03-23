import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    private static HashMap<String, Line> lines = new HashMap<>();

    public static void main(String[] args) {

        parseHTML("data/metro.html");

        JSONObject object = new JSONObject();
        HashMap<String, List<String>> list = new HashMap<>();

        for (String line : lines.keySet()) {
            list.put(line, lines.get(line).getStations());
        }

        object.put("Lines", list);


        PrintWriter writer;
        try {
            writer = new PrintWriter("data/jsonFile.json");
            writer.write(object.toJSONString());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        lines.keySet().forEach(line -> {
//            System.out.println(line + ": " + lines.get(line).getStations());
//        });

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String json = objectMapper.writeValueAsString(list);
            writer = new PrintWriter("data/jsonFile'objectMapper'.json");
            writer.write(object.toJSONString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void parseHTML(String path) {

        Document doc;
        Elements tables = null;
        try {
            doc = Jsoup.parse(new File(path), "UTF-8");
            tables = doc.select("table");
        } catch (IOException e) {
            e.printStackTrace();
        }

        tables.select("tr").stream().filter(tr -> (tr.select("td").size() == 8) || (tr.select("td").size() == 7))
                .forEach(tr -> {
                        Elements columns = tr.select("td");

                        String numLine = columns.get(0).select("span").get(0).text().replaceFirst("^0", "");
                        String nameLine = columns.get(0).select("span").attr("title");
                        String nameStation = columns.get(1).text();

                        addLine(numLine, nameLine);
                        addStation(numLine, nameStation);
                });
    }

    public static void addStation(String numLine, String name) {

        lines.get(numLine).addStations(name);
    }

    public static void addLine(String number, String name) {

        if (!lines.keySet().contains(number)) {
            lines.put(number, new Line(number, name));
        }
    }
}
