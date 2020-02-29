public class Manager implements Employee {

    private Company company;
    private int salary;

    public Manager() {

        salary = 120_000 + (int) ((Math.random() * 3000) * 10 + (company.getProfitCompany() * 0.05));
    }

    @Override
    public int getMonthSalary() {
        return salary;
        //System.out.println("Manager's salary: " + salary);
    }
}
