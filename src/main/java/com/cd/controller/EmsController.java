package com.cd.controller;


import com.cd.exceptions.EmployeeIDMismatchException;
import com.cd.model.Employee;
import com.cd.service.IEmsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ems")
@CrossOrigin
public class EmsController {

    @Autowired
    IEmsService iEmsService;

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("App is up and running");
    }


    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees()
    {
        return  ResponseEntity.ok(iEmsService.getEmployees());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id)
    {
        return  ResponseEntity.ok(iEmsService.getEmployeeById(UUID.fromString(id)));
    }

    @GetMapping("/users/{user}")
    public ResponseEntity<List<Employee>> getTodosByUser(@PathVariable String user)
    {
        return ResponseEntity.ok(iEmsService.getTodosByUser(user));
    }


    @PostMapping
    public ResponseEntity<Employee> addEmployee( @Valid  @RequestBody Employee Employee) throws URISyntaxException {
        Employee newEmployee= iEmsService.addEmployee(Employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEmployee.getEmployeeId()).toUri();
        return  ResponseEntity.created(location).body(newEmployee);
    }


    @GetMapping("/users/department/{department}")
    public ResponseEntity<List<Employee>> getEmployeeByDepartment(@PathVariable String department)
    {
        return ResponseEntity.ok(iEmsService.getEmployeeByDepartment(department));
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@Valid @PathVariable String id,@RequestBody Employee Employee)
    {
        if( UUID.fromString(id).equals(Employee.getEmployeeId()))
            return  iEmsService.updateEmployee(Employee);
        else
            throw new EmployeeIDMismatchException();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable String id)
    {
        if( iEmsService.deleteEmployee(UUID.fromString(id))){
            return ResponseEntity.noContent().build();
        }
        else
            return  ResponseEntity.notFound().build();
    }

    @GetMapping("/users/age/{from}/{to}")
    public ResponseEntity<List<Employee>> getEmployeeByAgeCriteria(@PathVariable int from, @PathVariable int to)
    {
        return ResponseEntity.ok(iEmsService.getEmployeeByAgeCriteria(from,to));
    }

    @GetMapping("/users/after/{joiningDate}")
    public ResponseEntity<List<Employee>> getEmployeeAfterjoiningDate(@PathVariable LocalDate joiningDate)
    {
        return ResponseEntity.ok(iEmsService.getEmployeeAfterjoiningDate(joiningDate));
    }

    @GetMapping("/users/before/{joiningDate}")
    public ResponseEntity<List<Employee>> getEmployeeBeforejoiningDate(@PathVariable LocalDate joiningDate)
    {
        return ResponseEntity.ok(iEmsService.getEmployeeBeforejoiningDate(joiningDate));
    }

    @GetMapping("/users/sort/{order}")
    public ResponseEntity<List<Employee>> sortEmployeeByJoiningDate(@PathVariable int order)
    {
        return ResponseEntity.ok(iEmsService.sortEmployeeByJoiningDate(order));
    }

}
