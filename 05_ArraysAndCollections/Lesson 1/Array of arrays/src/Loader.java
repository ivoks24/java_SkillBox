public class Loader {
    public static void main(String[] args) {

        String [][] x = new String[7][7];

        for (int i = 0; i < Math.ceil((double) x.length / 2); i++) {
            for (int j = 0; j < Math.ceil((double) x[i].length / 2); j++) {

                //-----------------как бы выглядел неоптимизированный вариант? ------------------------

                int y = (x[i].length - 1) - j;
                int z = (x.length - 1) - i;

                if (i == j) {

                    x[i] [j] = x[i] [y] = "x";
                    x[z] [j] = x[z] [y] = "x";
                    continue;
                }

                x[i] [j] = x[i] [y] = " ";
                x[z] [j] = x[z] [y] = " ";
            }
        }

        for (String[] strings : x) {
            for (String line : strings) {
                System.out.print(line + " ");
            }
            System.out.println();
        }
    }
}
