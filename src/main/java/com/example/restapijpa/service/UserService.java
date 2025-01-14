package com.example.restapijpa.service;

import com.example.restapijpa.exception.UserAlreadyExistException;
import com.example.restapijpa.exception.UserNotFoundException;
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
        Optional<UsersMainInfoEntity> existingUser = userRepository.getUserMainInfoByUsername(usersMainInfoDTO.getUsername());
        if (!existingUser.isEmpty()) {
            throw new UserAlreadyExistException(String.format("Username '%s' already exists", usersMainInfoDTO.getUsername()));
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorJson(
//                    "UserCreateError", String.format("Username '%s' already exists", usersMainInfoDTO.getUsername())));
        }
        existingUser = userRepository.getUsersMainInfoByEmail(usersMainInfoDTO.getEmail());
        if (!existingUser.isEmpty()) {
            throw new UserAlreadyExistException(String.format("Email '%s' already exists", usersMainInfoDTO.getEmail()));
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorJson(
//                    "UserCreateError", String.format("Email '%s' already exists", usersMainInfoDTO.getEmail())));
        }

        userRepository.save(usersMainInfoDTO.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    public ResponseEntity<?> updateUser(Long id, UsersMainInfoDTO userPatchDTO) {
        final Optional<UsersMainInfoEntity> user = userRepository.getUsersMainInfoById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(id.toString());
        }
        final UsersMainInfoEntity userMainInfo = user.get();
        final UsersMainInfoEntity userForPatch = user.get();
        if (userPatchDTO.getUsername() != null) {
            if (!userPatchDTO.getUsername().equals(userMainInfo.getUsername())) {
                Optional<UsersMainInfoEntity> existUser =
                        userRepository.getUserMainInfoByUsername(userPatchDTO.getUsername());
                if (!existUser.isEmpty()) {
                    if (!existUser.get().getId().equals(id)) {
                        throw new UserAlreadyExistException(
                                String.format("Username '%s' already taken", userPatchDTO.getUsername()));
                    }
                }
            }
        }
        if (userPatchDTO.getEmail() != null) {
            if (!userPatchDTO.getEmail().equals(userMainInfo.getEmail())) {
                final Optional<UsersMainInfoEntity> existUser =
                        userRepository.getUsersMainInfoByEmail(userPatchDTO.getEmail());
                if (existUser.isEmpty()) {
                    if (!existUser.get().getId().equals(id)) {
                        throw new UserAlreadyExistException(
                                String.format("Email '%s' already taken", userPatchDTO.getEmail()));
                    }
                }
            }
        }
        if (userPatchDTO.getFirstName() != null) {
            userForPatch.setFirstName(userPatchDTO.getFirstName());
        }
        if (userPatchDTO.getSecondName() != null) {
            userForPatch.setSecondName(userPatchDTO.getSecondName());
        }
        if (userPatchDTO.getDob() != null) {
            userForPatch.setDob(userPatchDTO.getDob());
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                userRepository.updateUsersMainInfoById(id,
                        userForPatch.getUsername(),
                        userForPatch.getFirstName(),
                        userForPatch.getSecondName(),
                        userForPatch.getEmail(),
                        userForPatch.getDob(),
                        userForPatch.getAge())
        );
    }
}
