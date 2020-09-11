package rest;

import dto.MovieDTO;
import entities.Actor;
import facades.ActorFacade;
import java.util.ArrayList;
//import javax.websocket.server.PathParam;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author magda
 */
@Path("movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final MovieFacade FACADE = MovieFacade.getMovieFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of MovieResource
     */
    /**
     * Retrieves representation of an instance of rest.MovieResource
     *
     * @return an instance of java.lang.String
     */
    /*
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String demo() {
     //   return "{\"msg\":\"<p1>Magdalena's Demo<p1>\"}";
     return "<h1>Magdalena's Demo<h1>";
    }

     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String demo() {
        //   return "{\"msg\":\"<p1>Magdalena's Demo<p1>\"}";
        return "<h1>Magdalena's Demo<h1>";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String countAll() {
        long count = FACADE.countAllMovies();
     
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @Path("olderthan/{year}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getOlderMovies(@PathParam("year") int year) {

        List<Movie> older = FACADE.getFromBeforeYear(year);

        List<MovieDTO> olderDTO = new ArrayList();

        for (Movie movie : older) {
            MovieDTO movieDTO = new MovieDTO(movie);
            olderDTO.add(movieDTO);
        }
        return new Gson().toJson(olderDTO);
    }

    @Path("getMovieByID/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovieByID(@PathParam("id") int id) {

        Movie movie = FACADE.findBMovieById(id);
        MovieDTO movieDTO = new MovieDTO(movie);
        movieDTO.transferActors(movie);
        movieDTO.setId(movie.getId());
        return new Gson().toJson(movieDTO);
    }

    @Path("allmovies")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getALlMovies() {
        List<Movie> all = FACADE.getAllMovies();
        List<MovieDTO> allDTO = new ArrayList<>();

        for (Movie movie : all) {
            MovieDTO movieDTO = new MovieDTO(movie);
            movieDTO.transferActors(movie);
            movieDTO.setId(movie.getId());
            allDTO.add(movieDTO);
        }
        return new Gson().toJson(allDTO);
    }

    @Path("insertdata")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String addFew() {
        Movie m1 = new Movie("Why not?", 2020);
        Movie m2 = new Movie("Pretty Woman", 2004);
        Movie m3 = new Movie("ALien", 1988);
        Movie m4 = new Movie("ERROR 400", 1969);
        Actor a1 = new Actor("Magdalena Wawrzak", 1978);
        Actor a2 = new Actor("Camilla Mortenson", 1984);
        Actor a3 = new Actor("Moniqe Mon Blanc", 1963);
        Actor a4 = new Actor("Peter Pan", 1945);

        ActorFacade aFacade = ActorFacade.getActorFacade(EMF);
        aFacade.addNewActor(a1);
        aFacade.addNewActor(a2);
        aFacade.addNewActor(a3);
        aFacade.addNewActor(a4);

        m1.addActor(a4);
        m1.addActor(a2);

        m2.addActor(a1);
        m2.addActor(a3);
        m2.addActor(a4);

        m3.addActor(a4);

        m4.addActor(a1);
        m4.addActor(a3);
        m4.addActor(a2);
        m4.addActor(a4);

        FACADE.addNewMovie(m1);
        FACADE.addNewMovie(m2);
        FACADE.addNewMovie(m3);
        FACADE.addNewMovie(m4);

        MovieDTO md1 = new MovieDTO(m1);
        MovieDTO md2 = new MovieDTO(m2);
        MovieDTO md3 = new MovieDTO(m3);
        MovieDTO md4 = new MovieDTO(m4);

        List<MovieDTO> dtos = new ArrayList();
        dtos.add(md1);
        dtos.add(md2);
        dtos.add(md3);
        dtos.add(md4);
        return new Gson().toJson(dtos);
    }

}
