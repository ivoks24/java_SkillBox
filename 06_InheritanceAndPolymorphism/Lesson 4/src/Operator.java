public class Operator implements Employee {

    int salary = 110_000 + (int) (Math.random() * 2000) * 10;

    @Override
    public int getMonthSalary() {
        return salary;
        //System.out.println("The salary of the operator: " + salary);
    }
}
