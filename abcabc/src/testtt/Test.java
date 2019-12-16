package testtt;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Test {

    public static void vain(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em= emf.createEntityManager();
        EntityTransaction tx= em.getTransaction();


        UserEntity u1= new UserEntity();
        u1.setUsername("Merto");
        u1.setUserType(2);
        u1.setPw("asb");

        tx.begin();
        em.persist(u1);
        tx.commit();
        em.close();

    }

    public static void kain(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em= emf.createEntityManager();
        EntityTransaction tx= em.getTransaction();

        Query a = em.createQuery("Select c from UserEntity c");
        List<UserEntity> res=  a.getResultList();
        System.out.println(res.size());
    }

    public static void main(String[] args) {

    }
}
