package org.com.splitwise.DTOs;

import org.com.splitwise.Models.Expense;
import org.com.splitwise.Models.User;

import java.util.List;

public class GroupDTO {
    public String name;
    public User admin;
    public List<User> members;
    public List<Expense> expenses;
}
