package com.shopping.app.controller;

import com.shopping.app.businessService.ProductService;
import com.shopping.app.core.Result;
import com.shopping.app.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/createProduct")
    public Result createProduct(@Valid @RequestBody Product product){
        return this.productService.createProduct(product);
    }

    @GetMapping("/getProduct")
    public Result getProduct(String productId){
        return this.productService.getProduct(productId);
    }

    @GetMapping("/getAllProduct")
    public Result<List<Product>> getAllProduct(){
        return this.productService.getAllProduct();
    }

    @PutMapping("/changeProductName")
    public Result changeProductName(@RequestParam String productId,@Valid @RequestParam String productName){
        return this.productService.changeProductName(productId, productName);
    }

    @PutMapping("/changeProductPrice")
    public Result changeProductPrice(@RequestParam String productId,@Valid @RequestParam int productPrice){
        return this.productService.changeProductPrice(productId, productPrice);
    }

    @PutMapping("/changeProductCategory")
    public Result changeProductCategory(@RequestParam String productId,@Valid @RequestParam String categoryId){
        return this.productService.changeProductCategory(productId, categoryId);
    }

    @DeleteMapping("/deleteProduct")
    public Result deleteProduct(@RequestParam String productId){
        return this.productService.deleteProduct(productId);
    }

}
