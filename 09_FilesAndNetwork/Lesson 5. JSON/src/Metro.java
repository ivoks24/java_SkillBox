import org.json.simple.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class Metro {

    HashMap<String, Line> numberLine;
    TreeSet<Station> stations;
    TreeMap<Station, TreeSet<Station>> connections;

    public Metro() {
        numberLine = new HashMap<>();
        stations = new TreeSet<>();
        connections = new TreeMap<>();
    }

    public String obtainJSONObject() {

        JSONObject obj = new JSONObject();

        HashMap<String, List<Station>> lines = new HashMap();

        numberLine.keySet().forEach(key -> lines.put(key, numberLine.get(key).getStations()));

        obj.put("Stations", lines);
//        obj.put("Connections", connections);

        return obj.toJSONString();
    }

    public TreeSet<Station> stationsS() {
        return stations;
    }

    public List<String> findStation() {
        List<String> names = new ArrayList<>();
        stations.forEach(s -> names.add(s.getName()));
        return names;
    }

    public void addLine(Line line) {
        numberLine.put(line.getNumber(), line);
    }

    public void addStations(Station station) {
        stations.add(station);
    }

    public void addConnection(List<Station> stations)
    {
        for(Station station : stations)
        {
            if(!connections.containsKey(station)) {
                connections.put(station, new TreeSet<>());
            }
            TreeSet<Station> connectedStations = connections.get(station);
            connectedStations.addAll(stations.stream()
                    .filter(s -> !s.equals(station)).collect(Collectors.toList()));
        }
    }

    public Line getLine(String number) { return numberLine.get(number); }

    public Station getStation(String name) {

        for(Station station : stations)
        {
            if(station.getName().equalsIgnoreCase(name)) {
                return station;
            }
        }
        return null;
    }

    public Station getStation(String name, String lineNumber) {

        Station query = new Station(getLine(lineNumber), name);
        Station station = stations.ceiling(query);
        return station.equals(query) ? station : null;
    }

    public Set<Station> getConnectedStations(Station station) {

        if(connections.containsKey(station)) {
            return connections.get(station);
        }
        return new TreeSet<>();
    }
}
