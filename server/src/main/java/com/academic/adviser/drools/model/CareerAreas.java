package com.academic.adviser.drools.model;

import com.academic.adviser.model.CareerArea;

import java.util.ArrayList;
import java.util.List;

public class CareerAreas {

    private List<CareerArea> careerAreas;
    private boolean filled;

    public CareerAreas() {
        filled = false;
        careerAreas = new ArrayList<>();
    }

    public void addCareerArea(CareerArea careerArea) {
        careerAreas.add(careerArea);
        if(careerAreas.size() == 3) {
            filled = true;
        }
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public List<CareerArea> getCareerAreas() {
        return careerAreas;
    }
}
