package com.academic.adviser.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LoginDTO {
    @NotBlank(message = "Email address is mandatory.")
    private String emailAddress;

    @NotBlank(message = "Password is mandatory.")
    @Size(min=4, max=256, message = "Password should be between 8 and 256 characters.")
    private String password;

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}
