import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandlerImproved extends DefaultHandler {

//    private Voter voter;
    private String voter;
//    private static final SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private final HashMap<String, Byte> voterCount;

    public XMLHandlerImproved(String fileName) throws Exception {

        voterCount = new HashMap<>();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File(fileName), this);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equals("voter") && voter == null) {
//                Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
//                voter = new Voter(attributes.getValue("name"), birthDay);
            voter = attributes.getValue("name") + " - " + attributes.getValue("birthDay");
        } else if (qName.equals("visit") && voter != null) {
            byte count = voterCount.getOrDefault(voter, (byte) 0);
            voterCount.put(voter, (byte) (count + 1));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equals("voter")) {
            voter = null;
        }
    }

    public void printDuplicatedVoters() {

        for (String voter : voterCount.keySet()) {

            byte count = voterCount.get(voter);
            if (count > 1) {
                System.out.println(voter + " - " + count);
            }
        }
    }
}
