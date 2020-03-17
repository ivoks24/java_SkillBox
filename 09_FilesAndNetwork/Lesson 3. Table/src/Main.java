import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static HashMap<String, Double> allExpenditure = new HashMap<>();
    private static HashMap<String, Double> allComing = new HashMap<>();
    private static double comingRub = 0;
    private static double expenditureRub = 0;

    public static void main(String[] args) {

        try {
            List<String> lines = Files.readAllLines(Paths.get("data/movementList.csv"));
            lines.remove(0);
            lines.forEach(Main::pars);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.printf("%nОбщая сумма приходов - %.2f%nСводный отчет о расходе:%n\t===============%n", comingRub);
        allComing.keySet().forEach(k -> System.out.printf("\t%-13s : %.2f%n", k, allComing.get(k)));

        System.out.printf("%nОбщая сумма расходов - %.2f%nСводный отчет о расходе:%n\t===============%n", expenditureRub);
        allExpenditure.keySet().forEach(k -> System.out.printf("\t%-13s : %.2f%n", k, allExpenditure.get(k)));
    }

    private static void pars(String line) {

        String[] str = line.split("\\s+" + " ");

        if (str[1].length() == 6) {
            setComingRub(str[2], str[3]);
        } else {
            setExpenditure(str[1], str[3], str[4]);
        }

    }

    private static void setComingRub(String place, String money) {
        String finalPlace = place.substring(place.lastIndexOf("/") + 1);
        double finalRub = Double.parseDouble(money.substring(money.lastIndexOf(" ") + 1));
        addPlace(allComing, finalPlace, finalRub);
        comingRub = comingRub + finalRub;
    }

    private static void setExpenditure(String place, String rub, String currency) {

        String finalPlace = place.substring(place.lastIndexOf("\\") + 1);
        if (currency.contains("\"")) {
            rub = conversion(currency);
        }
        double finalRub = Double.parseDouble(rub);
        addPlace(allExpenditure, finalPlace, finalRub);
        expenditureRub = expenditureRub + finalRub;
    }

    private static String conversion(String currency) {
        return currency.substring(currency.indexOf("\"") + 1, currency
                .lastIndexOf("\"")).replace(",", ".");
    }

    private static void addPlace(HashMap<String, Double> map, String place, Double money) {

        if (!map.containsKey(place)) {
            map.put(place, money);
        } else {
            map.put(place, map.get(place) + money);
        }
    }
}
