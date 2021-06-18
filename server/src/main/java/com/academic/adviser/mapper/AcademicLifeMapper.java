package com.academic.adviser.mapper;

import com.academic.adviser.dto.AcademicLifeDTO;
import com.academic.adviser.model.AcademicLife;
import com.academic.adviser.model.City;
import com.academic.adviser.model.Dormitory;
import com.academic.adviser.model.Major;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AcademicLifeMapper {
    public AcademicLifeDTO toDto(AcademicLife academicLife) {
        HashMap<String, List<String>> dorms = new HashMap<>();
        for(Dormitory dorm : academicLife.getDormitories()) {
            String city = dorm.getCity().getName();
            if(dorms.containsKey(city)) {
                dorms.get(city).add(dorm.getName());
            } else {
                dorms.put(city, new ArrayList<>() {{ add(dorm.getName()); }});
            }
        }
        return new AcademicLifeDTO(
                academicLife.getMajors().stream().map(Major::getName).collect(Collectors.toList()),
                dorms,
                academicLife.getLifeCost().stream().collect(
                        Collectors.toMap(City::getName, city -> new HashMap<String, Double>() {{
                            put("lifeCost", city.getLifeCost());
                            put("rent", city.getRent());
                        }}))
        );
    }
}
