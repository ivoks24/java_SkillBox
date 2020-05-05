import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class Main {

    private static MongoCollection<Document> collection;
    private final static String filePath = "src/main/resources/mongo.csv";

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("test");

        collection = database.getCollection("Students"); // Создаем коллекцию
        collection.drop(); // Удалим из нее все документы

        readCSV(filePath);

        System.out.println("\n— общее количество студентов в базе: " + collection.countDocuments());

        System.out.println("\n— количество студентов старше 40 лет: " + collection
                .countDocuments(new Document("age", new Document("$gt", 40))));

        collection.find().sort(new Document("age", 1)).limit(1).forEach((Consumer<Document>) doc ->
                System.out.println("\n— имя самого молодого студента: " + doc.get("name")));

        collection.find().sort(new Document("age", -1)).limit(1).forEach((Consumer<Document>) doc ->
                System.out.println("\n— список курсов самого старого студента: " + doc.get("courses")));
                        //doc.entrySet().toArray()[3]));
    }

    private static void readCSV(String filePath) {

        BufferedReader reader = null;

        try {
            String line;
            reader = new BufferedReader(new FileReader(filePath));

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", 3);

                Document document = new Document()
                        .append("name", fields[0])
                        .append("age", Integer.parseInt(fields[1]))
                        .append("courses", new ArrayList<>(Arrays.asList(fields[2]
                                .replace("\"", "")
                                .split(","))));

                collection.insertOne(document);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
