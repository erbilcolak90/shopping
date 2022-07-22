package com.shopping.app.businessService.managers;

import com.shopping.app.businessService.ShoppingCartService;
import com.shopping.app.core.Result;
import com.shopping.app.entities.Product;
import com.shopping.app.entities.ShoppingCart;
import com.shopping.app.entities.User;
import com.shopping.app.repositories.ProductRepository;
import com.shopping.app.repositories.ShoppingCartRepository;
import com.shopping.app.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Service
public class ShoppingCartManager implements ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @Autowired
    public ShoppingCartManager(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    //Post methods
    @Override
    public Result createShoppingCart(ShoppingCart shoppingCart) {
        try {
            User user = this.userRepository.findById(shoppingCart.getUserId()).orElseThrow();
            if(shoppingCart.getUserId().isEmpty()){

                this.shoppingCartRepository.save(shoppingCart);
                return new Result<>(true,"Shopping Cart is ready",shoppingCart);
            }
            else{
                return new Result<>(false,"You already have a shopping cart and shopping cart Id :",shoppingCart);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Get methods
    @Override
    public Result<List<Product>> getShoppingCartProductList(String shoppingCartId) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartRepository.findById(shoppingCartId).orElseThrow();
            List<Product> productList = shoppingCart.getShoppingCartProductList();

            return new Result<>(true,"Shopping Cart Product List : ",productList);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Put methods
    @Override
    public Result removeItemFromShoppingCart(String shoppingCartId, String productId, int count) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartRepository.findById(shoppingCartId).orElseThrow();
            List<Product> productList = shoppingCart.getShoppingCartProductList();
            Product product = this.productRepository.findById(productId).orElseThrow();


            for (Product productItem : productList) {
                if (productItem.getId().equals(productId)) {
                    for (int i = 0; i <= count; i++) {
                        productList.remove(product);
                        shoppingCart.setTotal(shoppingCart.getTotal());
                        this.shoppingCartRepository.save(shoppingCart);

                    }
                    return new Result<>(true, "Product removed at cart", null);
                } else {
                    return new Result<>(false, "The product is not already in the cart ", null);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result addProductToShoppingCart(String shoppingCartId, String productId, int count) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartRepository.findById(shoppingCartId).orElseThrow();
            List<Product> productList = shoppingCart.getShoppingCartProductList();
            Product product = this.productRepository.findById(productId).orElseThrow();

            for (Product productItem : productList) {
                if (productItem.getId().equals(productId)) {
                    for (int i = 0; i <= count; i++) {
                        productList.add(product);
                        shoppingCart.setTotal(shoppingCart.calculateTotal());
                        this.shoppingCartRepository.save(shoppingCart);
                    }
                    return new Result<>(true, "This product(s) is already available and product quantity has been increased", null);
                } else {
                    for (int i = 0; i <= count; i++) {
                        productList.add(product);
                        shoppingCart.setTotal(shoppingCart.calculateTotal());
                        this.shoppingCartRepository.save(shoppingCart);
                    }
                    return new Result<>(true, "Product(s) is added your cart", null);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result resetCart(String shoppingCartId) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartRepository.findById(shoppingCartId).orElseThrow();

            shoppingCart.getShoppingCartProductList().clear();
            this.shoppingCartRepository.save(shoppingCart);
            return new Result<>(true, "Cart is empty", null);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
