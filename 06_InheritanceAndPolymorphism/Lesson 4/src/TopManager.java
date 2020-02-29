public class TopManager implements Employee {

    private int salary = 100_000 + (int) (Math.random() * 10_000);

    @Override
    public int getMonthSalary() {
        return salary;
    }
}
