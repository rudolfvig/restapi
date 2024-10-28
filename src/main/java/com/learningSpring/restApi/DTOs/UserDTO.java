package com.learningSpring.restApi.DTOs;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private int age;
    private int level;
}
