package com.example.springblogmain.models;

import javax.persistence.*;

@Entity
@Table(name = "coffee")
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 6)
    private String roast;

    @Column(nullable = false, length = 50)
    private String origin;

    @Column(nullable = false, length = 50)
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Coffee() {
    }

    public Coffee(String roast, String brand) {
        this.roast = roast;
        this.brand = brand;
    }

    public Coffee(String roast, String origin, String brand) {
        this.roast = roast;
        this.origin = origin;
        this.brand = brand;
    }

    public String getRoast() {
        return roast;
    }

    public void setRoast(String roast) {
        this.roast = roast;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
