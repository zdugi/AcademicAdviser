package com.academic.adviser.model;

import com.academic.adviser.constants.BigFiveTraitLevel;
import org.kie.api.definition.type.Modifies;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class CareerArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
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

    @Modifies({"id", "name", "rang"})
    public void changeProps(CareerArea careerArea) {
        this.id = careerArea.getId();
        this.name = careerArea.getName();
        this.rang = careerArea.getRang();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CareerArea that = (CareerArea) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
