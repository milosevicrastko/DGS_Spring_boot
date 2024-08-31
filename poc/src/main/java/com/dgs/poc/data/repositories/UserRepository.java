package com.dgs.poc.data.repositories;

import com.dgs.poc.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
