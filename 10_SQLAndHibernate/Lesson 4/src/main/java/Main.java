import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

//import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build(); //"hibernate.cfg.xml"
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        Transaction transaction = session.beginTransaction();

        CriteriaQuery<PurchaseList>  queryPurchase = builder.createQuery(PurchaseList.class);
        Root<PurchaseList> rootPurchase = queryPurchase.from(PurchaseList.class);
        queryPurchase.select(rootPurchase);

//        Query query = session.createQuery("FROM PurchaseList");
        List<PurchaseList> purchaseList = session.createQuery(queryPurchase).getResultList(); //session.createQuery(queryPurchase).getResultList();


        for (PurchaseList purchase : purchaseList) {

            Query<Course> fromCourseQuery = session.createQuery("From Course where name = :name", Course.class);
            fromCourseQuery.setParameter("name", purchase.getCompositeKey().getCourse());
            Course course = fromCourseQuery.getSingleResult();

            Query<Student> fromStudentQuery = session.createQuery("From Student where name = :name", Student.class);
            fromStudentQuery.setParameter("name", purchase.getCompositeKey().getStudent());
            Student student = fromStudentQuery.getSingleResult();

            LinkedPurchaseList linkedPurchase = new LinkedPurchaseList(student.getId(), course.getId());
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