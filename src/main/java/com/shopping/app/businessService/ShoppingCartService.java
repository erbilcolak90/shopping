package com.shopping.app.businessService;

import com.shopping.app.core.Result;
import com.shopping.app.entities.Product;
import com.shopping.app.entities.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    Result createShoppingCart(ShoppingCart shoppingCart);

    Result addProductToShoppingCart(String shoppingCartId, String productId, int count);

    Result<List<Product>> getShoppingCartProductList(String shoppingCartId);

    Result removeItemFromShoppingCart(String shoppingCartId, String productId, int count);

    Result resetCart(String shoppingCartId);
}
