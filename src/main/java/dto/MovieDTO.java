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
public class MovieDTO {
    private int id;
    private String title;
    private int year;
    private List<ActorDTO> actors;

    public MovieDTO(Movie movie) {
        this.title = movie.getTitle();
        this.year = movie.getPremiereDate();
        this.actors = new ArrayList();
    }
    
    public void transferActors(Movie movie) {
        for (Actor actor : movie.getActorsCast()) {
            ActorDTO actorDTO = new ActorDTO(actor);
            this.actors.add(actorDTO);            
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<ActorDTO> getActors() {
        return actors;
    }

    public void setActors(List<ActorDTO> actors) {
        this.actors = actors;
    }
    
    
    
    
    
    
    
}
