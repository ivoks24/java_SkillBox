import com.skillbox.airport.Airport;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Airport airport = Airport.getInstance();

        Date dateNow = new Date();
        Date extremeDated = new Date();

        extremeDated.setTime(extremeDated.getTime() + 2 * 60 * 60 * 1000);

        airport.getTerminals()
                .forEach(terminal -> terminal.getFlights().stream()
                        .filter(flight -> flight.getDate().after(dateNow) && flight.getDate().before(extremeDated))
                        .forEach(flight -> System.out.println(flight.getDate() + " - " + flight.getAircraft())))
        ;
    }
}
