package com.example.restapijpa.model.entity;

import com.example.restapijpa.model.dto.UsersMainInfoDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.time.LocalDate;

@Entity
@Table(name = "users_main_info",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username", "email"})
        },
        schema = "users_main_info_schema"
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersMainInfoEntity {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("second_name")
    private String secondName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("dob")
    private LocalDate dob;
    @JsonProperty("age")
    private Integer age;

    public UsersMainInfoEntity(Long id, String username, String firstName, String secondName, String email, LocalDate dob, Integer age) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    public UsersMainInfoEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public UsersMainInfoDTO toDTO() {
        UsersMainInfoDTO usersMainInfoDTO = new UsersMainInfoDTO();
        usersMainInfoDTO.setUsername(this.username);
        usersMainInfoDTO.setFirstName(this.firstName);
        usersMainInfoDTO.setSecondName(this.secondName);
        usersMainInfoDTO.setEmail(this.email);
        usersMainInfoDTO.setDob(this.dob);
        usersMainInfoDTO.setAge(this.age);
        return usersMainInfoDTO;
    }
}
