import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.tool.schema.TargetType;

import javax.persistence.EmbeddedId;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();



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