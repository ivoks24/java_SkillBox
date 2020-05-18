import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Company {

    private final List<Employee> staff = new ArrayList<>();
    private final long incomeCompany;
    private int countManager = 0;
    private int bonusForManagers;

    public Company(long income) {

        incomeCompany = income;
    }

    private long getProfitCompany() {

        long expenses = 0;
        for (Employee employee : staff) {

            expenses += employee.getMonthSalary();
        }
        if ((incomeCompany - expenses) / 20 > 0 && countManager > 0) {
            bonusForManagers = (int) ((incomeCompany - expenses) / (20 * countManager));
        } else {
            bonusForManagers = 0;
        }
        return incomeCompany - expenses - bonusForManagers;
    }

    public int getBonusForManagers() {
        return bonusForManagers;
    }

    public long getIncome() {
        return incomeCompany;
    }

    public void fire(int number) {

        if (staff.size() >= number)
            while (number > 0) {

                if (staff.get(0) instanceof Manager)
                    countManager--;

                staff.remove(0);
                number--;
            }
        getProfitCompany();
    }

    public void hire(Employee employee) {

        employee.setCompany(this);
        staff.add(employee);

        if (employee instanceof Manager) {
            countManager++;
            getProfitCompany();
        }
    }

    public void hireAll(Employee employee, int count) {

            for (int i = 0; i < count; i++) {

                if (employee instanceof Operator) {
                    employee = new Operator();
                } else if (employee instanceof TopManager) {
                    employee = new TopManager();
                } else if (employee instanceof Manager) {
                    employee = new Manager();
                    countManager++;
                    getProfitCompany();
                }

                employee.setCompany(this);
                staff.add(employee);
            }
    }

    public ArrayList<Employee> getTopSalaryStaff(int count) {

        if (count > staff.size())
            return null;

        staff.sort((o1, o2) -> String.valueOf(o2.getMonthSalary()).compareTo(String.valueOf(o1.getMonthSalary())));
        ArrayList<Employee> topSalaryStaff = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            topSalaryStaff.add(staff.get(i));
        }

        printTopStaff(topSalaryStaff, true);
        return topSalaryStaff;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {

        if (count > staff.size())
            return null;

        staff.sort(Comparator.comparing(o -> String.valueOf(o.getMonthSalary())));
        ArrayList<Employee> lowestSalaryStaff = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lowestSalaryStaff.add(staff.get(i));
        }

        printTopStaff(lowestSalaryStaff, false);
        return lowestSalaryStaff;
    }

    private void printTopStaff(ArrayList<Employee> employees, boolean b) {

        int number = 1;
        String string = (b)?
                MessageFormat.format("\n{0, choice, 1#Самая высокая зарпалта:|2#Список самых высоких {0} зарплат по убыванию:}\n", employees.size())
                :
                MessageFormat.format("\n{0, choice, 1#Самая низкая зарпалта:|2#Список самых низких {0} зарплат по возрастанию:}\n", employees.size());

        System.out.print(string);

        for (Employee e : employees) {
            System.out.format("%3d. %,6d руб.%n", number++, e.getMonthSalary());
        }
    }
}
