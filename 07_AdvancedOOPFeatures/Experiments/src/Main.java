import com.skillbox.airport.Airport;

import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Airport airport = Airport.getInstance();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 2);

        airport.getTerminals()
                .forEach(terminal -> terminal.getFlights().stream()
                        .filter(flight -> flight.getDate().after(new Date()) && flight.getDate().before(calendar.getTime()))
                        .forEach(flight -> System.out.println(flight.getDate() + " - " + flight.getAircraft())))
        ;
    }
}
