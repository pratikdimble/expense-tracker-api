package com.cd.expensetrackerapi;

import com.cd.expensetrackerapi.model.CategoryMaster;
import com.cd.expensetrackerapi.model.ExpenseMaster;
import com.cd.expensetrackerapi.model.UserMaster;
import com.cd.expensetrackerapi.repository.CategoryRepository;
import com.cd.expensetrackerapi.repository.ExpenseRepository;
import com.cd.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ExpenseTrackerApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerApiApplication.class, args);
    }

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ExpenseRepository expenseRepository;


    @Override
    public void run(String... args) throws Exception {
        List<CategoryMaster> categories = new ArrayList<>();
        List<String> categoriesNames = Arrays.asList("Food","Transport","Utilities","Entertainment","Miscellaneous");
        for(String category: categoriesNames ){
            CategoryMaster categoryMaster =new CategoryMaster();
            categoryMaster.setCategoryName(category);
            categories.add(categoryMaster);
        }
        categoryRepository.saveAll(categories);

        UserMaster userMaster = new UserMaster();
        userMaster.setUsername("admin");
        userMaster.setPassword("admin");
        userRepository.save(userMaster);

        List<ExpenseMaster> expenseMasters = new ArrayList<>();
        ExpenseMaster expenseMaster = new ExpenseMaster();
        expenseMaster.setTitle("Hotel");expenseMaster.setAmount(1350d);expenseMaster.setUserId(1l);expenseMaster.setCategory("Food");
        expenseMaster.setDeleted(false);expenseMaster.setCreatedDate(LocalDate.of(2024,11,19));expenseMaster.setUpdatedDate(LocalDate.of(2024,11,19));

        ExpenseMaster expenseMaster2 = new ExpenseMaster();
        expenseMaster2.setTitle("Petrol");expenseMaster2.setAmount(3250d);expenseMaster2.setUserId(1l);expenseMaster2.setCategory("Transport");
        expenseMaster2.setDeleted(false);expenseMaster2.setCreatedDate(LocalDate.of(2024,11,10));expenseMaster2.setUpdatedDate(LocalDate.of(2024,11,10));

        ExpenseMaster expenseMaster3 = new ExpenseMaster();
        expenseMaster3.setTitle("Movies");expenseMaster3.setAmount(950d);expenseMaster3.setUserId(1l);expenseMaster3.setCategory("Entertainment");
        expenseMaster3.setDeleted(false);expenseMaster3.setCreatedDate(LocalDate.of(2024,12,10));expenseMaster3.setUpdatedDate(LocalDate.of(2024,12,10));

        expenseMasters.add(expenseMaster);expenseMasters.add(expenseMaster2);expenseMasters.add(expenseMaster3);
        expenseRepository.saveAll(expenseMasters);

    }
}
