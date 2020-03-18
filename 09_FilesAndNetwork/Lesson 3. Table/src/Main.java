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

        System.out.printf("%nОбщая сумма прихода - %.2f%nСводный отчет о приходе:%n\t===============%n", comingRub);
        allComing.keySet().forEach(k -> System.out.printf("\t%-13s : %.2f%n", k, allComing.get(k)));

        System.out.printf("%nОбщая сумма расхода - %.2f%nСводный отчет о расходе:%n\t===============%n", expenditureRub);
        allExpenditure.keySet().forEach(k -> System.out.printf("\t%-13s : %.2f%n", k, allExpenditure.get(k)));
    }

    private static void pars(String line) {

        double RUB = getMoney(line);

        if (line.contains("MCC6536")) {
           addOperation(allComing, getPlace(line), RUB);
           comingRub += RUB;
        } else {
            addOperation(allExpenditure, getPlace(line), RUB);
            expenditureRub += RUB;
        }

    }

    private static String getPlace(String line) {
        String[] str = line.split( " {3,}");
        return str[1];
    }

    private static Double getMoney(String getRUB) {

        if (getRUB.contains("\"")) {
            getRUB = getRUB.substring(getRUB.indexOf("\"") + 1,
                    getRUB.lastIndexOf("\""));
        } else if (getRUB.contains("MCC6536")) {
            getRUB = getRUB.substring(getRUB.lastIndexOf(" "), getRUB.lastIndexOf(","));
            getRUB = getRUB.substring(getRUB.lastIndexOf(",") + 1);
        } else {
            getRUB = getRUB.substring(getRUB.lastIndexOf(",") + 1);
        }
        return Double.parseDouble(getRUB.replace(",", "."));
    }

    private static void addOperation(HashMap<String, Double> map, String place, Double money) {

        if (!map.containsKey(place)) {
            map.put(place, money);
        } else {
            map.put(place, map.get(place) + money);
        }
    }
}
