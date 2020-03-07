package EclipseLink;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class Test {

    public static void samain(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();//KANALI ACAR
        Customer c1= new Customer("1","Mertali","Koprulu");
        Product p1= new Product("p1",95.0);
        Purchase b1= new Purchase(c1,p1,new Date());

        em.persist(c1);
        em.persist(p1);
        em.persist(b1);
        et.commit();
        em.close();
    }

    public static void zain(String[] args){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em= emf.createEntityManager();

        Customer res= em.find(Customer.class,"1");
        System.out.println(res.getFullName());
    }

    public static void cain(String[] args) {        // 1 kere kullanacagim query ise bunu KULLAN !
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em= emf.createEntityManager();
       // Query a = em.createNativeQuery("Select custId From ..."); //ICINE DIREK QUERY YAZILABILIR NAMED QUERY HER ZAMAN DAHA IYIDIR PROJE BOLUMU ACISINDAN
        Query a = em.createQuery("Select c from Customer c ");
        List<Customer> res=  a.getResultList();
        ObservableList<Customer> o1= FXCollections.observableArrayList(res);
        System.out.println(res.size());
        o1.forEach(b->{
            System.out.println(b.getFullName());
        });
    }

    public static void eain(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em= emf.createEntityManager();
        TypedQuery<Customer> returned = em.createQuery("select C from Customer C where C.firstName = :fName",Customer.class);
        returned.setParameter("fName","Mertali");
        List<Customer> listOfCustomers= returned.getResultList();
        System.out.println(listOfCustomers.size());
        listOfCustomers.forEach(p-> System.out.println(p.getFullName()));
    }

    public static void main(String[] args) {//Named query test
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em= emf.createEntityManager();
        Query q= em.createNamedQuery("findAllCustomers");
        List<Customer> r=q.getResultList();
        //r.forEach((r1)->r1.getFullName());
        r.forEach(p->{
            System.out.println(p.printCustom(a -> "Id: "+a.getCustId()+" , "+a.getFullName() ));
        }  );
        em.close();
        //Entitiy Manageri kapatmayi unutma !!
    }

    public static void mcain(String[] args) {//Named Query with parameter
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em= emf.createEntityManager();
        Query q= em.createNamedQuery("filterbyName");
        q.setParameter("namee","Mertali");
        List<Customer> r=q.getResultList();
        System.out.println(r.get(0).getFullName());
        em.close();
    }







}
