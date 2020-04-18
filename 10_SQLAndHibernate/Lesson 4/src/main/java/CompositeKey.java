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

    @Column(name = "keyStudent")
    private String student;

    @Column(name = "keyCourse")
    private String course;

}
