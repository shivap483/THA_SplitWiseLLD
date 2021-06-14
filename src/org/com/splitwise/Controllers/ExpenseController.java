package org.com.splitwise.Controllers;

import org.com.splitwise.Exceptions.Validations.InvalidGroupException;
import org.com.splitwise.Exceptions.Validations.UserNotFoundException;
import org.com.splitwise.Models.Expense;
import org.com.splitwise.Models.Group;
import org.com.splitwise.Models.User;
import org.com.splitwise.Repositories.ExpenseRepo;
import org.com.splitwise.Repositories.GroupRepo;
import org.com.splitwise.Repositories.UserRepo;
import org.com.splitwise.Services.Authentication.AuthenticationContext;
import org.com.splitwise.Services.Payments.PaymentStrategy;
import org.com.splitwise.Services.Split.SplitStrategy;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseController {
    ExpenseRepo expenseRepo;
    UserRepo userRepo;
    GroupRepo groupRepo;

    public Expense createExpenseWithOtherUsers(AuthenticationContext authenticationContext
            , List<Long> participantsIds
            , String description
            , PaymentStrategy paymentStrategy
            , SplitStrategy splitStrategy
            , Date date) {
        User user = authenticationContext
                .getCurrentlyLoggedInUser()
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        List<User> participants = participantsIds
                .stream()
                .map((id) -> userRepo
                        .findById(id)
                        .orElseThrow(() -> new UserNotFoundException(id
                                .toString())))
                .collect(Collectors.toList());
        participants.add(user);
        Expense expense = new Expense(description, date, participants);
        splitStrategy.calculateOwedAmount(expense);
        paymentStrategy.calculatePaidAmount(expense);
        expenseRepo.save(expense);
        return expense;
    }

    public Expense createExpenseWithGroup(AuthenticationContext authenticationContext
            , Long groupID
            , String description
            , PaymentStrategy paymentStrategy
            , SplitStrategy splitStrategy
            , Date date) {
        User user = authenticationContext
                .getCurrentlyLoggedInUser()
                .orElseThrow(() -> new UserNotFoundException("user not found"));

        Group group = groupRepo.findById(groupID).orElseThrow(() -> new InvalidGroupException("Group doesnot exist"));
        List<User> participants = group.getMembers();
        Expense expense = new Expense(description, date, participants);
        splitStrategy.calculateOwedAmount(expense);
        paymentStrategy.calculatePaidAmount(expense);
        expenseRepo.save(expense);
        return expense;
    }
}
