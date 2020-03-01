public class TopManager implements Employee {

    private Company company;
    private int salary;

    public TopManager() {

        salary = 150_000 + (int) (Math.random() * 3500) * 10;
    }

    @Override
    public int getMonthSalary() {
        return salary;
    }

    @Override
    public void setCompany(Company company) {

        this.company = company;

        if (company.getIncome() > 10_000_000)
            salary = (int) (salary * 2.5);
    }
}
