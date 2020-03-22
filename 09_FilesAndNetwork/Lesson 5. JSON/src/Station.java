import java.util.ArrayList;
import java.util.List;

public class Station implements Comparable<Station> {

    private List<Line> line = new ArrayList<>();
    private String name;

    public Station(Line line, String name) {

        this.line.add(line);
        this.name = name;
    }

    public void addLine(Line line) {
        this.line.add(line);
    }

    public List<Line> getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Station station)
    {
        boolean lineComparison = station.getLine().contains(line);
        if(lineComparison) {
            return -1;
        }
        return name.compareToIgnoreCase(station.getName());
    }

    @Override
    public boolean equals(Object obj)
    {
        return compareTo((Station) obj) == 0;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
