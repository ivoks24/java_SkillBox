public class Operator implements Employee {

    private int salary = 60_000 + (int) (Math.random() * 10_000);

    @Override
    public int getMonthSalary() {
        return salary;
    }
}
