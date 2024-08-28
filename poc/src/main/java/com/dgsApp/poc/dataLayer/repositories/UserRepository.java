package com.dgsApp.poc.dataLayer.repositories;

import com.dgsApp.poc.dataLayer.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
