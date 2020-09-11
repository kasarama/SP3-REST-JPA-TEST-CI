/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Actor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author magda
 */
public class ActorFacade {
    
    
        
    private static ActorFacade instance;
    private static EntityManagerFactory emf;

    public ActorFacade() {
    }
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ActorFacade getActorFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ActorFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long countAllActors(){
        EntityManager em = emf.createEntityManager();
        try{
            long movieCount = (long)em.createQuery("SELECT COUNT(a) FROM Actor a").getSingleResult();
            return movieCount;
        }finally{  
            em.close();
        }
        
    }
    
    public Actor addNewActor(Actor actor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(actor);
            em.getTransaction().commit();
            return actor;
        } finally {
            em.close();
        }
    }

}

