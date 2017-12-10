package org.sementsinsky.filmSearch.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "films")
public class Film implements IEntity<Integer>{

    @Id
    @GenericGenerator(
            name = "film_id_generator",
            strategy = "sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "sequence",
                            value = "film_id_seq"
                    )
            })
    @GeneratedValue(generator = "film_id_generator")
    @Column(name = "film_id")
    protected Integer id;

    @Column(name="film_name")
    private String name;

    @Column(name="rating")
    private double rating;

    @ManyToOne
    @JoinColumn(name="producer_id")
    private Producer producer;

    @Column(name="year")
    private int year;

    public Film(){}

    public Film(String name, double rating, Producer producer, int year){
        this.name = name;
        this.rating = rating;
        this.producer = producer;
        this.year = year;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getRating(){
        return rating;
    }

    public void setRating(double rating){
        this.rating = rating;
    }

    public Producer getProducer(){
        return producer;
    }

    public void setProducer(Producer producer){
        this.producer = producer;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }

    @Override
    public String toString(){
        return  "{name='"+name+'\''+
                ",rating='"+rating+'\''+
                ",producer="+producer.toString()+'\''+
                ",year='"+year+"'}";
    }

    public boolean equals(Film film){
        return this.toString().equals(film.toString());
    }
}
