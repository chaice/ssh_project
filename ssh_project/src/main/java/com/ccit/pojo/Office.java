package com.ccit.pojo;

import javax.persistence.*;

@Entity
@Table(name = "t_office")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String officename;
    private String responsname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOfficename() {
        return officename;
    }

    public void setOfficename(String officename) {
        this.officename = officename;
    }

    public String getResponsname() {
        return responsname;
    }

    public void setResponsname(String responsname) {
        this.responsname = responsname;
    }
}
