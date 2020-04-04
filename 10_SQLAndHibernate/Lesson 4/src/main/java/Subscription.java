import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @EmbeddedId
    private CompositeKey compositeKey;

    @Column(name = "subscription_date")
    private Date subscriptionDate;
}
