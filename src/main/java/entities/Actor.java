/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author magda
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Actor.deleteAllRows", query = "DELETE from Actor"),
@NamedQuery(name = "Actor.getAll", query = "SELECT a FROM Actor a"),
@NamedQuery(name = "Actor.getByName", query = "SELECT a FROM Actor a WHERE a.name LIKE :name")})
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    private int yearOfBirth;
    
    @ManyToMany(mappedBy = "actorsCast")
    private List<Movie> castedMovies;
    
    

    public Actor() {
    }

    public Actor(String name, int yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.castedMovies = new ArrayList();
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
    public void addMovie(Movie movie){
        this.castedMovies.add(movie);
    }

    public List<Movie> getCastedMovies() {
        return castedMovies;
    }

    public void setCastedMovies(List<Movie> castedMovies) {
        this.castedMovies = castedMovies;
    }
    
    
    
}
