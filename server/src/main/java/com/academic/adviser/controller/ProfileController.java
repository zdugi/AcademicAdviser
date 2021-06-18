package com.academic.adviser.controller;

import com.academic.adviser.dto.ProfileDTO;
import com.academic.adviser.service.CityService;
import com.academic.adviser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/user")
public class ProfileController {

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    @GetMapping("/cities")
    public ResponseEntity<?> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCityNames());
    }

    @GetMapping
    public ResponseEntity<?> getProfile(Principal principal) {
        return ResponseEntity.ok(userService.getProfileData(principal.getName()));
    }

    @PostMapping
    public ResponseEntity<?> updateProfile(@RequestBody @Validated ProfileDTO profileDTO, Principal principal) {
        try {
            userService.updateProfile(profileDTO, principal.getName());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
