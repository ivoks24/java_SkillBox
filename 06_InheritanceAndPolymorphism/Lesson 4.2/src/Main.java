
public class Main {
    public static void main(String[] args) {

        Company company = new Company(100_000_000);

        company.hireAll(new TopManager(company), 10);
        company.hireAll(new Manager(company), 40);
        System.out.println("Бонус менеджерам - " + company.getBonusForManagers());

        company.hireAll(new Manager(company), 40);
        company.hireAll(new Operator(company), 180);
        System.out.println("Бонус менеджерам - " + company.getBonusForManagers());

        company.getTopSalaryStaff(10);
        company.getLowestSalaryStaff(30);

        company.fire(135);
        System.out.println("Бонус менеджерам - " + company.getBonusForManagers());

        company.getTopSalaryStaff(10);
        company.getLowestSalaryStaff(30);
//-----------------------------------------------------------------------
        Company company_1 = new Company(1_000_000);

        company_1.hire(new Operator(company_1));
        company_1.hire(new TopManager(company_1));

        Manager manager_1 = new Manager(company_1);
        company_1.hire(manager_1);

//        company_1.getTopSalaryStaff(3);
//        company_1.getLowestSalaryStaff(1);
 //-----------------------------------------------------------------------
//        Operator operator = new Operator();
//        Manager manager = new Manager();
//        TopManager topManager = new TopManager();

//        System.out.println(operator.getMonthSalary());
//        System.out.println(manager.getMonthSalary());
//        System.out.println(topManager.getMonthSalary());


    }
}
