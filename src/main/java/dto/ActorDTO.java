/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Actor;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author magda
 */
class ActorDTO {
    
    private int id;
    private String name;
    private List<MovieDTO> movies;

    public ActorDTO(Actor actor) {
        this.name=actor.getName();
        this.movies=new ArrayList();
    }
    
    public void transferMovies(Actor actor) {
        for (Movie movie : actor.getCastedMovies()){
            MovieDTO movieDTO = new MovieDTO(movie);
            this.movies.add(movieDTO);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDTO> movies) {
        this.movies = movies;
    }
    
    
    
           
    
}
