package com.academic.adviser.model;

import org.kie.api.definition.type.Position;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Major {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer minScore;

    @ManyToMany
    @JoinTable(
            name = "major_area",
            joinColumns = @JoinColumn(name = "major_id"),
            inverseJoinColumns = @JoinColumn(name = "career_area_id"))
    private Set<CareerArea> careerArea;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public Major() {}

    public Major(Integer id, String name, Integer minScore, Set<CareerArea> careerArea, City city) {
        this.id = id;
        this.name = name;
        this.minScore = minScore;
        this.careerArea = careerArea;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

    public Set<CareerArea> getCareerArea() {
        return careerArea;
    }

    public void setCareerArea(Set<CareerArea> careerArea) {
        this.careerArea = careerArea;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
