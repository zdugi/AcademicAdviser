package com.academic.adviser.model;

import javax.persistence.*;

@Entity
public class City {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double lifeCost;

    @Column(nullable = false)
    private Double rent;

    //TODO: Add list or attributes that describe life cost

    public City() {
    }

    public City(Integer id, String name, Double lifeCost, Double rent) {
        this.id = id;
        this.name = name;
        this.lifeCost = lifeCost;
        this.rent = rent;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getLifeCost() {
        return lifeCost;
    }

    public Double getRent() {
        return rent;
    }
}
