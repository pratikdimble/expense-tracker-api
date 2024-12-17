package com.cd.expensetrackerapi.controller;

import com.cd.expensetrackerapi.model.ExpenseMaster;
import com.cd.expensetrackerapi.service.IExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/expense")
@CrossOrigin
public class ExpenseController {

    @Autowired
    IExpenseService iExpenseService;

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("App is up and running");
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ExpenseMaster>> getExpenses(@PathVariable Long userId)
    {
        return ResponseEntity.ok(iExpenseService.getExpenses(userId));
    }

    @GetMapping("/users/deleted-expense/{userId}")
    public ResponseEntity<List<ExpenseMaster>> getDeletedExpenses(@PathVariable Long userId)
    {
        return ResponseEntity.ok(iExpenseService.getDeletedExpenses(userId));
    }

    @PostMapping
    public ResponseEntity<ExpenseMaster> addExpenses( @Valid @RequestBody ExpenseMaster expenseMaster) throws URISyntaxException {
        ExpenseMaster expenseMasterNew= iExpenseService.addExpenses(expenseMaster);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(expenseMasterNew.getExpenseId()).toUri();
        return  ResponseEntity.created(location).body(expenseMasterNew);
    }

    @PutMapping("/{expenseId}")
    public ExpenseMaster updateExpenses(@Valid @PathVariable Long expenseId,@RequestBody ExpenseMaster expenseMaster)
    {
        return  iExpenseService.updateExpenses(expenseMaster);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteExpenses(@PathVariable Long id)
    {
        if( iExpenseService.deleteExpenses(id)){
            return ResponseEntity.noContent().build();
        }
        else
            return  ResponseEntity.notFound().build();
    }
}
