package com.academic.adviser.controller;

import com.academic.adviser.dto.LoginDTO;
import com.academic.adviser.dto.RegistrationDTO;
import com.academic.adviser.dto.UserTokenStateDTO;
import com.academic.adviser.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenStateDTO> login(
            @RequestBody @Validated LoginDTO loginDTO) {
        UserTokenStateDTO token = authService.loginUser(loginDTO);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/registration")
    public ResponseEntity<Void> registration(
            @RequestBody @Validated RegistrationDTO registrationDTO) {
        try {
            authService.registerCandidate(registrationDTO);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
