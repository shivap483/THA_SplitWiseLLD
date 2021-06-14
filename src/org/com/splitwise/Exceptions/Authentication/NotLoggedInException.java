package org.com.splitwise.Exceptions.Authentication;

public class NotLoggedInException extends AuthenticationException {
    public NotLoggedInException(String message) {
        super(message);
    }
}
