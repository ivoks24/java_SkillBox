import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.sql.SQLException;

public class SAXParser extends DefaultHandler {

    public SAXParser(String fileName) throws Exception {

        long start = System.currentTimeMillis();
        parseFile(fileName);

        System.out.println("Parsing duration: " + (System.currentTimeMillis() - start) + " ms");
//        DBConnection.printVoterCounts();

    }

    private void parseFile(String fileName) throws Exception {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser parser = factory.newSAXParser();
        parser.parse(new File(fileName), this);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        try {
            if (qName.equals("voter")) {

                DBConnection.countVoter(
                        attributes.getValue("name"),
                        attributes.getValue("birthDay")
                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equals("voters")) {
            try {
                DBConnection.executeMultiInsert();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
