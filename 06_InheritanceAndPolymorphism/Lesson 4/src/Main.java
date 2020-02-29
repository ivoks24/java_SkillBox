
public class Main {
    public static void main(String[] args) {

        Company company = new Company(2_000_000_000);

        company.hireAll(new TopManager(), 10);
        company.hireAll(new Manager(), 80);
        company.hireAll(new Operator(), 180);

        company.getTopSalaryStaff(3);
        company.getLowestSalaryStaff(5);

        company.fire(135);

        company.getTopSalaryStaff(3);
        company.getLowestSalaryStaff(5);

    }
}
