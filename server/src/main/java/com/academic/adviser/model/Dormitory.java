package com.academic.adviser.model;

import javax.persistence.*;

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

    //TODO: Make list of dormitory users that are described with BigFive traits
}
