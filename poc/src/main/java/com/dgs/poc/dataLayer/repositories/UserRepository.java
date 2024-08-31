package com.dgs.poc.dataLayer.repositories;

import com.dgs.poc.dataLayer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
