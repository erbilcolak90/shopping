package com.shopping.app.businessService;

import com.shopping.app.core.Result;
import com.shopping.app.entities.Category;

import java.util.List;

public interface CategoryService {

    Result createCategory(Category category);

    Result<List<Category>> getAllCategory();

}
