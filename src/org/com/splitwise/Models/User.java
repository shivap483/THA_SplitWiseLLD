package org.com.splitwise.Models;

import org.com.splitwise.Exceptions.Validations.InvalidUserNameException;

import java.util.List;


public class User extends Auditable {
    private String fullName;
    private String userName;
    private String password;

    private String phoneNumber;

    private List<Expense> expenses;
    private List<Group> groups;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName.length() < 2) {
            throw new InvalidUserNameException("username should be minimum 5 characters");
        }
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Double getTotalOwed() {
        Double totalOwed = 0.0;
        for (Expense expense : this.expenses) {
            totalOwed += expense.getTotalAmount();
        }
        return totalOwed;
    }

}
