public class Operator implements Employee {

    private Company company;
    private int salary;

    public Operator() {

        salary = 110_000 + (int) (Math.random() * 2000) * 10;
    }

    @Override
    public int getMonthSalary() {
        return salary;
        //System.out.println("The salary of the operator: " + salary);
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
