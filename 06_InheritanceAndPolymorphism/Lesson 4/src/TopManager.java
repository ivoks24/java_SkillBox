public class TopManager implements Employee {

    private Company company;
    private int salary;

    public TopManager() {

        salary = 150_000 + (int) (Math.random() * 5000) * 10;

        if (company.getIncome() > 10_000_000) {
            salary = (int) (salary * 2.5);
        }
    }

    @Override
    public int getMonthSalary() {
        return salary;
        //System.out.println("The salary of a top Manager: " + salary);
    }
}
