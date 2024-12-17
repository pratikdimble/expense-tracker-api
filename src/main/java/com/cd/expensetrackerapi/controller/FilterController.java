package com.cd.expensetrackerapi.controller;

import com.cd.expensetrackerapi.model.ExpenseMaster;
import com.cd.expensetrackerapi.model.RequestDto;
import com.cd.expensetrackerapi.model.SummaryDto;
import com.cd.expensetrackerapi.service.IFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
@CrossOrigin
public class FilterController {

    @Autowired
    IFilterService filterService;

    @GetMapping("/filter/{userId}/{category}/{keyword}")
    public ResponseEntity<List<ExpenseMaster>> getExpenses(@PathVariable Long userId, @PathVariable String category, @PathVariable String keyword)
    {
        return ResponseEntity.ok(filterService.filterByType(userId, category, keyword));
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ExpenseMaster>>  getExpenses(@RequestBody RequestDto requestDto)
    {
        if(requestDto.getCategory() != null && !requestDto.getKeyword().isEmpty())
            return ResponseEntity.ok(filterService.filterByDateRangeAndCategoryAndKeyword(requestDto.getUserId(), requestDto.getFromDate(),requestDto.getToDate(), requestDto.getCategory(), requestDto.getKeyword()));
        else if (requestDto.getCategory() != null && requestDto.getKeyword().isEmpty())
            return ResponseEntity.ok(filterService.filterByDateRangeAndCategory(requestDto.getUserId(), requestDto.getFromDate(),requestDto.getToDate(), requestDto.getCategory()));
        else
            return ResponseEntity.ok(filterService.filterByDateRange(requestDto.getUserId(), requestDto.getFromDate(),requestDto.getToDate()));
    }

    @GetMapping("/summary/{userId}")
    public ResponseEntity<List<SummaryDto>> getExpenses(@PathVariable Long userId)
    {
        return ResponseEntity.ok(filterService.getTotalExpensesForCurrentMonth(userId));
    }
}
