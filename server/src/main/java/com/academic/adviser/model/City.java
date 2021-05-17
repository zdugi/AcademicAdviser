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
}
