import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class DOMParser {

    private static final SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static final SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static final HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private static final HashMap<Voter, Integer> voterCounts = new HashMap<>();

    public DOMParser(String fileName) throws Exception {

        long start = System.currentTimeMillis();
        parseFile(fileName);
        System.out.println("Parsing duration: " + (System.currentTimeMillis() - start) + " ms");

        DBConnection.printVoterCounts();

    }

    private static void parseFile(String fileName) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));

        findEqualVoters(doc);
        fixWorkTimes(doc);
    }

    private static void findEqualVoters(Document doc) throws Exception {
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();
        for(int i = 0; i < votersCount; i++)
        {
            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();

            String name = attributes.getNamedItem("name").getNodeValue();
            String birthDay = attributes.getNamedItem("birthDay").getNodeValue();
//            Date birthDay = birthDayFormat.parse(attributes.getNamedItem("birthDay").getNodeValue());

            DBConnection.countVoter(name, birthDay);

//            Voter voter = new Voter(name, birthDay);
//            Integer count = voterCounts.get(voter);
//            voterCounts.put(voter, count == null ? 1 : count + 1);
        }
        DBConnection.executeMultiInsert();
    }

    private static void fixWorkTimes(Document doc) throws Exception {
        NodeList visits = doc.getElementsByTagName("visit");
        int visitCount = visits.getLength();
        for(int i = 0; i < visitCount; i++)
        {
            Node node = visits.item(i);
            NamedNodeMap attributes = node.getAttributes();

            Integer station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
            Date time = visitDateFormat.parse(attributes.getNamedItem("time").getNodeValue());
            WorkTime workTime = voteStationWorkTimes.get(station);
            if(workTime == null)
            {
                workTime = new WorkTime();
                voteStationWorkTimes.put(station, workTime);
            }
            workTime.addVisitTime(time.getTime());
        }
    }

//    public void printVoters() {
//
//        //Printing results
//        System.out.println("Voting station work times: ");
//        for(Integer votingStation : voteStationWorkTimes.keySet())
//        {
//            WorkTime workTime = voteStationWorkTimes.get(votingStation);
//            System.out.println("\t" + votingStation + " - " + workTime);
//        }
//
//        System.out.println("Duplicated voters: ");
//        for(Voter voter : voterCounts.keySet())
//        {
//            Integer count = voterCounts.get(voter);
//            if(count > 1) {
//                System.out.println("\t" + voter + " - " + count);
//            }
//        }
//    }
}
