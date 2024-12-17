package com.cd.expensetrackerapi.service;

import com.cd.expensetrackerapi.model.ExpenseMaster;
import com.cd.expensetrackerapi.model.RequestDto;
import com.cd.expensetrackerapi.model.SummaryDto;
import com.cd.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FilterServiceImpl implements IFilterService {
    @Autowired
    ExpenseRepository expenseRepository;


    @Override
    public List<ExpenseMaster> filterByType(RequestDto requestDto) {
        return expenseRepository.findAllByCategoryAndIsDeletedAndTitleContainingIgnoreCase(requestDto.getUserId(), requestDto.getCategory(),false, requestDto.getKeyword().toLowerCase());
    }

    @Override
    public List<ExpenseMaster> filterByDateRange(RequestDto requestDto) {
        return expenseRepository.findAllByUserIdAndUpdatedDateBetween(requestDto.getUserId(), requestDto.getFromDate(), requestDto.getToDate());
    }

    @Override
    public List<ExpenseMaster> filterByDateRangeAndCategory(RequestDto requestDto) {
        return expenseRepository.findAllByUserIdAndUpdatedDateBetweenAndCategory(requestDto.getFromDate(), requestDto.getToDate(), requestDto.getCategory(),requestDto.getUserId());
    }

    @Override
    public List<ExpenseMaster> filterByDateRangeAndCategoryAndKeyword(RequestDto requestDto) {
        return expenseRepository.findAllByUserIdAndUpdatedDateBetweenAndCategoryAndTitleContainingIgnoreCase(requestDto.getUserId(), requestDto.getFromDate(), requestDto.getToDate(), requestDto.getCategory(),requestDto.getKeyword().toLowerCase());
    }

    @Override
    public List<SummaryDto> getTotalExpensesForCurrentMonth(Long userId) {
        List<Object[]> monthlySummary = expenseRepository.getMonthlyExpenseSummary(userId);
        List<SummaryDto> summaryDtos = new ArrayList<>();
        for (Object[] row : monthlySummary) {
            SummaryDto summaryDto = new SummaryDto();
            summaryDto.setMonthSpan((String) row[0]);
            summaryDto.setTotalExpenses((Double) row[1]);
             summaryDtos.add(summaryDto);
/*            String month = (String) row[0];
            Double totalExpense = (Double) row[1];
            System.out.println(month + ": Amount " + totalExpense);*/
        }
        return summaryDtos;
//        return  expenseRepository.getTotalExpensesForCurrentMonth(userId, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
    }
}
