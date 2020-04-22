import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LinkedPurchaseList")
@IdClass(LinkedPurchaseList.Id.class)
public class LinkedPurchaseList {

    @javax.persistence.Id
    @Column(name = "student_id")
    private Integer studentId;

    @javax.persistence.Id
    @Column(name = "course_id")
    private Integer courseId;
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Id implements Serializable {

        private Integer studentId;

        private Integer courseId;
    }
}
