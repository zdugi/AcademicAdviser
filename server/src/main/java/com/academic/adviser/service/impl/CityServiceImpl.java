package com.academic.adviser.service.impl;

import com.academic.adviser.model.City;
import com.academic.adviser.repository.CityRepository;
import com.academic.adviser.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Map<Integer, String> getAllCityNames() {
        return cityRepository.findAll().stream().collect(Collectors.toMap(City::getId, City::getName));
    }
}
