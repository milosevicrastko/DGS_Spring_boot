package com.dgsApp.poc.serviceLayer.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
}
