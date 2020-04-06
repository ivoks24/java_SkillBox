//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class Test {
//
//    public static void main(String[] args) {
//
//        Bank bank = new Bank();
//        HashMap<String, Account> accounts= new HashMap<>();
//
//
//        for (int i = 0; i < 10; i++) {
//            Account account = new Account();
//            accounts.put(account.getAccNumber(), account);
//        }
//
//        bank.setAccounts(accounts);
//        List<String> accNumbers = new ArrayList<>(accounts.keySet());
//
//        bank.transfer(accNumbers.get(0), accNumbers.get(1), 60_000);
//        System.out.println(bank.getBalance(accNumbers.get(0)));
//        bank.transfer(accNumbers.get(0), accNumbers.get(3), 60_000);
//        System.out.println(bank.getBalance(accNumbers.get(0)));
//
//        bank.transfer(accNumbers.get(0), accNumbers.get(5), 60_000);
//        System.out.println(bank.getBalance(accNumbers.get(0)));
//        bank.transfer(accNumbers.get(0), accNumbers.get(7), 100_000);
//        System.out.println(bank.getBalance(accNumbers.get(0)));
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(bank.getBalance(accNumbers.get(0)));
//
//
//
//    }
//}
