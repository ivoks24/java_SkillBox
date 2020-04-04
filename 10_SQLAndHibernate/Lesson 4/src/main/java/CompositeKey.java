import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositeKey implements Serializable {

    @ManyToOne//(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "student_id") //insertable = false, updatable = false, nullable=false
    private Student student;

    @ManyToOne//(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "course_id")
    private Course course;

}
