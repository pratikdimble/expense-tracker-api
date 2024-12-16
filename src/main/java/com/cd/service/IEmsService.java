package com.cd.service;



import com.cd.model.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IEmsService {
    List<Employee> getEmployees();
    List<Employee> getTodosByUser(String userName);
    Employee getEmployeeById(UUID id);
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    boolean deleteEmployee(UUID id);
    List<Employee> getEmployeeByDepartment(String department);
    List<Employee> getEmployeeBeforejoiningDate(LocalDate joiningDate);
    List<Employee> getEmployeeAfterjoiningDate(LocalDate joiningDate);
    List<Employee> getEmployeeByAgeCriteria(Integer from, Integer to);
    List<Employee> sortEmployeeByJoiningDate(Integer order);
}
