package com.example.restapijpa.controller;

import com.example.restapijpa.model.dto.UsersMainInfoDTO;
import com.example.restapijpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody UsersMainInfoDTO usersMainInfoDTO) {
        return userService.createUser(usersMainInfoDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @RequestBody @Valid UsersMainInfoDTO usersMainInfoDTO) {
        return userService.updateUser(id, usersMainInfoDTO);
    }
}
