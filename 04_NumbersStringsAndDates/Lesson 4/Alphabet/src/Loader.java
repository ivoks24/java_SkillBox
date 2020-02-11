public class Loader {
    public static void main(String[] args) {

        String alphabet = "Aa Bb Cc Dd Ee Ff Gg Hh Ii Jj Kk Ll Mm Nn Oo Pp Qq Rr Ss Tt Uu Vv Xx Yy Zz";

        for (int i = 0; i < alphabet.length(); i++) {

            if ((int) alphabet.charAt(i) == 32) continue;
            System.out.println(alphabet.charAt(i) + " - " + (int) alphabet.charAt(i));

        }
    }
}
