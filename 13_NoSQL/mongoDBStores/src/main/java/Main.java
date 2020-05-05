import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    private static MongoCollection<Document> collectionStores;
    private static MongoCollection<Document> collectionProducts;
    private final static String helper = " --- COMMANDS --- "
            + "\nADDS name_store"
            + "\nADDP name_product price"
            + "\nDELIVER name_product name_store"
            + "\nSTATISTICS\n";

    public static void main(String[] args) throws IOException {

        connectMongodb();
        System.out.println(helper);

        addStore("stor");
        addStore("sto");

        addProduct("apple", 10);
        addProduct("strawberry", 15);

        deliver("apple", "sto");


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("\ntype the command: ");
            String[] line = reader.readLine()
                    .trim()
                    .split(" ");

            switch (line[0]) {
                case "ADDS":
                    addStore(line[1]);
                    break;
                case "ADDP":
                    addProduct(line[1], Integer.parseInt(line[2]));
                    break;
                case "DELIVER":
                    deliver(line[1], line[2]);
                    break;
                case "STATISTICS":
                    statistics();
                    break;
            }
        }

    }

    private static void addStore(String nameStore) {

        try {

            collectionStores.find(new Document("name", nameStore)).first().isEmpty();
            System.err.println("name \"" + nameStore + "\" is busy!");

        } catch (NullPointerException ex) {

            Document store = new Document()
                    .append("name", nameStore)
                    .append("products", new ArrayList<String>());

            collectionStores.insertOne(store);
            System.err.println("store \"" + nameStore + "\" added!");
        }
    }

    private static void addProduct(String nameProduct, int price) {

        try {

            collectionProducts.find(new Document("name", nameProduct)).first().isEmpty();
            System.err.println(nameProduct + " is available!");

        } catch (NullPointerException ex) {

            Document product = new Document("name", nameProduct)
                    .append("price", price);

            collectionProducts.insertOne(product);
            System.err.println(nameProduct + " added, price " + price + "!");
        }
    }

    private static void deliver(String nameProduct, String nameStore) {

        try {
//            BsonDocument query = BsonDocument.parse("{name: {$eq: '" + nameStore + "'}}");

            Document store = collectionStores.find(new Document("name", nameStore)).first();
            collectionProducts.find(new Document("name", nameProduct)).first().isEmpty();

            ArrayList<String> products = (ArrayList<String>) store.get("products");
            products.add(nameProduct);

//            Bson updatedValue = new Document("products", products);
//            Bson updatedOperation = new Document("$set", updatedValue);

            System.out.println(store.get("name"));

            Bson name = Filters.eq(store.get("name"));

            Bson updatedValue = new Document("name", "SanFu");
            Bson updatedOperation = new Document("$set", updatedValue);

            collectionStores.updateOne(store, updatedOperation);

            System.out.println(store.get("name"));

        } catch (NullPointerException ex) {
            System.err.println("Not found!");
        }
    }

    private static void statistics() {

    }

    private static void connectMongodb() {

        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("test");

        collectionStores = database.getCollection("stores"); // Создаем коллекцию
        collectionProducts = database.getCollection("products");

//        collectionStores.drop(); // Удалим из нее все документы
//        collectionProducts.drop();
    }
}
