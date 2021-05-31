package com.academic.adviser.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Dormitory {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Candidate> candidates;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public City getCity() {
        return city;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dormitory dormitory = (Dormitory) o;
        return id.equals(dormitory.id) && name.equals(dormitory.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
