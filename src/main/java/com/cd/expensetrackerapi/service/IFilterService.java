package com.cd.expensetrackerapi.service;

import com.cd.expensetrackerapi.model.ExpenseMaster;
import com.cd.expensetrackerapi.model.RequestDto;
import com.cd.expensetrackerapi.model.SummaryDto;

import java.time.LocalDate;
import java.util.List;

public interface IFilterService {

    List<ExpenseMaster> filterByType(RequestDto requestDto);
    List<ExpenseMaster> filterByDateRange(RequestDto requestDto);
    List<ExpenseMaster> filterByDateRangeAndCategory(RequestDto requestDto);
    List<ExpenseMaster> filterByDateRangeAndCategoryAndKeyword(RequestDto requestDto);




    List<SummaryDto> getTotalExpensesForCurrentMonth(Long userId);

}
