import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main
{
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        ArrayList<Employee> staff = loadStaffFromFile();

        //Lesson 1 ---------------
//        Comparator<Employee> comparator = Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName);
//        staff.sort(comparator);
//        staff.forEach(System.out::println);
        // -----------------------


        //Lesson 2 ---------------


        System.out.println(staff.stream().filter(date -> dateToCalendar(date.getWorkStart()).get(Calendar.YEAR) == 2017)
                .max(Comparator.comparing(Employee::getSalary)).get().getSalary());

        // -----------------------
    }

    private static Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}