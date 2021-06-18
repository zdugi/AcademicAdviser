package com.academic.adviser.controller;

import com.academic.adviser.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/user")
public class ProfileController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<?> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCityNames());
    }
}
