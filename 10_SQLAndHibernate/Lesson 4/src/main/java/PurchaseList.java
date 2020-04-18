import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "PurchaseList")
public class PurchaseList {

    @EmbeddedId
    private CompositeKey compositeKey;

    @Column(name = "student_name")
    private String student;

    @Column(name = "course_name")
    private String course;

    @Column(name = "price")
    private int price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "subscription_date")
    private Date subscriptionDate;
}
