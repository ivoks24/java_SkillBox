import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne//(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "student_id") //insertable = false, updatable = false, nullable=false
    private Student student;

    @ManyToOne//(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;
}
