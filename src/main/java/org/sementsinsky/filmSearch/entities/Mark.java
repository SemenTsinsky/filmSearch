package org.sementsinsky.filmSearch.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="marks")
public class Mark implements IEntity<UUID>{

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="film_id")
    private Film film;

    @Column(name="mark")
    private int mark;

    @Id
    @GenericGenerator(
            name = "mark_id_seq",
            strategy = "sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "sequence",
                            value = "mark_id_seq"
                    )
            })
    @GeneratedValue(generator = "mark_id_seq")
    @Column(name = "mark_id")
    private UUID Id;

    public Mark(){}

    public Mark(User user, Film film, int mark){
        this.user = user;
        this.film = film;
        this.mark = mark;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID Id){
        this.Id = Id;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public Film getFilm(){
        return film;
    }

    public void setFilm(Film film){
        this.film = film;
    }

    public int getMark(){
        return mark;
    }

    public void setMark(int mark){
        this.mark = mark;
    }

    @Override
    public String toString(){
        return  "{userId='"+user.toString()+'\''+
                ",filmId='"+film.toString()+'\''+
                ",mark='"+mark+"}";
    }

    public boolean equals(Mark mark){
        return this.toString().equals((mark.toString()));
    }
}
