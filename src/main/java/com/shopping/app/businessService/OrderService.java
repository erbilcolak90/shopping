package com.shopping.app.businessService;

import com.shopping.app.core.Result;
import com.shopping.app.entities.Order;
import com.shopping.app.entities.Product;

import java.util.List;

public interface OrderService {

    Result createOrder(String shoppingCartId,String deliveryAddressId,String invoiceAddressId);

    Result<List<Product>> getOrderProductList(String orderId);

    Result editOrderAddresses(String orderId,String userId,String deliveryAddressId,String invoiceAddressId);

    Result completeOrder(String orderId);

    Result cancelOrder(String orderId);


}
