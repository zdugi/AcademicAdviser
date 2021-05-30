package com.academic.adviser.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Dormitory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
