package com.academic.adviser.model;

import com.academic.adviser.constants.Gender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Candidate implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String emailAddress;

    @Column(nullable = false)
    private String password;

    @Column
    private double grade;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    private BigFiveResults bigFiveResults;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AcademicLife academicLife;

    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;

    public Candidate() {}

    public Candidate(double grade) {
        this.grade = grade;
    }

    public Candidate(BigFiveResults bigFiveResults) {
        this.bigFiveResults = bigFiveResults;
    }

    public Candidate(String emailAddress, String password, double grade, String firstName, String lastName, Gender gender) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.grade = grade;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public void setBigFiveResults(BigFiveResults bigFiveResults) {
        this.bigFiveResults = bigFiveResults;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Gender getGender() {
        return gender;
    }

    public BigFiveResults getBigFiveResults() {
        return bigFiveResults;
    }

    public AcademicLife getAcademicLife() {
        return academicLife;
    }

    public void setAcademicLife(AcademicLife academicLife) {
        this.academicLife = academicLife;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(new Authority("ROLE_CANDIDATE"));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
