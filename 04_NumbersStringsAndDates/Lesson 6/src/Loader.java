import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Loader {
    public static void main(String[] args) {

        Calendar birthday = new GregorianCalendar(1995,Calendar.NOVEMBER,24);
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd - EEEE");

        Calendar now = new GregorianCalendar();
        int i = 0;

        for (;;) {

            System.out.printf("%n%2d - " + dateFormat.format(birthday.getTime()), i++);
            birthday.add(Calendar.YEAR, 1);
            if (birthday.after(now)) {
                break;
            }
        }
    }
}
