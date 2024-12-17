package com.cd.expensetrackerapi.service;



import com.cd.expensetrackerapi.model.CategoryMaster;
import com.cd.expensetrackerapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {


    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryMaster> getCategories() {
        return categoryRepository.findAll();
    }
/*
@Autowired
    EmsRepository emsRepository;

    @Override
    public List<Employee> getEmployees() {
        return emsRepository.findAll();
    }

    @Override
    public List<Employee> getTodosByUser(String userName) {
        return emsRepository.findEmployeeByNameEqualsIgnoreCase(userName);
    }


    @Override
    public Employee getEmployeeById(UUID id) {
        return   emsRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return emsRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        getEmployeeById(employee.getEmployeeId());
        return emsRepository.save(employee);
    }

    @Override
    public boolean deleteEmployee(UUID id) {
        getEmployeeById(id);
        emsRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Employee> getEmployeeByDepartment(String department) {
        return emsRepository.getEmployeeByDepartmentEqualsIgnoreCase(department);
    }

    @Override
    public List<Employee> getEmployeeBeforejoiningDate(LocalDate joiningDate) {
        return emsRepository.findAllByJoiningDateBefore(joiningDate);
    }

    @Override
    public List<Employee> getEmployeeAfterjoiningDate(LocalDate joiningDate) {
        return emsRepository.findAllByJoiningDateAfter(joiningDate);
    }

    @Override
    public List<Employee> getEmployeeByAgeCriteria(Integer from, Integer to) {
        return emsRepository.findAllByAgeBetween(from, to);
    }

    @Override
    public List<Employee> sortEmployeeByJoiningDate(Integer order) {
        if(order == 0)
            return emsRepository.findAllByOrderByJoiningDateAsc();
        else
            return emsRepository.findAllByOrderByJoiningDateDesc();
    }*/
}
