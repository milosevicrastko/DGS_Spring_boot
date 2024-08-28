package com.dgsApp.poc.dataLayer.repositories;

import com.dgsApp.poc.dataLayer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
