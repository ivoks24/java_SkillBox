
public class Loader {

    public static void main(String[] args) {

        String[] colors = {"Red", "Orange", "Yellow", "Green", "Blue", "Violet"};
        String color;

        for (int i = 0; i < colors.length / 2; i++) {

            color = colors[i];
            colors[i] = colors[colors.length - i - 1];
            colors[colors.length - i - 1] = color;
        }

        for (String printColor : colors) {

            System.out.println(printColor);
        }
    }
}
