import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Query query = session.createQuery("SELECT name FROM Student");

        List<String> namesStudents = query.list();

        for (String name : namesStudents) {
            System.out.println(name);
        }

        Course course = session.get(Course.class, 1);
        System.out.println(course.getName());

        Student student = session.get(Student.class, 1);
        System.out.println(student.getId());

        Teacher teacher = session.get(Teacher.class, 1);
        System.out.println(teacher.getAge());



        sessionFactory.close();
    }
}
