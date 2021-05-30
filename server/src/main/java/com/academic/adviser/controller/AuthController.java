package com.academic.adviser.controller;

import com.academic.adviser.dto.LoginDTO;
import com.academic.adviser.dto.UserTokenStateDTO;
import com.academic.adviser.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenStateDTO> login(
            @RequestBody @Validated LoginDTO loginDTO, HttpServletResponse response) {
        UserTokenStateDTO token = authService.loginUser(loginDTO);
        return ResponseEntity.ok(token);
    }
}
