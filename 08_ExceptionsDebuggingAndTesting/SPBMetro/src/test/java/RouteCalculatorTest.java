import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.*;

public class RouteCalculatorTest extends TestCase {

    List<Station> route;
    StationIndex stationIndex;
    RouteCalculator routeCalculator;
    Line line1, line2, line3;

    @Override
    protected void setUp() throws Exception {

        route = new ArrayList<>();
        stationIndex = new StationIndex();

        line1 = new Line(1, "Первая");
        line2 = new Line(2, "Вторая");
        line3 = new Line(3, "Третья");

//        stationIndex.addLine(line1);
//        stationIndex.addLine(line2);
//        stationIndex.addLine(line3);

        route.add(new Station("1.1", line1)); // station 0
        route.add(new Station("1.2", line1)); // station 1
        route.add(new Station("2.1", line2)); // station 2
        route.add(new Station("2.2", line2)); // station 3
        route.add(new Station("2.3", line2)); // station 4
        route.add(new Station("3.1", line3)); // station 5
        route.add(new Station("3.2", line3)); // station 6

//        route.forEach(station -> stationIndex.addStation(station));

        line1.addStation(route.get(0));
        line1.addStation(route.get(1));
        line2.addStation(route.get(2));
        line2.addStation(route.get(3));
        line2.addStation(route.get(4));
        line3.addStation(route.get(5));
        line3.addStation(route.get(6));

        List<Station> connectionStations1 = new ArrayList<>();
        connectionStations1.add(route.get(1));
        connectionStations1.add(route.get(2));

        List<Station> connectionStations2 = new ArrayList<>();
        connectionStations2.add(route.get(4));
        connectionStations2.add(route.get(5));

        stationIndex.addConnection(connectionStations1);
        stationIndex.addConnection(connectionStations2);

        routeCalculator = new RouteCalculator(stationIndex);


    }

    public void testGetShortestRoute() {

        List<Station> actual;

        Station st_0 = route.get(0); // station 0; line 1
        Station st_1 = route.get(1); // station 1; line 1
        Station st_4 = route.get(4); // station 4; line 2
        Station st_6 = route.get(6); // station 6; line 3

        actual = routeCalculator.getShortestRoute(st_0, st_1);
        List<Station> expected = new ArrayList<>(line1.getStations());
        assertEquals(expected, actual);

        actual = routeCalculator.getShortestRoute(st_0, st_4);
        expected.addAll(line2.getStations());
        assertEquals(expected, actual);

        actual = routeCalculator.getShortestRoute(st_0, st_6);
        assertEquals(route, actual);
    }

    public void testCalculateDuration() {

        double actual = RouteCalculator.calculateDuration(route);
        double expected = 17;
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
