package view;


import course.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em= emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Course c = new Course ("CSE482","advanced java programming",3);

        em.persist(c);
        etx.commit();
        em.close();

    }
}
