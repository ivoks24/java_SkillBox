import java.util.Locale;

public class Loader {
    public static void main(String[] args) {

        double[] temperatures = new double[30];
        double averageTemperature = 0.0;
        int notSick = 0;

        for (double temperature : temperatures) {

            temperature = Math.random() * 8 + 32;
            System.out.printf(Locale.ENGLISH, "%.1f ", temperature);
            averageTemperature += temperature;

            if (36.2 < temperature || temperature > 36.9) {
                notSick++;
            }
        }
        
        averageTemperature = averageTemperature/temperatures.length;
        System.out.printf(Locale.ENGLISH, "%nAverage temperature - %.1f", averageTemperature);
        System.out.printf("%nNot sick: %d", notSick);
    }
}
