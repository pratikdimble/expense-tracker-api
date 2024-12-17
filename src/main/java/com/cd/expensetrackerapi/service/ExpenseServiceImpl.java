package com.cd.expensetrackerapi.service;



import com.cd.expensetrackerapi.model.ExpenseMaster;
import com.cd.expensetrackerapi.repository.ExpenseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ExpenseServiceImpl implements IExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Override
    public List<ExpenseMaster> getExpenses(Long userId) {
        return expenseRepository.findAllByUserIdAndIsDeleted(userId,false);
    }

    @Override
    public List<ExpenseMaster> getDeletedExpenses(Long userId) {
        return expenseRepository.findAllByUserIdAndIsDeleted(userId,true);
    }

    @Override
    public ExpenseMaster addExpenses(ExpenseMaster expenseMaster) {
        expenseMaster.setCreatedDate(LocalDate.now());
        expenseMaster.setUpdatedDate(LocalDate.now());
        expenseMaster.setDeleted(false);
        return expenseRepository.save(expenseMaster);
    }

    @Override
    public ExpenseMaster updateExpenses(ExpenseMaster expenseMaster) {
//        expenseMaster.setUpdatedDate(LocalDate.now());
        expenseRepository.save(expenseMaster);
        return expenseMaster;
    }

    @Override
    public boolean deleteExpenses(Long expenseId) {
        ExpenseMaster expenseMaster = (ExpenseMaster) expenseRepository.findExpenseMasterByExpenseId(expenseId).get(0);
        expenseMaster.setUpdatedDate(LocalDate.now());
        expenseMaster.setDeleted(true);
        expenseRepository.save(expenseMaster);
        return true;
    }
}
