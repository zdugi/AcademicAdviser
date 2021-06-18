package com.academic.adviser.service;

import com.academic.adviser.dto.ProfileDTO;

import java.sql.SQLException;

public interface UserService {
    ProfileDTO getProfileData(String emailAddress);

    void updateProfile(ProfileDTO profileDTO, String emailAddress) throws SQLException;
}
