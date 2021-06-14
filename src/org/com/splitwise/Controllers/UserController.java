package org.com.splitwise.Controllers;

import org.com.splitwise.DTOs.UserDTO;
import org.com.splitwise.Exceptions.Authentication.NotLoggedInException;
import org.com.splitwise.Exceptions.Authentication.PasswordMismatchException;
import org.com.splitwise.Exceptions.Validations.DuplicateUserNameException;
import org.com.splitwise.Models.Expense;
import org.com.splitwise.Models.Group;
import org.com.splitwise.Models.User;
import org.com.splitwise.Repositories.UserRepo;
import org.com.splitwise.Services.Authentication.AuthenticationContext;
import org.com.splitwise.Services.Authentication.PasswrodEncoder;

import java.util.List;

public class UserController {
    UserRepo userRepo;
    PasswrodEncoder passwrodEncoder = null;


    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void setPasswordEncoder(PasswrodEncoder passwrodEncoder) {
        this.passwrodEncoder = passwrodEncoder;
    }

    public User register(UserDTO userDTO) {
        User user = new User();
        if (userRepo.findByuserName(userDTO.userName).isPresent()) {
            throw new DuplicateUserNameException("user already exist with this username");
        }
        user.setUserName(userDTO.userName);
        user.setFullName(userDTO.fullName);
        user.setPhoneNumber(userDTO.phoneNumber);
        String hashedPassword = passwrodEncoder.encode(userDTO.password, userDTO.userName);
        user.setPassword(hashedPassword);
        userRepo.create(user);
        return user;
    }

    public void changePassword(AuthenticationContext authenticationContext, String oldPassword, String newPassword) {
        User loggedInUser = authenticationContext
                .getCurrentlyLoggedInUser()
                .orElseThrow(() -> new NotLoggedInException("login to change password"));
        if (!loggedInUser.getPassword().equals(passwrodEncoder.encode(oldPassword, loggedInUser.getUserName()))) {
            throw new PasswordMismatchException("");
        }
        loggedInUser.setPassword(passwrodEncoder.encode(newPassword, loggedInUser.getUserName()));
        userRepo.save(loggedInUser);
    }

    public void updateProfile(AuthenticationContext authenticationContext, UserDTO userDTO, String password) {
        User loggedInUser = authenticationContext
                .getCurrentlyLoggedInUser()
                .orElseThrow(() -> new NotLoggedInException("login to change password"));
        if (!loggedInUser.getPassword().equals(passwrodEncoder.encode(password, loggedInUser.getUserName()))) {
            throw new PasswordMismatchException("");
        }
        loggedInUser.setFullName(userDTO.fullName);
        loggedInUser.setPhoneNumber(userDTO.phoneNumber);
        userRepo.save(loggedInUser);
    }

    public Double getMyTotalOwed(AuthenticationContext authenticationContext) {
        User loggedInUser = authenticationContext
                .getCurrentlyLoggedInUser()
                .orElseThrow(() -> new NotLoggedInException("login to change password"));
        return loggedInUser.getTotalOwed();
    }

    public List<Expense> getMyExpenseHistory(AuthenticationContext authenticationContext) {
        User loggedInUser = authenticationContext
                .getCurrentlyLoggedInUser()
                .orElseThrow(() -> new NotLoggedInException("login to change password"));
        return loggedInUser.getExpenses();
    }

    public List<Group> getMyGroups(AuthenticationContext authenticationContext) {
        User loggedInUser = authenticationContext
                .getCurrentlyLoggedInUser()
                .orElseThrow(() -> new NotLoggedInException("login to change password"));
        return loggedInUser.getGroups();
    }
}
