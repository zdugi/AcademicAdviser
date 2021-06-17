package com.academic.adviser.service.impl;

import com.academic.adviser.model.Candidate;
import com.academic.adviser.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Candidate candidate = candidateRepository.findByEmailAddress(username);

        if (candidate == null)
            throw new UsernameNotFoundException(String.format("No user found with email address '%s'.", username));

        return candidate;
    }
}
