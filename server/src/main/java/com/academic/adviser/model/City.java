package com.academic.adviser.model;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id.equals(city.id) && name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
