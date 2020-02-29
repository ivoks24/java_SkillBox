public class Manager implements Employee {

    private int salary = 80_000 + (int) (Math.random() * 10_000);

    @Override
    public int getMonthSalary() {
        return salary;
    }
}
