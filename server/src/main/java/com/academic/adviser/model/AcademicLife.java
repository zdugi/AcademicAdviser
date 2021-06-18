package com.academic.adviser.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class AcademicLife {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    @JoinTable(
            name = "dormitories_life",
            joinColumns = @JoinColumn(name = "academic_life_id"),
            inverseJoinColumns = @JoinColumn(name = "dormitory_id"))
    private Set<Dormitory> dormitories;

    @ManyToMany
    @JoinTable(
            name = "life_cost",
            joinColumns = @JoinColumn(name = "academic_life_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id"))
    private Set<City> lifeCost;

    @ManyToMany
    @JoinTable(
            name = "suggested_majors",
            joinColumns = @JoinColumn(name = "academic_life_id"),
            inverseJoinColumns = @JoinColumn(name = "major_id"))
    private Set<Major> majors;

    public AcademicLife() {
        dormitories = new HashSet<>();
        lifeCost = new HashSet<>();
        majors = new HashSet<>();
    }

    public AcademicLife(Set<Dormitory> dormitories, Set<City> lifeCost) {
        this.dormitories = dormitories;
        this.lifeCost = lifeCost;
    }

    public void extendDorms(List<Dormitory> dorms) {
        dormitories.addAll(dorms);
    }

    public void addCityLifeCost(City city) {
        this.lifeCost.add(city);
    }

    public Set<Dormitory> getDormitories() {
        return dormitories;
    }

    public Set<City> getLifeCost() {
        return lifeCost;
    }

    public Set<Major> getMajors() {
        return majors;
    }

    public void setMajors(Set<Major> majors) {
        this.majors = majors;
    }
}
