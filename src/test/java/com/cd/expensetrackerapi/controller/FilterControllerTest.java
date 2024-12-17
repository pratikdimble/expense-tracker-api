package com.cd.expensetrackerapi.controller;

import com.cd.expensetrackerapi.model.ExpenseMaster;
import com.cd.expensetrackerapi.model.RequestDto;
import com.cd.expensetrackerapi.service.IExpenseService;
import com.cd.expensetrackerapi.service.IFilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FilterControllerTest {

    @Mock
    private IFilterService iFilterService;

    @InjectMocks
    private FilterController filterController;

    private ExpenseMaster testExpenseMaster;
//    private Long userId;

    @BeforeEach
    public void setup() {
        System.out.println(" setup called...");
//        userId = 1L;
        testExpenseMaster = new ExpenseMaster();
        testExpenseMaster.setTitle("Movies");testExpenseMaster.setAmount(950d);testExpenseMaster.setUserId(1l);testExpenseMaster.setCategory("Entertainment");
        testExpenseMaster.setDeleted(false);testExpenseMaster.setCreatedDate(LocalDate.of(2024,12,10));testExpenseMaster.setUpdatedDate(LocalDate.of(2024,12,10));
    }


    @Test
    public void testFilterExpensesByDateRangeAndCategoryAndKeyword(){
        List<ExpenseMaster> mockExpenseMasters = Collections.singletonList(testExpenseMaster);
        RequestDto requestDto = new RequestDto();
        requestDto.setUserId(1L);requestDto.setCategory("Entertainment");requestDto.setFromDate(LocalDate.of(2024,12,10));
        requestDto.setToDate(LocalDate.of(2024,12,10));requestDto.setKeyword("es");

        when(iFilterService.filterByDateRangeAndCategoryAndKeyword(requestDto)).thenReturn(mockExpenseMasters);

        ResponseEntity<List<ExpenseMaster>> response = filterController.filterExpenses(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(testExpenseMaster, response.getBody().get(0));
        verify(iFilterService).filterByDateRangeAndCategoryAndKeyword(requestDto);
    }

    @Test
    public void testFilterExpensesByDateRangeAndCategory(){
        List<ExpenseMaster> mockExpenseMasters = Collections.singletonList(testExpenseMaster);
        RequestDto requestDto = new RequestDto();
        requestDto.setUserId(1L);requestDto.setCategory("Entertainment");requestDto.setFromDate(LocalDate.of(2024,12,10));
        requestDto.setToDate(LocalDate.of(2024,12,10));

        when(iFilterService.filterByDateRangeAndCategory(requestDto)).thenReturn(mockExpenseMasters);

        ResponseEntity<List<ExpenseMaster>> response = filterController.filterExpenses(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(testExpenseMaster, response.getBody().get(0));
        verify(iFilterService).filterByDateRangeAndCategory(requestDto);
    }

    @Test
    public void testFilterExpensesByDateRange(){
        List<ExpenseMaster> mockExpenseMasters = Collections.singletonList(testExpenseMaster);
        RequestDto requestDto = new RequestDto();
        requestDto.setUserId(1L);requestDto.setFromDate(LocalDate.of(2024,12,10));
        requestDto.setToDate(LocalDate.of(2024,12,10));

        when(iFilterService.filterByDateRange(requestDto)).thenReturn(mockExpenseMasters);

        ResponseEntity<List<ExpenseMaster>> response = filterController.filterExpenses(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(testExpenseMaster, response.getBody().get(0));
        verify(iFilterService).filterByDateRange(requestDto);
    }



}