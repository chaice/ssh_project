package com.ccit.pojo;


import javax.persistence.*;

@Entity
@Table(name = "t_ill")
public class Ill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String illname;
    @ManyToOne
    @JoinColumn(name = "officeid")
    private Office office;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIllname() {
        return illname;
    }

    public void setIllname(String illname) {
        this.illname = illname;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
