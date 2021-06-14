package org.com.splitwise.Services.Split;

import org.com.splitwise.Models.Expense;

public interface SplitStrategy {
    void calculateOwedAmount(Expense expense);
}


