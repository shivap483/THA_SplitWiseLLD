package org.com.splitwise.Services.Payments;

import org.com.splitwise.Models.Expense;

public interface PaymentStrategy {
    void calculatePaidAmount(Expense expense);
}
