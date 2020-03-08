import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.*;

public class RouteCalculatorTest extends TestCase {

    List<Station> route;
    StationIndex stationIndex;
    RouteCalculator routeCalculator;
    Line line1, line2;

    @Override
    protected void setUp() throws Exception {

        route = new ArrayList<>();
        stationIndex = new StationIndex();

        line1 = new Line(1, "Первая");
        line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");

        route.add(new Station("1.1", line1)); // st_0
        route.add(new Station("1.2", line1)); // st_1
        route.add(new Station("2.1", line2)); // st_2
        route.add(new Station("2.2", line2)); // st_3
        route.add(new Station("2.3", line2)); // st_4
        route.add(new Station("3.1", line3)); // st_5
        route.add(new Station("3.2", line3)); // st_6

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

    }

    public void testCalculateDuration() {

        double actual = RouteCalculator.calculateDuration(route);
        double expected = 17;
        assertEquals(expected, actual);
    }

    public void testGetRouteOnTheLine() {

        Station from = route.get(0); //              "1.1", line_1
        Station nexStation = route.get(1); //        "1.2", line_1
        Station to = route.get(3); //                "2.2", line_2

        List<Station> actual = routeCalculator.getRouteOnTheLine(from, to); //use two lines
        assertNull(actual);

        actual = routeCalculator.getRouteOnTheLine(from, nexStation); //use one line
        List<Station> expected = line1.getStations();
        assertEquals(expected, actual);
    }

    public void testGetRouteWithOneConnection() {

        Station st_2 = route.get(2); //line_2
        Station st_3 = route.get(3); //line_2
        Station st_4 = route.get(4); //line_2
        Station st_5 = route.get(5); //line_3

        List<Station> actual = routeCalculator.getRouteWithOneConnection(st_2, st_4); // one line
        assertNull(actual);

        List<Station> routeExpected = new ArrayList<>();
        route.add(st_2);
        route.add(st_3);
        route.add(st_4);
        route.add(st_5);

        actual = routeCalculator.getRouteWithOneConnection(st_2, st_5); // two line
        assertEquals(routeExpected, actual);
    }

    public void testIsConnected() {

        Station st_1 = route.get(1);
        Station st_2 = route.get(2);

        boolean actual = routeCalculator.isConnected(st_1, st_2);
        assertTrue(actual);
    }

    public void testGetRouteViaConnectedLine() {

        Station st_0 = route.get(0); //line_1
        Station st_3 = route.get(3); //line_2
        Station st_6 = route.get(6); //line_3

        List<Station> actual = routeCalculator.getRouteViaConnectedLine(st_0, st_3);
        assertNull(actual);

        actual = routeCalculator.getRouteViaConnectedLine(st_0, st_6);
        List<Station> expected = line2.getStations();
        assertEquals(expected, actual);
    }

    public void testGetRouteWithTwoConnections() {

        Station st_0 = route.get(0); //line_1
        Station st_1 = route.get(1); //line_1
        Station st_6 = route.get(6); //line_3

        List<Station> actual = routeCalculator.getRouteWithTwoConnections(st_0, st_1); // one line
        assertNull(actual);

        actual = routeCalculator.getRouteWithTwoConnections(st_0, st_6); // three lines
        assertEquals(route, actual);

    }

    @Override
    protected void tearDown() throws Exception {

    }
}
