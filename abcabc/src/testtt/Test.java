package testtt;

import javax.persistence.*;
import java.util.List;

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

    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em= emf.createEntityManager();
        EntityTransaction tx= em.getTransaction();

        Query a = em.createQuery("Select c from UserEntity c");
        List<UserEntity> res=  a.getResultList();
        System.out.println(res.size());

    }
}
