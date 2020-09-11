
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.MovieFacade;
import javax.persistence.EntityManagerFactory;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author magda
 */
@Path("movie")
public class MovieResource {

   
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
        
    private static final MovieFacade FACADE =  MovieFacade.getMovieFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of MovieResource
     */
  

    /**
     * Retrieves representation of an instance of rest.MovieResource
     * @return an instance of java.lang.String
     */
    
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String demo() {
     //   return "{\"msg\":\"<p1>Magdalena's Demo<p1>\"}";
     return "<h1>Magdalena's Demo<h1>";
    }

    /*
@GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
   */
}
