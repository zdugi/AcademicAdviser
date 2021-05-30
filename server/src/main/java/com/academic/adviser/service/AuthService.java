package com.academic.adviser.service;

import com.academic.adviser.dto.LoginDTO;
import com.academic.adviser.dto.UserTokenStateDTO;

public interface AuthService {
    UserTokenStateDTO loginUser(LoginDTO credentials);
}
