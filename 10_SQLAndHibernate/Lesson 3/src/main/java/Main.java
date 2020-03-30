import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import javax.persistence.EmbeddedId;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

//        Course course = session.get(Course.class, 1);
//        List<Student> students = course.getStudents();
//
//        for (Student student : students) {
//            System.out.println(student.getName());
//        }

        Subscription subscription = session.get(Subscription.class, 1);
        System.out.println(subscription);

        transaction.commit();
        sessionFactory.close();
    }
}
