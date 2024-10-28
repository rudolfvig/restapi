package com.learningSpring.restApi.services;

import com.learningSpring.restApi.DTOs.UserCreateDTO;
import com.learningSpring.restApi.DTOs.UserDTO;
import com.learningSpring.restApi.entities.User;
import com.learningSpring.restApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertUserToUserDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertUserToUserDTO);
    }

    @Override
    public Optional<UserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(this::convertUserToUserDTO);
    }

    @Override
    public Optional<UserDTO> getUserByName(String name) {
        return userRepository.findByName(name).map(this::convertUserToUserDTO);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteUserByName(String name) {
        userRepository.deleteByName(name);
    }

    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        User user = convertUserCreateDTOToUser(userCreateDTO);
        User savedUser = userRepository.save(user);
        return convertUserToUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserCreateDTO userCreateDTO) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userCreateDTO.getName());
                    user.setEmail(userCreateDTO.getEmail());
                    user.setPassword(userCreateDTO.getPassword());
                    user.setAge(userCreateDTO.getAge());
                    User updatedUser = userRepository.save(user);
                    return convertUserToUserDTO(updatedUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDTO updateUserLevel(Long id, int deltaValue) {
        return userRepository.findById(id)
                .map(user -> {
                    // Update the user's level
                    int newLevel = user.getLevel() + deltaValue;
                    user.setLevel(newLevel);
                    User updatedUser = userRepository.save(user);
                    return convertUserToUserDTO(updatedUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Utility method to convert User entity to UserDTO
    private UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAge(user.getAge());
        userDTO.setLevel(user.getLevel());
        return userDTO;
    }

    private User convertUserCreateDTOToUser(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setName(userCreateDTO.getName());
        user.setEmail(userCreateDTO.getEmail());
        user.setAge(userCreateDTO.getAge());
        user.setPassword(userCreateDTO.getPassword());
        user.setLevel(0);
        return user;
    }
}
