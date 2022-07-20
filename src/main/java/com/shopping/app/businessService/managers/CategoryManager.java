package com.shopping.app.businessService.managers;

import com.shopping.app.businessService.CategoryService;
import com.shopping.app.core.Result;
import com.shopping.app.entities.Category;
import com.shopping.app.repositories.CategoryRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
public class CategoryManager implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryManager(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Result createCategory(Category category) {
        try {
            category.setCreateDate(new Date());
            category.setUpdateDate(new Date());
            category.setDeleted(false);
            this.categoryRepository.save(category);

            return new Result<>(true,"Category is created",category);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<List<Category>> getAllCategory() {
        try {
            return new Result<>(true,"All categories listed",this.categoryRepository.findAll());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
