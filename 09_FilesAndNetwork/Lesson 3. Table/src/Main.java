import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static HashMap<String, Integer> places = new HashMap<>();
    private static double comingRub = 0;
    private static double expenditureRub = 0;

    public static void main(String[] args) {

        try {
            List<String> lines = Files.readAllLines(Paths.get("data/movementList.csv"));
            lines.forEach(Main::pars);
        } catch (IOException e) {
            e.printStackTrace();
        }

        places.keySet().forEach(k -> System.out.println(k + " - " + places.get(k)));
    }

    private static void pars(String line) {

        String[] str = line.split("\\s+" + " ");
        if (str.length == 5)
            if (str[1].length() == 6) {
                setComingRub(str[3]);
            } else {
                setExpenditure(str[1], str[3], str[4]);
            }

    }

    private static void setExpenditure(String place, String rub, String currency) {

        place = place.substring(place.lastIndexOf("\\") + 1);

        if (!places.containsKey(place)) places.put(place, 1);
            else places.put(place, places.get(place) + 1);

        if (currency.contains("\"")) rub = conversion(currency);

        expenditureRub = expenditureRub + Double.parseDouble(rub);
    }

    private static String conversion(String currency) {
        return currency.substring(currency.indexOf("\"") + 1, currency.lastIndexOf("\"")).replace(",", ".");
    }

    private static void setComingRub(String money) {
        comingRub = comingRub + Double.parseDouble(money.substring(money.lastIndexOf(" ") + 1));
    }
}
