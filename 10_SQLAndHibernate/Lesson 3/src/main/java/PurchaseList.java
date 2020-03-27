import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "purchaselist")
public class PurchaseList {

    @Column(name = "student_name")
    private Student student;

    @Column(name = "course_name")
    private Course course;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;
}
