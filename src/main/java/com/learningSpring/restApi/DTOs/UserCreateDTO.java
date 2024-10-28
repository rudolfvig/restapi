package com.learningSpring.restApi.DTOs;

import lombok.Data;

@Data
public class UserCreateDTO {

    private String name;
    private String email;
    private String password;
    private int age;
}
