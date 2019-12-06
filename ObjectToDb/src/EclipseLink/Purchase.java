package EclipseLink;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Purchase implements Serializable {

    @EmbeddedId
    private CompositeKey ck;

    @JoinColumn(name = "custId",referencedColumnName = "custId",insertable = false,updatable = false)
    @ManyToOne(optional = false)
    Customer cust;

    @JoinColumn(name = "productId",referencedColumnName = "productId",insertable = false,updatable = false)
    @ManyToOne
    Product pro ;
    @Temporal(TemporalType.DATE)
    Date dateOfPurchase;


    public Purchase(){

    }
    public Purchase(Customer cust,Product pro,Date d1){
        this.cust=cust;
        this.pro=pro;
        ck= new CompositeKey(cust.getCustId(),pro.getProductId());
        dateOfPurchase= d1;
    }


    public Customer getCust() {
        return cust;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public Product getPro() {
        return pro;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public void setPro(Product pro) {
        this.pro = pro;
    }

    public CompositeKey getCk() {
        return ck;
    }

}
