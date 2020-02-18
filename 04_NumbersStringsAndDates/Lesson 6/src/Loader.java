import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Loader {
    public static void main(String[] args) {

        Calendar birthday = new GregorianCalendar(1995,Calendar.NOVEMBER,24);
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        DateFormat DAY_WEEK = new SimpleDateFormat("EEEE");

        Calendar now = new GregorianCalendar();
        int i = 0;

        for (;;) {
            System.out.printf("%2d - " + dateFormat.format(birthday.getTime()) + " - %tA%n", i++, birthday.getTime());
            //System.out.println(MessageFormat.format("{0} - {1} - {2}",
            //       i, dateFormat.format(birthday.getTime()), DAY_WEEK.format(birthday.getTime())));
            birthday.add(Calendar.YEAR, 1);
            if (birthday.after(now)) {
                break;
            }
        }
    }
}
