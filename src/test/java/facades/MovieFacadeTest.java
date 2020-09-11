/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Actor;
import entities.Movie;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author magda
 */
public class MovieFacadeTest {

//Uncomment the line below, to temporarily disable this test
//@Disabled
    private static EntityManagerFactory emf;
    private static MovieFacade facade;

    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MovieFacade.getMovieFacade(emf);

    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        Actor actor1 = new Actor("Magdalena", 1988);
        Actor actor2 = new Actor("Aleksandra", 1963);
        Actor actor3 = new Actor("Karolina", 2014);

        Movie m1 = new Movie("Titanic", 1999);
        Movie m2 = new Movie("King Kong", 1973);
        m1.addActor(actor3);
        m1.addActor(actor2);
        m2.addActor(actor1);
        m2.addActor(actor2);

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.persist(actor1);
            em.persist(actor2);
            em.persist(actor3);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.createNamedQuery("Actor.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void TestSetUp() {

        EntityManager em = emf.createEntityManager();
        long movieCount = 0;
        long actorCount = 0;
        try {
            movieCount = (long) em.createQuery("SELECT COUNT(m) FROM Movie m").getSingleResult();
            actorCount = (long) em.createQuery("SELECT COUNT(a) FROM Actor a").getSingleResult();

            System.out.println("movies: " + movieCount + " actors: " + actorCount);

        } finally {
            em.close();
        }
        assertEquals(5L, movieCount + actorCount);
    }

    
     @Test
    public void getFromBeforeYearTest() {
       assertTrue(facade.getFromBeforeYear(1990).get(0).getTitle().equals("King Kong"), "Expects two rows in the database");
    }
    
    @Test
    public void addNewMovieTest() {
        String title="Test";
        facade.addNewMovie(title, 2018);
        Movie result = facade.findBMovieByTitle(title);
        EntityManager em = emf.createEntityManager();  
        assertTrue(result.getTitle().equals(title));
    }

}
