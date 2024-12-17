package com.cd.expensetrackerapi.service;


import com.cd.expensetrackerapi.model.ExpenseMaster;

import java.util.List;

public interface IExpenseService {

    List<ExpenseMaster> getExpenses(Long userId);
    List<ExpenseMaster> getDeletedExpenses(Long userId);
//    List<ExpenseMaster> getExpensesByUser(Long userId);
    ExpenseMaster addExpenses(ExpenseMaster expenseMaster);
    ExpenseMaster updateExpenses(ExpenseMaster expenseMaster);
    boolean deleteExpenses(Long expenseId);

}
