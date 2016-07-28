package com.ccit.pojo;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "t_card")
public class Card {
    @Id
    @GenericGenerator(name="fk",strategy ="foregin",parameters ={@Parameter(name = "property",value = "person")})
    private Integer id;
    private String cardname;
    @OneToOne(mappedBy = "card")
    private Person person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
