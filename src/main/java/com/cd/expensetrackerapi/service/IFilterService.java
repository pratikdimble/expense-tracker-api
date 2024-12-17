package com.cd.expensetrackerapi.service;

import com.cd.expensetrackerapi.model.ExpenseMaster;
import com.cd.expensetrackerapi.model.SummaryDto;

import java.time.LocalDate;
import java.util.List;

public interface IFilterService {

    List<ExpenseMaster> filterByType(Long userId, String category, String keyword);
    List<ExpenseMaster> filterByDateRange(Long userId, LocalDate fromDate, LocalDate toDate);
    List<ExpenseMaster> filterByDateRangeAndCategory(Long userId, LocalDate fromDate, LocalDate toDate,String category);
    List<ExpenseMaster> filterByDateRangeAndCategoryAndKeyword(Long userId, LocalDate fromDate, LocalDate toDate,String category, String keyword);




    List<SummaryDto> getTotalExpensesForCurrentMonth(Long userId);

}
