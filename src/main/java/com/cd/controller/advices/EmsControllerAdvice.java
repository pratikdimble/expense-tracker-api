package com.cd.controller.advices;



import com.cd.exceptions.EmployeeIDMismatchException;
import com.cd.exceptions.EmployeeNotFoundException;
import com.cd.utils.EmsErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class EmsControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ResponseEntity handleException(EmployeeNotFoundException ex){
        EmsErrorResponse EmsErrorResponse = new EmsErrorResponse();
        EmsErrorResponse.setErrorCode("EMS-404");
        EmsErrorResponse.setErrorMessage("Employee Not found!");
        EmsErrorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(EmsErrorResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ResponseEntity handleException(EmployeeIDMismatchException ex){
        EmsErrorResponse EmsErrorResponse = new EmsErrorResponse();
        EmsErrorResponse.setErrorCode("EMS-400");
        EmsErrorResponse.setErrorMessage("Employee ID in the path is not same as the ID of Employee!");
        EmsErrorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EmsErrorResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ResponseEntity handleException(MethodArgumentNotValidException ex){
        EmsErrorResponse EmsErrorResponse = new EmsErrorResponse();
        EmsErrorResponse.setErrorCode("EMS-400");
        EmsErrorResponse.setErrorMessage("EMS is Not Valid!");
        EmsErrorResponse.setTimestamp(LocalDateTime.now());
        EmsErrorResponse.setFieldErrors(ex.getFieldErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EmsErrorResponse);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ResponseEntity handleException(Exception ex){
        EmsErrorResponse EmsErrorResponse = new EmsErrorResponse();
        EmsErrorResponse.setErrorCode("EMS-500");
        EmsErrorResponse.setErrorMessage("An Server Exception occurred!");
        EmsErrorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(EmsErrorResponse);
    }
}
