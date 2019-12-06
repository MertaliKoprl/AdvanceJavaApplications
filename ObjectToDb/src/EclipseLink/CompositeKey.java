package EclipseLink;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CompositeKey implements Serializable {
private String custId;
private String productId;

    public CompositeKey(String custId, String productId) {
        this.custId = custId;
        this.productId = productId;
    }


    public CompositeKey() {
    }

}
