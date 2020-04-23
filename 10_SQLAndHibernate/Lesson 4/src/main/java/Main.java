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

        List<PurchaseList> purchaseList = session.createQuery(query).getResultList();

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

        for (PurchaseList purchase : purchaseList) {

            CompositeKey compositeKey = purchase.getCompositeKey();

            LinkedPurchaseList linkedPurchase = new LinkedPurchaseList(
                    students.get(compositeKey.getStudent()),
                    courses.get(compositeKey.getCourse()));

            session.save(linkedPurchase);
        }

        transaction.commit();
        sessionFactory.close();
    }
}


//    String hql = "From " + Course.class.getSimpleName() + " Where price > 120000";
//    List<Course> coursesList = session.createQuery(hql).getResultList();
//
//        for (Course course : coursesList) {
//                System.out.println(course.getName() + " - " + course.getPrice());
//                }

//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Course> query = builder.createQuery(Course.class);
//        Root<Course> root = query.from(Course.class);
//        query.select(root).where(builder.greaterThan(root.<Integer>get("price"), 100_000))
//                .orderBy(builder.desc(root.get("price")));
//        List<Course> courseList = session.createQuery(query).setMaxResults(5).getResultList();
//
//        for (Course course : courseList) {
//            System.out.println(course.getName() + " - " + course.getPrice());
//        }