import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription extends SubscriptionPK {

    @EmbeddedId
    private SubscriptionPK subscriptionPK;

//    @Column(name = "student_id")
//    private int student;
//
//    @Column(name = "course_id")
//    private int course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;
}
