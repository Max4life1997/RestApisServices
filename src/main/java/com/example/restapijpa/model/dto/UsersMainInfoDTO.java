package com.example.restapijpa.model.dto;


import com.example.restapijpa.model.entity.UsersMainInfoEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;


public class UsersMainInfoDTO {
    @NotNull(message = "username cannot be null")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;
    @NotNull(message = "firstName cannot be null")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    private String firstName;

    @NotNull(message = "secondName cannot be null")
    @Size(max = 30, message = "Second name must not exceed 30 characters")
    private String secondName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @NotNull(message = "Age cannot be null")
    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 100, message = "Age must be less than 100")
    private Integer age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UsersMainInfoDTO() {
    }

    public UsersMainInfoDTO(String username, String firstName, String secondName, String email, LocalDate dob, Integer age) {
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    public UsersMainInfoEntity toEntity() {
        UsersMainInfoEntity user = new UsersMainInfoEntity();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);
        user.setDob(dob);
        user.setAge(age);
        return user;
    }
}
