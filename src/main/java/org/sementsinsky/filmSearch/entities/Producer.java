package org.sementsinsky.filmSearch.entities;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name="producers")
public class Producer implements IEntity<UUID>{
    @Id
    @GenericGenerator(
            name = "producer_id_seq",
            strategy = "sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "sequence",
                            value = "producer_id_seq"
                    )
            })
    @GeneratedValue(generator = "producer_id_seq")
    @Column(name="id")
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="birth_date")
    private Date birthDate;

    public Producer(){}

    public Producer(String name, Date birthDate){
        this.name = name;
        this.birthDate = birthDate;
    }

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Date getBirthDate(){
        return birthDate;
    }

    public void setBirthDate(Date birthDate){
        this.birthDate = birthDate;
    }

    @Override
    public String toString(){
        return  "{name='"+name+'\''+
                ",birthDate='"+birthDate+"'}";
    }

    public boolean equals(Producer producer){
        return this.toString().equals(producer.toString());
    }
}
