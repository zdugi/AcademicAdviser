package com.academic.adviser.dto;

import com.academic.adviser.constants.Gender;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RegistrationDTO {
    private Integer id;

    @NotBlank(message = "Username is mandatory.")
    private String emailAddress;

    @NotBlank(message = "Password is mandatory.")
    @Size(min=4, max=256, message = "Password should be between 4 and 256 characters.")
    private String password;

    @NotBlank(message = "First name is mandatory.")
    private String firstName;

    @NotBlank(message = "Last name is mandatory.")
    private String lastName;

    @DecimalMax("5.0") @DecimalMin("1.0")
    private double grade;

    private Gender gender;

    public Integer getId() {
        return id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getGrade() {
        return grade;
    }

    public Gender getGender() {
        return gender;
    }
}
