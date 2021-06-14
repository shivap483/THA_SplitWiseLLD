package org.com.splitwise.Exceptions.Authentication;


public class PasswordMismatchException extends AuthenticationException {
    public PasswordMismatchException(String message) {
        super(message);
    }
}
