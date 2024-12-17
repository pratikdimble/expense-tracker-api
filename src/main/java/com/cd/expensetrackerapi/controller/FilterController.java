package com.cd.expensetrackerapi.controller;

import com.cd.expensetrackerapi.model.ExpenseMaster;
import com.cd.expensetrackerapi.model.RequestDto;
import com.cd.expensetrackerapi.model.SummaryDto;
import com.cd.expensetrackerapi.service.IFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expense")
@CrossOrigin
public class FilterController {

    @Autowired
    IFilterService filterService;

    /*@GetMapping("/filter/{userId}/{category}/{keyword}")
    public ResponseEntity<List<ExpenseMaster>> getExpenses(@PathVariable Long userId, @PathVariable String category, @PathVariable String keyword)
    {
        return ResponseEntity.ok(filterService.filterByType(userId, category, keyword));
    }*/

    @PostMapping("/filter")
    public ResponseEntity<List<ExpenseMaster>>  filterExpenses(@RequestBody RequestDto requestDto)
    {
        Optional<String> keyword= Optional.ofNullable(requestDto.getKeyword());
        Optional<String> category= Optional.ofNullable(requestDto.getCategory());
        if(category.isPresent() && keyword.isPresent())
            return ResponseEntity.ok(filterService.filterByDateRangeAndCategoryAndKeyword(requestDto));
        else if (category.isPresent() && !keyword.isPresent())
            return ResponseEntity.ok(filterService.filterByDateRangeAndCategory(requestDto));
        else
            return ResponseEntity.ok(filterService.filterByDateRange(requestDto));
    }

    @GetMapping("/summary/{userId}")
    public ResponseEntity<List<SummaryDto>> getExpenses(@PathVariable Long userId)
    {
        return ResponseEntity.ok(filterService.getTotalExpensesForCurrentMonth(userId));
    }
}
