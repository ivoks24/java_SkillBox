import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {

    @EmbeddedId
    private CompositeKey compositeKey;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "student_id")
    private Integer studentId;
}
