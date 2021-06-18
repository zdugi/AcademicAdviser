package com.academic.adviser.service.impl;

import com.academic.adviser.dto.ProfileDTO;
import com.academic.adviser.mapper.ProfileMapper;
import com.academic.adviser.model.Candidate;
import com.academic.adviser.repository.CandidateRepository;
import com.academic.adviser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLException;

public class UserDetailsServiceImpl implements UserDetailsService, UserService {
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

    @Override
    public ProfileDTO getProfileData(String emailAddress) {
        return new ProfileMapper().toDto(candidateRepository.findByEmailAddress(emailAddress));
    }

    @Override
    public void updateProfile(ProfileDTO profileDTO, String emailAddress) throws SQLException {
        if(candidateRepository.findByEmailAddress(profileDTO.getEmailAddress()) != null &&
            !profileDTO.getEmailAddress().equals(emailAddress)) {
            throw new SQLException("Already registered email address.");
        }

        Candidate candidate = candidateRepository.findByEmailAddress(emailAddress);
        candidate.setEmailAddress(profileDTO.getEmailAddress());
        candidate.setFirstName(profileDTO.getFirstName());
        candidate.setLastName(profileDTO.getLastName());
        candidate.setGrade(profileDTO.getGrade());
        candidateRepository.save(candidate);
    }
}
