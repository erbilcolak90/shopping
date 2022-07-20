package com.shopping.app.controller;

import com.shopping.app.businessService.CategoryService;
import com.shopping.app.core.Result;
import com.shopping.app.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/createCategory")
    public Result createCategory(@Valid @RequestBody Category category){
        return this.categoryService.createCategory(category);
    }

    @GetMapping("/getAllCategory")
    public Result<List<Category>> getAllCategory(){
        return this.categoryService.getAllCategory();
    }
}
