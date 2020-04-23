import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

//import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build(); //"hibernate.cfg.xml"
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        Transaction transaction = session.beginTransaction();

        CriteriaQuery<PurchaseList>  query = builder.createQuery(PurchaseList.class);
        Root<PurchaseList> root = query.from(PurchaseList.class);
        query.select(root);



        Map<String, Integer> courses = new HashMap<>();
        session.createQuery("SELECT name, id FROM Course").getResultList().forEach(e -> {
            Object[] g = (Object[]) e;
            courses.put((String) g[0],  (Integer) g[1]);
        });

        Map<String, Integer> students = new HashMap<>();
        session.createQuery("SELECT name, id FROM Student").getResultList().forEach(e -> {
            Object[] g = (Object[]) e;
            students.put((String) g[0],  (Integer) g[1]);
        });

        session.createQuery(query).getResultList().forEach(purchase -> {

            CompositeKey compositeKey = purchase.getCompositeKey();

            LinkedPurchaseList linkedPurchase = new LinkedPurchaseList(
                    students.get(compositeKey.getStudent()),
                    courses.get(compositeKey.getCourse()));

            session.save(linkedPurchase);
        });

        transaction.commit();
        sessionFactory.close();
    }
}