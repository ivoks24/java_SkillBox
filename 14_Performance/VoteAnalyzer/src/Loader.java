import java.util.Locale;

public class Loader {

    public static void main(String[] args) throws Exception {

        String fileName = "res/data-18M.xml";
        long usage;

        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        XMLHandler handler = new XMLHandler(fileName);
//        handler.printDuplicatedVoters();
        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;
        System.out.printf(Locale.GERMAN, "\nSAX-парсер - %,d", usage);

        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        XMLHandlerImproved handlerImproved = new XMLHandlerImproved(fileName);
//        handlerImproved.printDuplicatedVoters();
        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;
        System.out.printf(Locale.GERMAN, "\nSAX-парсер improved - %,d", usage);

        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        XMLDocument xmlDocument = new XMLDocument(fileName);
//        xmlDocument.printVoters();
        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;
        System.out.printf(Locale.GERMAN, "\nDOM-парсер - %,d\n", usage);
    }
}