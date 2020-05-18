import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class XMLHandlerImproved extends DefaultHandler {

    private static final SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private final HashMap<String, Byte> voterCount;
    private long usage;

    public XMLHandlerImproved(String fileName) throws Exception {

        voterCount = new HashMap<>();


        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File(fileName), this);

        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        try {
            if (qName.equals("voter")) {
                Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
                String voterString = new Voter(attributes.getValue("name"), birthDay).toString();

                byte count = voterCount.getOrDefault(voterString, (byte) 0);
                voterCount.put(voterString, (byte) (count + 1));
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

//    @Override
//    public void endElement(String uri, String localName, String qName) {
//
//        if (qName.equals("voter")) {
//            voter = null;
//        }
//    }

    public void checkMemory() {
        System.out.printf(Locale.GERMAN, "\nЗамер по памяти при парсинге SAX-improved: %,d", usage);
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
