import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {

    private List<Employee> staff = new ArrayList<>();
    private long incomeCompany;

    public Company(long income) {
        incomeCompany = income;
    }

    public long getProfitCompany() {

        long expenses = 0;
        for (Employee employee : staff) {

            expenses += employee.getMonthSalary();
        }
        return incomeCompany - expenses;
    }

    public long getIncome() {
        return incomeCompany;
    }

    public void fire(int number) {
        if (staff.size() >= number)
            while (number > 0) {
                staff.remove(0);
                number--;
            }
    }

    public void hire(Employee employee) {
        staff.add(employee);
    }

    public void hireAll(Employee employee, int count) {

        if (employee instanceof Operator) {

            for (int i = 0; i < count; i++)
                staff.add(new Operator());

        } else if (employee instanceof Manager) {

            for (int i = 0; i < count; i++)
                staff.add(new Manager());

        } else if (employee instanceof TopManager) {

            for (int i = 0; i < count; i++)
                staff.add(new TopManager());
        }
    }

    public ArrayList<Employee> getTopSalaryStaff(int count) {

        if (count > staff.size())
            System.exit(0);

        ArrayList<Integer> salary = new ArrayList<>();
        ArrayList<Employee> topSalaryStaff = new ArrayList<>();

        for (Employee employee : staff)
            salary.add(employee.getMonthSalary());

        salary.sort(Collections.reverseOrder());
        int index = 0;

        do {
            for (Employee employee : staff) {
                if (salary.get(index).equals(employee.getMonthSalary()) && index < count) {

                    topSalaryStaff.add(employee);
                    index++;
                }
            }
        } while (index < count);

        PrintTopStaff(topSalaryStaff, true);
        return topSalaryStaff;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {

        if (count > staff.size())
            System.exit(0);

        ArrayList<Integer> salary = new ArrayList<>();
        ArrayList<Employee> lowestSalaryStaff = new ArrayList<>();

        for (Employee employee : staff)
            salary.add(employee.getMonthSalary());

        Collections.sort(salary);
        int index = 0;

        do {
            for (Employee employee : staff) {
                if (salary.get(index).equals(employee.getMonthSalary()) && index < count) {

                    lowestSalaryStaff.add(employee);
                    index++;
                }
            }
        } while (index < count);

        PrintTopStaff(lowestSalaryStaff, false);
        return lowestSalaryStaff;
    }

    private void PrintTopStaff(ArrayList<Employee> employees, boolean b) {

        int number = 1;
        String string = (b)?
                MessageFormat.format("\n{0, choice, 1#Самая высокая зарпалта:|2#Список {0} зарплат по убыванию:}\n", employees.size())
                :
                MessageFormat.format("\n{0, choice, 1#Самая низкая зарпалта:|2#Список {0} зарплат по возрастанию:\n}", employees.size());

        System.out.println(string);

        for (Employee e : employees) {
            System.out.format("%3d. %,6d руб.%n", number++, e.getMonthSalary());
        }

    }
}
