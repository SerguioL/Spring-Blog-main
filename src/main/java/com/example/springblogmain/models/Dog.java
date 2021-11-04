package com.example.springblogmain.models;

import javax.persistence.*;

@Entity
@Table(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String age;
    private String name;
    private String resideState;



}
