package org.com.splitwise.Exceptions.Validations;

import org.com.splitwise.Exceptions.Authentication.AuthenticationException;

public class UserNotFoundException extends AuthenticationException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
