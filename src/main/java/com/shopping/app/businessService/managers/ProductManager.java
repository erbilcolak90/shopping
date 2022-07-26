package com.shopping.app.businessService.managers;

import com.shopping.app.businessService.ProductService;
import com.shopping.app.core.Result;
import com.shopping.app.entities.Category;
import com.shopping.app.entities.Product;
import com.shopping.app.repositories.CategoryRepository;
import com.shopping.app.repositories.ProductRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
public class ProductManager implements ProductService {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    @Autowired
    public ProductManager(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Result createProduct(Product product) {
        try {
            product.setCreateDate(new Date());
            product.setUpdateDate(new Date());
            product.setDeleted(false);

            this.productRepository.save(product);
            return new Result<>(true, "Product created", null);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result getProduct(String productId) {
        try {
            Product product = this.productRepository.findById(productId).orElseThrow();

            return new Result<>(true, "Product is listed :", product);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Result<List<Product>> getAllProduct() {

        try {

            List<Product> productList = this.productRepository.findAll();
            List<Product> products = new ArrayList<Product>();
            for(Product productItem: productList){
                //deleted products are not listed
                if(productItem.isDeleted()==false){
                    products.add(productItem);
                }
            }
            return new Result<>(true, "Products listed ", products);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Result changeProductName(String productId, String productName) {
        try {
            Product product = this.productRepository.findById(productId).orElseThrow();
            product.setName(productName);
            product.setUpdateDate(new Date());
            this.productRepository.save(product);
            return new Result<>(true, "Product name changed", null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result changeProductPrice(String productId, int productPrice) {
        try {
            Product product = this.productRepository.findById(productId).orElseThrow();
            product.setPrice(productPrice);
            product.setUpdateDate(new Date());
            this.productRepository.save(product);
            return new Result<>(true, "Product price changed", null);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Result changeProductCategory(String productId, String categoryId) {
        try {
            Product product = this.productRepository.findById(productId).orElseThrow();
            Category category = this.categoryRepository.findById(categoryId).orElseThrow();

            product.setCategoryId(categoryId);
            product.setUpdateDate(new Date());
            this.productRepository.save(product);

            return new Result<>(true,"Product category is changed",null);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Result deleteProduct(String productId) {
        try {
            Product product = this.productRepository.findById(productId).orElseThrow();
            product.setDeleted(true);
            product.setUpdateDate(new Date());

            this.productRepository.save(product);

            return new Result<>(true,"Product deleted",null);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
