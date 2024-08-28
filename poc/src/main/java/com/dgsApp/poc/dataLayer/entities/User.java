package com.dgsApp.poc.dataLayer.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
}
