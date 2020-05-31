import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAXParser extends DefaultHandler {

    public SAXParser(String fileName) throws Exception {

        long start = System.currentTimeMillis();

        parseFile(fileName);

        System.out.println("Parsing duration: " + (System.currentTimeMillis() - start) + " ms");
        DBConnection.printVoterCounts();

    }

    private void parseFile(String fileName) throws Exception {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser parser = factory.newSAXParser();
        parser.parse(new File(fileName), this);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        String name = attributes.getValue("name");
        String birthDay = attributes.getValue("birthDay");

        try {
            if (qName.equals("voter")) {

                DBConnection.countVoter(name, birthDay);
            }
            DBConnection.executeMultiInsert();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
//
//        if (qName.equals("voter")) {
//            voter = null;
//        }
    }

//    public void printDuplicatedVoters() {
//
//        for (String voter : voterCount.keySet()) {
//
//            byte count = voterCount.get(voter);
//            if (count > 1) {
//                System.out.println(voter + " - " + count);
//            }
//        }
//    }
}
