package Banking;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DepositoryCurrentAccount extends Account{

    private Calendar openDate;

@Override
    public void putMoney(double money) {
        setOpenDate();
        super.putMoney(money);
    }

@Override
    public void getMoney(double money) {

        try {
            Calendar now = new GregorianCalendar();
            if (openDate.before(now)) {
                super.getMoney(money);
            } else {
                System.out.println("Operation is not possible!");
            }
        } catch (NullPointerException e) {
            System.out.println("Operation is not possible!");
        }
    }

    private void setOpenDate() {

        openDate = new GregorianCalendar();
        openDate.add(Calendar.MONTH, 1);
    }

    public Date getOpenDate() {

        try {
            return openDate.getTime();
        } catch (NullPointerException e){
            return null;
        }
    }
}
