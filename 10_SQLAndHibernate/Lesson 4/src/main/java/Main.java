import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<PurchaseList>  queryPurchase = builder.createQuery(PurchaseList.class);
        Root<PurchaseList> rootPurchase = queryPurchase.from(PurchaseList.class);
        queryPurchase.select(rootPurchase);
        List<PurchaseList> purchaseList = session.createQuery(queryPurchase).getResultList();

        for (PurchaseList p : purchaseList) {
            System.out.println(p.toString());
        }
//
//        CriteriaQuery<Course> queryCourse = builder.createQuery(Course.class);
//        Root<Course> rootCourse = queryCourse.from(Course.class);
//        queryCourse.select(rootCourse);
//        List<Course> courseList = session.createQuery(queryCourse).getResultList();
//
//        CriteriaQuery<Student> queryStudent = builder.createQuery(Student.class);
//        Root<Student> rootStudent = queryStudent.from(Student.class);
//        queryStudent.select(rootStudent);
//        List<Student> studentList = session.createQuery(queryStudent).getResultList();

//        for (PurchaseList purchase : purchaseList) {
//            System.out.println(purchase.getCourse());
//        }

//        for (Student student : studentList) {
//            System.out.println(student.getId());
//        }
//
//        for (Course course : courseList) {
//            System.out.println(course.getName() + " - " + course.getPrice());
//        }



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