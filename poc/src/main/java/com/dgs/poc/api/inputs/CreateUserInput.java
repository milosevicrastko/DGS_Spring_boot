package com.dgs.poc.api.inputs;

import lombok.Data;

@Data
public class CreateUserInput {
    private String name;
    private String email;
}
