package com.ccit.pojo;

import javax.persistence.*;

@Entity
@Table(name = "t_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bookname;
    private String bookauthor;
    private Float bookprice;
    private Integer booknum;

}
