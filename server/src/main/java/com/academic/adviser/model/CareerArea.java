package com.academic.adviser.model;

import com.academic.adviser.constants.BigFiveTraitLevel;

import javax.persistence.*;
import java.util.List;

@Entity
public class CareerArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name="traits")
    private List<BigFiveTraitLevel> traits;

    @Column(nullable = false)
    private Integer rang;

    public CareerArea() {}

    public CareerArea(Integer id, String name, List<BigFiveTraitLevel> traits, Integer rang) {
        this.id = id;
        this.name = name;
        this.traits = traits;
        this.rang = rang;
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

    public List<BigFiveTraitLevel> getTraits() {
        return traits;
    }

    public void setTraits(List<BigFiveTraitLevel> traits) {
        this.traits = traits;
    }

    public Integer getRang() {
        return rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }
}
