package Banking;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DepositoryCurrentAccount extends Account{

    private Calendar openDate;

    public void putMoney(double money) {
        setOpenDate();
        super.putMoney(money);
    }

    public void getMoney(int money) {

        Calendar now = new GregorianCalendar();
        openDate.add(Calendar.MONTH, 1);

        if (openDate.before(now)) {
            super.getMoney(money);
        } else {
            System.out.println("Operation is not possible!");
        }
    }

    private void setOpenDate() {
        openDate = new GregorianCalendar();
    }

    public Date getOpenDate() {
        return openDate.getTime();
    }
}
