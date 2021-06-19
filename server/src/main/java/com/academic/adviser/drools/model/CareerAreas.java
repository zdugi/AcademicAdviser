package com.academic.adviser.drools.model;

import com.academic.adviser.model.CareerArea;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CareerAreas {

    private Set<CareerArea> careerAreas;
    private boolean filled;

    public CareerAreas() {
        filled = false;
        careerAreas = new HashSet<>();
    }

    public void addCareerArea(CareerArea careerArea) {
        careerAreas.add(careerArea);
        if(careerAreas.size() == 3) {
            filled = true;
        }
    }

    public void addAllAreas(List<CareerArea> careerAreaList) {
        careerAreas.addAll(careerAreaList);
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Set<CareerArea> getCareerAreas() {
        return careerAreas;
    }
}
