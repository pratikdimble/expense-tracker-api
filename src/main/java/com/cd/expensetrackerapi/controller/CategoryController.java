package com.cd.expensetrackerapi.controller;


import com.cd.expensetrackerapi.model.CategoryMaster;
import com.cd.expensetrackerapi.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catogory")
@CrossOrigin
public class CategoryController {

    @Autowired
    ICategoryService iCategoryService;

    @GetMapping
    public ResponseEntity<List<CategoryMaster>> getCategories()
    {
        return  ResponseEntity.ok(iCategoryService.getCategories());
    }
}
