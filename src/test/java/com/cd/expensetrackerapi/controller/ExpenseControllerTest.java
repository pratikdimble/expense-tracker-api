package com.cd.expensetrackerapi.controller;

import com.cd.expensetrackerapi.model.ExpenseMaster;
import com.cd.expensetrackerapi.service.IExpenseService;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExpenseControllerTest {

    @Mock
    private IExpenseService iExpenseService;

    @InjectMocks
    private ExpenseController expenseController;

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
    public void testHealthCheck(){
        ResponseEntity<String> response = expenseController.healthCheck();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("App is up and running","App is up and running");
    }
    @Test
    public void testGetExpenses(){
        List<ExpenseMaster> mockExpenseMasters = Collections.singletonList(testExpenseMaster);
        when(iExpenseService.getExpenses(1L)).thenReturn(mockExpenseMasters);

        ResponseEntity<List<ExpenseMaster>> response = expenseController.getExpenses(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(testExpenseMaster, response.getBody().get(0));
        verify(iExpenseService).getExpenses(1L);
    }

    @Test
    public void testGetDeletedExpenses(){
        testExpenseMaster.setDeleted(true);
        List<ExpenseMaster> mockExpenseMasters = Collections.singletonList(testExpenseMaster);
        when(iExpenseService.getDeletedExpenses(1L)).thenReturn(mockExpenseMasters);

        ResponseEntity<List<ExpenseMaster>> response = expenseController.getDeletedExpenses(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(testExpenseMaster, response.getBody().get(0));
        verify(iExpenseService).getDeletedExpenses(1L);
    }

    @Test
    public void testAddExpense() throws URISyntaxException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        when(iExpenseService.addExpenses(any(ExpenseMaster.class))).thenReturn(testExpenseMaster);
        ResponseEntity<ExpenseMaster> response = expenseController.addExpenses(testExpenseMaster);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testExpenseMaster, response.getBody());
        verify(iExpenseService).addExpenses(testExpenseMaster);
    }

    @Test
    public void testUpdateExpense(){
        when(iExpenseService.updateExpenses(testExpenseMaster)).thenReturn(testExpenseMaster);

        ExpenseMaster updatedExpenses = expenseController.updateExpenses(1L, testExpenseMaster);

        assertEquals(testExpenseMaster, updatedExpenses);
        verify(iExpenseService).updateExpenses(testExpenseMaster);
    }


    @Test
    public void testDeleteExpenseSuccess(){
        when(iExpenseService.deleteExpenses(1L)).thenReturn(true);

        ResponseEntity<?> response = expenseController.deleteExpenses(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(iExpenseService).deleteExpenses(1L);
    }

    @Test
    public void testDeleteExpenseNotFound(){
        when(iExpenseService.deleteExpenses(2L)).thenReturn(false);

        ResponseEntity<?> response = expenseController.deleteExpenses(2L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(iExpenseService).deleteExpenses(2L);
    }


}