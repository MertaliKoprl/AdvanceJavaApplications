package EclipseLink;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Product {
    @Id
    String productId;
    Double price;


    public Product(){

    }
    public Product(String productId,Double price){
        this.productId=productId;
        this.price=price;

    }

    public Double getPrice() {
        return price;
    }

    public String getProductId() {
        return productId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
