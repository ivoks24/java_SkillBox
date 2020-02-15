public class Loader {
    public static void main(String[] args) {

        for (char i = 'A'; i <= 'Z'; i++) {

            int a_z = 'a' - 'A';

            System.out.println(i + " - " + (int) i);
            System.out.println((char) (i + a_z) + " - " + (i + a_z));

        }
    }
}
