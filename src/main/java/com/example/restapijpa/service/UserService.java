package com.example.restapijpa.service;

import com.example.restapijpa.model.ErrorJson;
import com.example.restapijpa.model.dto.UsersMainInfoDTO;
import com.example.restapijpa.model.entity.UsersMainInfoEntity;
import com.example.restapijpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<UsersMainInfoEntity>> getUsers() {
        List<UsersMainInfoEntity> usersMainInfoEntities = userRepository.findAll();
        if (usersMainInfoEntities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(usersMainInfoEntities);
    }

    public ResponseEntity<?> createUser(UsersMainInfoDTO usersMainInfoDTO) {
        Optional<UsersMainInfoEntity> existingUser = userRepository.findUsersMainInfoByUsername(usersMainInfoDTO.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorJson(
                    "UserCreateError", String.format("Username '%s' already exists", usersMainInfoDTO.getUsername())));
        }

        existingUser = userRepository.findUsersMainInfoByEmail(usersMainInfoDTO.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorJson(
                    "UserCreateError", String.format("Email '%s' already exists", usersMainInfoDTO.getEmail())));
        }

        userRepository.save(usersMainInfoDTO.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    public ResponseEntity<?> updateUser(Long id, UsersMainInfoDTO usersMainInfoDTO) {
        Optional<UsersMainInfoEntity> userById = userRepository.findUsersMainInfoEntityById(id);
        if (userById.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorJson(
                    "User update error", String.format("By username %s",
                    usersMainInfoDTO.getUsername())));
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                userRepository.updateUsersMainInfoById(id,
                        usersMainInfoDTO.getUsername(),
                        usersMainInfoDTO.getFirstName(),
                        usersMainInfoDTO.getSecondName(),
                        usersMainInfoDTO.getEmail(),
                        usersMainInfoDTO.getDob(),
                        usersMainInfoDTO.getAge())
        );
    }
}
