package com.academic.adviser.service.impl;

import com.academic.adviser.dto.LoginDTO;
import com.academic.adviser.dto.UserTokenStateDTO;
import com.academic.adviser.model.Candidate;
import com.academic.adviser.security.TokenUtils;
import com.academic.adviser.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public UserTokenStateDTO loginUser(LoginDTO credentials) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(),
                        credentials.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Candidate user = (Candidate) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiredIn = tokenUtils.getExpiredIn();

        return new UserTokenStateDTO(jwt, expiredIn);
    }
}
