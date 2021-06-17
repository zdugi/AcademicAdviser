package com.academic.adviser.service;

import com.academic.adviser.dto.LoginDTO;
import com.academic.adviser.dto.RegistrationDTO;
import com.academic.adviser.dto.UserTokenStateDTO;

import java.sql.SQLException;

public interface AuthService {
    UserTokenStateDTO loginUser(LoginDTO credentials);

    void registerCandidate(RegistrationDTO registrationDTO) throws SQLException;
}
