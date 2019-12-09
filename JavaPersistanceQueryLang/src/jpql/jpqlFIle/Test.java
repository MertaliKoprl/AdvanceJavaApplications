package jpql.jpqlFIle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test {

    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        PlayerEntity p1 = new PlayerEntity();
        p1.setId("3");
        p1.setPname("Caner");
        p1.setPosition("Adc");
        p1.setSalary(200.5);

        tx.begin();
        em.persist(p1);
        tx.commit();
        em.close();

    }
}
