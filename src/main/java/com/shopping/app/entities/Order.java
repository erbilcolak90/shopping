package com.shopping.app.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("Orders")
public class Order {

    @Id
    private String id;
    private Date createDate;
    private Date updateDate;
    private boolean isDeleted;
    private List<Product> orderProductList;
    private double discountTotal;
    @NotEmpty
    private String shoppingCartId;
    private String userId;
    @NotEmpty
    private String deliveryAddressId;
    @NotEmpty
    private String invoiceAddressId;
    @NotEmpty
    private String status;

    public int productPriceTotal(){
        int productPriceTotal =0;
        for(Product productItem: orderProductList){
            productPriceTotal = productPriceTotal+productItem.getPrice();

        }
        return productPriceTotal;
    }

    public double discount(){
        int total=0;
        for(Product productItem: orderProductList){
            total = total + productItem.getPrice();
        }
        if( total > 499 || total <= 1000){
            discountTotal = total * 0.10;
            return discountTotal;
        }
        else if(total>1000 || total<=2000){
            discountTotal = total * 0.15;
            return discountTotal;
        }
        else if(total> 2000){
            discountTotal = total * 0.20;
            return discountTotal;
        }
        else{
            return total;
        }

    }
}
