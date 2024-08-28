package com.dgsApp.poc.serviceLayer.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID uuid;
    private String name;
    private String email;
}
