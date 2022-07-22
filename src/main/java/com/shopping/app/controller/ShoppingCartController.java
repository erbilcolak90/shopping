package com.shopping.app.controller;

import com.shopping.app.businessService.ShoppingCartService;
import com.shopping.app.core.Result;
import com.shopping.app.entities.Product;
import com.shopping.app.entities.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    //Post methods
    @PostMapping("/createShoppingCart")
    public Result createShoppingCart(@Valid @RequestBody ShoppingCart shoppingCart) {
        return this.shoppingCartService.createShoppingCart(shoppingCart);
    }

    //Get methods
    @GetMapping("/getShoppingCartProductList")
    public Result<List<Product>> getShoppingCartProductList(@RequestParam String shoppingCartId) {
        return this.shoppingCartService.getShoppingCartProductList(shoppingCartId);
    }

    //Put methods
    @PutMapping("/removeItemShoppingCart")
    public Result removeItemFromShoppingCart(@RequestParam String shoppingCartId, @RequestParam String productId, @RequestParam int count) {
        return this.shoppingCartService.removeItemFromShoppingCart(shoppingCartId, productId, count);
    }

    @PutMapping("/addProductToShoppingCart")
    public Result addProductToShoppingCart(@RequestParam String shoppingCartId,@RequestParam String productId, @RequestParam int count) {
        return this.shoppingCartService.addProductToShoppingCart(shoppingCartId, productId, count);
    }

    @PutMapping("/resetCart")
    public Result resetCart(@RequestParam String shoppingCartId) {
        return this.shoppingCartService.resetCart(shoppingCartId);
    }


}
