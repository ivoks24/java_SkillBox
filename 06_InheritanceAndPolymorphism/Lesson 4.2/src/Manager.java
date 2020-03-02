public class Manager implements Employee {

    private Company company;
    private int salary;

    public Manager(Company company) {

        this.company = company;
        salary = 120_000 + (int) (Math.random() * 3000) * 10;
    }

    @Override
    public int getMonthSalary() {

        return (company != null) ? salary + company.getBonusForManagers() : salary;
    }

//    public void setCompany(Company company) {
//        this.company = company;
//    }
}
