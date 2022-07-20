package com.shopping.app.businessService;

import com.shopping.app.core.Result;
import com.shopping.app.entities.Product;

import java.util.List;

public interface ProductService {

    Result createProduct(Product product);

    Result getProduct(String productId);

    Result<List<Product>> getAllProduct();

    Result changeProductName(String productId,String productName);

    Result changeProductPrice(String productId,int productPrice);

    Result changeProductCategory(String productId,String categoryId);

    Result deleteProduct(String productId);


}
