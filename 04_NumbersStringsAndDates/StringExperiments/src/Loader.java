import static java.lang.Integer.parseInt;

public class Loader
{
    public static void main(String[] args)
    {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        text = text.replaceAll("[^0-9\\s]+", "").trim();
        text = text.replaceAll("\\s+", " ");

        String[] array = text.split(" ");
        int sum = 0;

        for (String s : array) {

            sum += parseInt(s);
        }

        System.out.println(sum);
    }
}