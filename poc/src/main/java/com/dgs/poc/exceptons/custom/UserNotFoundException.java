package com.dgs.poc.exceptons.custom;


public class UserNotFoundException extends RuntimeException {
    private static final String USER_NOT_FOUND = "User with id %d was not found";

    public UserNotFoundException(Long id) {
        super(String.format(USER_NOT_FOUND, id));
    }
}
