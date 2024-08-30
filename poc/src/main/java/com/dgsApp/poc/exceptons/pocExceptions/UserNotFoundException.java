package com.dgsApp.poc.exceptons.pocExceptions;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    private static final String USER_NOT_FOUND = "User with id %d was not found";

    public UserNotFoundException(Long id) {
        super(String.format(USER_NOT_FOUND, id));
    }
}
