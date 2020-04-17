import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "purchaselist")
public class PurchaseList {

    @EmbeddedId
    private CompositeKey compositeKey;

    @Column(name = "student_name")
    private Student student;

    @Column(name = "course_name")
    private Course course;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;
}
