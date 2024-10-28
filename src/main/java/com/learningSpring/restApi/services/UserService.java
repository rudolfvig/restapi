package com.learningSpring.restApi.services;

import com.learningSpring.restApi.DTOs.UserCreateDTO;
import com.learningSpring.restApi.DTOs.UserDTO;
import com.learningSpring.restApi.entities.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

    public List<UserDTO> getAllUsers();
    public Optional<UserDTO> getUserById(Long id);
    public Optional<UserDTO> getUserByName(String name);
    public Optional<UserDTO> getUserByEmail(String email);
    public void deleteUserById(Long id);
    public void deleteUserByName(String name);
    public UserDTO createUser(UserCreateDTO user);
    public UserDTO updateUser(Long id, UserCreateDTO user);
    public UserDTO updateUserLevel(Long id, int deltaValue);
}
