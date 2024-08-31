package com.dgs.poc.api.inputs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserInput {

    private String name;

    @NotBlank(message = "Email must not be blank")
    private String email;
}
