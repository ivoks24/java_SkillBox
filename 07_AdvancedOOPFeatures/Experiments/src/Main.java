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
                        .filter(flight -> dateToCalendar(flight.getDate()).after(Calendar.getInstance()) && dateToCalendar(flight.getDate()).before(calendar))
                        .forEach(flight -> System.out.println(flight.getDate() + " - " + flight.getAircraft())))
        ;
    }

    private static Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
