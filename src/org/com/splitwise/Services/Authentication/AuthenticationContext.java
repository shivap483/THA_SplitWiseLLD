package org.com.splitwise.Services.Authentication;

import org.com.splitwise.Models.User;

import java.util.Optional;

public interface AuthenticationContext {
    Optional<User> getCurrentlyLoggedInUser();

    Boolean isAuthenticated();
}
