import java.util.Locale;

public class Loader {

    public static void main(String[] args) throws Exception {

        String fileName = "res/data-18M.xml";
        long usage;


        XMLHandler handler = new XMLHandler(fileName);
//        handler.printDuplicatedVoters();
        handler.checkMemory();



        XMLHandlerImproved handlerImproved = new XMLHandlerImproved(fileName);
//        handlerImproved.printDuplicatedVoters();
        handlerImproved.checkMemory();


        XMLDocument xmlDocument = new XMLDocument(fileName);
//        xmlDocument.printVoters();
        xmlDocument.checkMemory();
    }
}