package com.example.restapijpa.repository;

import com.example.restapijpa.model.entity.UsersMainInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsersMainInfoEntity, Long> {

    Optional<UsersMainInfoEntity> findUsersMainInfoEntityById(Long id);

    Optional<UsersMainInfoEntity> findUsersMainInfoByUsername(String username);

    Optional<UsersMainInfoEntity> findUsersMainInfoByEmail(String email);

    @Modifying
    @Query("UPDATE UsersMainInfoEntity u SET " +
            "u.username = :username, " +
            "u.firstName = :firstName, " +
            "u.secondName = :secondName, " +
            "u.email = :email, " +
            "u.dob = :dob, " +
            "u.age = :age " +
            "WHERE u.id = :id")
    int updateUsersMainInfoById(
            @Param("id") Long id,
            @Param("username") String username,
            @Param("firstName") String firstName,
            @Param("secondName") String secondName,
            @Param("email") String email,
            @Param("dob") LocalDate dob,
            @Param("age") Integer age);
}
