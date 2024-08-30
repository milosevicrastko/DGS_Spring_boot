package com.dgsApp.poc.apiLayer.inputs;

import lombok.Data;
import lombok.Value;

@Data
public class CreateUserInput {
    String name;
    String email;
}
