package com.cd.expensetrackerapi.controller.advices;




import com.cd.expensetrackerapi.exceptions.UserNotFoundException;
import com.cd.expensetrackerapi.utils.ExpenseErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExpenseControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ResponseEntity handleException(UserNotFoundException ex){
        ExpenseErrorResponse errorResponse = new ExpenseErrorResponse();
        errorResponse.setErrorCode("Expense-404");
        errorResponse.setErrorMessage("Invalid Username or Password  !!");
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ResponseEntity handleException(MethodArgumentNotValidException ex){
        ExpenseErrorResponse errorResponse = new ExpenseErrorResponse();
        errorResponse.setErrorCode("Expense-400");
        errorResponse.setErrorMessage("Expense is Not Valid!");
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setFieldErrors(ex.getFieldErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ResponseEntity handleException(Exception ex){
        ExpenseErrorResponse errorResponse = new ExpenseErrorResponse();
        errorResponse.setErrorCode("Expense-500");
        errorResponse.setErrorMessage("An Server Exception occurred!");
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
