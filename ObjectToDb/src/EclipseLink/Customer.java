package EclipseLink;

import javax.persistence.*;
import java.io.Serializable;
import java.util.function.Function;


//Kullanacagimiz zaman cagiricagiz! Statictir !!!
@NamedQueries({
        @NamedQuery( name="findAllCustomers" , query="Select p from Customer p"),
        @NamedQuery(name="filterbyName" ,query = "Select c from Customer c where c.firstName= :namee")
})
@Entity
public class Customer implements Serializable {

    //@OneToOne (cascade=CascadeType.PERSIST) YAPARSAN HER HANGI BIR TABLOYA YAPILAN BIR EKLEME DIGER TABLOLARADA EKLENIR !
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //IT MEANS THE FIELD IS AUTO INCREMENT KEY
    private String custId;    //ID Annotation Primary Keyi belirler !

    private String firstName;
    private String lastName;

    public Customer() {
    }

    public Customer(String custId, String firstName, String lastName) {
        this.custId = custId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //accessor/mutators

    public String getCustId() {
        return custId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        StringBuilder sb=new StringBuilder();
        sb.append(this.firstName);
        sb.append(" ");
        sb.append(this.lastName);
        return sb.toString();

    }

    public String printCustom(Function<Customer, String> f){
        return f.apply(this);
    }


}
