package com.shopping.app.controller;

import com.shopping.app.businessService.OrderService;
import com.shopping.app.core.Result;
import com.shopping.app.entities.Order;
import com.shopping.app.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public Result createOrder(@RequestParam String shoppingCartId,@RequestParam String deliveryAddressId,@RequestParam String invoiceAddressId){
        return this.orderService.createOrder(shoppingCartId, deliveryAddressId, invoiceAddressId);
    }

    @GetMapping("/getOrderProductList")
    public Result<List<Product>> getOrderProductList(@RequestParam String orderId){
        return this.orderService.getOrderProductList(orderId);
    }

    @PutMapping("/editOrderAddresses")
    public Result editOrderAddresses(@RequestParam String orderId,@RequestParam String userId,@Valid @RequestParam String deliveryAddressId,@Valid @RequestParam String invoiceAddressId){
        return this.orderService.editOrderAddresses(orderId, userId, deliveryAddressId, invoiceAddressId);
    }

    @PutMapping("/completeOrder")
    public Result completeOrder(@RequestParam String orderId){
        return this.orderService.completeOrder(orderId);
    }

    @DeleteMapping("/cancelOrder")
    public Result cancelOrder(@RequestParam String orderId){
        return this.orderService.cancelOrder(orderId);
    }



}
