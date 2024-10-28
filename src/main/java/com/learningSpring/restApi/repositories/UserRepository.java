package com.learningSpring.restApi.repositories;

import com.learningSpring.restApi.DTOs.UserDTO;
import com.learningSpring.restApi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    void deleteByName(String name);

    Optional<User> findByName(String name);
}
