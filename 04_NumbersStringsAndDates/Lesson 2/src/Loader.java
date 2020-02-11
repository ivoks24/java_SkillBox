public class Loader {
    public static void main(String[] args) {

        byte byteMin = Byte.MIN_VALUE;
        byte byteMax = Byte.MAX_VALUE;

        int intMin = Integer.MIN_VALUE;
        int intMax = Integer.MAX_VALUE;

        short shortMin = Short.MIN_VALUE;
        short shortMax = Short.MAX_VALUE;

        long longMin = Long.MIN_VALUE;
        long longMax = Long.MAX_VALUE;

        float floatMin = Float.MIN_VALUE;
        float floatMax = Float.MAX_VALUE;

        double doubleMin = Double.MIN_VALUE;
        double doubleMax = Double.MAX_VALUE;

        System.out.println("Byte: " + byteMin + "; " + byteMax);
        System.out.println("Integer: " + intMin + "; " + intMax);
        System.out.println("Short: " + shortMin + "; " + shortMax);
        System.out.println("Long: " + longMin + "; " + longMax);
        System.out.println("Float: " + floatMin + "; " + floatMax);
        System.out.println("Double: " + doubleMin + "; " + doubleMax);
    }
}
