package com.shopping.app.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("ShoppingCarts")
public class ShoppingCart {

    @Id
    private String id;
    @NotEmpty
    private String userId;
    private List<Product> shoppingCartProductList = new ArrayList<Product>();

    private int total;

    public int calculateTotal(){
        int total =0;
        for(Product cartItem: shoppingCartProductList){
            total = total+cartItem.getPrice();
        }
        return total;
    }

    public boolean resetShoppingCart(){
        setShoppingCartProductList(new ArrayList<Product>());
        setTotal(0);

        return true;
    }

}
