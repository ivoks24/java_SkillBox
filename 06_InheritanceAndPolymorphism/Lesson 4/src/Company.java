import java.util.ArrayList;
import java.util.List;

public class Company {

    private List<Employee> staff = new ArrayList<>();

    public Company() {

    }

    public void hire(Employee employee) {
        staff.add(employee);
        System.out.println("\nSalary: " + employee.getMonthSalary());
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

        int[] salary = new int[count];
        ArrayList<Employee> topSalaryStaff = new ArrayList<>();
        int min;

        for (Employee employee : staff) {
            System.out.println(employee.getMonthSalary());
            for (int i = 0; i < count; i++) {

                min = salary[0];
                if (min > salary[i])
                    min = salary[i];

                if (employee.getMonthSalary() > min) {

                    salary[i] = employee.getMonthSalary();
                    System.out.println(salary[i]);
                    break;
                }
            }
        }

        for (Employee employee : staff) {
            if (salary.equals(employee.getMonthSalary()) && count != 0) {

                topSalaryStaff.add(employee);
                count--;
            }
        }
        return topSalaryStaff;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {

        int[] salary = new int[count];
        ArrayList<Employee> lowestSalaryStaff = new ArrayList<>();

        for (Employee employee : staff) {
            for (int i = 0; i < count; i++) {
                if (salary[i] == 0 || employee.getMonthSalary() < salary[i]) {

                    salary[i] = employee.getMonthSalary();
                    break;
                }
            }
        }

        for (Employee employee : staff) {
            if (salary.equals(employee.getMonthSalary()) && count != 0) {
                lowestSalaryStaff.add(employee);
                count--;
            }
        }
        return lowestSalaryStaff;
    }

    private ArrayList<Employee> topStaff(boolean b) {

        return null;

    }
}
