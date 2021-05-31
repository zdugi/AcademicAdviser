package com.academic.adviser.drools.model;

import com.academic.adviser.model.City;
import com.academic.adviser.model.Dormitory;

import java.util.*;

public class AcademicLife {
    private Set<Dormitory> dormitories;
    private Map<Integer, City> lifeCost;

    public AcademicLife() {
        dormitories = new HashSet<>();
        lifeCost = new HashMap<>();
    }

    public AcademicLife(Set<Dormitory> dormitories, Map<Integer, City> lifeCost) {
        this.dormitories = dormitories;
        this.lifeCost = lifeCost;
    }

    public void extendDorms(List<Dormitory> dorms) {
        dormitories.addAll(dorms);
    }

    public void addCityLifeCost(City city) {
        this.lifeCost.put(city.getId(), city);
    }

    public Set<Dormitory> getDormitories() {
        return dormitories;
    }

    public Map<Integer, City> getLifeCost() {
        return lifeCost;
    }
}
