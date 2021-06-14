package org.com.splitwise.Repositories;

import org.com.splitwise.Models.User;

import java.util.Optional;

public interface UserRepo extends IRepo<User, Long> {
    public Optional<User> findByuserName(String userName);
}
