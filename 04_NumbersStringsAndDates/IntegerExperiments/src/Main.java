public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 7843;

        System.out.println(sumDigits(1234567890));

    }

    public static Integer sumDigits(Integer number)
    {
        int sum = 0;
        String num = Integer.toString(number);
        for (int i = 0 ; i < num.length() ; i++)
            sum += Character.getNumericValue(num.charAt(i));

        return sum;
    }
}
