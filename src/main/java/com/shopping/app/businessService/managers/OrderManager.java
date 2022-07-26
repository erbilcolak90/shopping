package com.shopping.app.businessService.managers;

import com.shopping.app.businessService.OrderService;
import com.shopping.app.core.Result;
import com.shopping.app.entities.*;
import com.shopping.app.enums.Enums;
import com.shopping.app.repositories.AddressRepository;
import com.shopping.app.repositories.OrderRepository;
import com.shopping.app.repositories.ShoppingCartRepository;
import com.shopping.app.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
public class OrderManager implements OrderService {

    private OrderRepository orderRepository;

    private UserRepository userRepository;

    private AddressRepository addressRepository;

    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public OrderManager(OrderRepository orderRepository, UserRepository userRepository, AddressRepository addressRepository, ShoppingCartRepository shoppingCartRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    //Post methods
    @Override
    public Result createOrder(String shoppingCartId,String deliveryAddressId,String invoiceAddressId) {
        try {
            ShoppingCart shoppingCart = this.shoppingCartRepository.findById(shoppingCartId).orElseThrow();
            Order order = new Order();
            order.setShoppingCartId(shoppingCartId);
            order.setUserId(shoppingCart.getUserId());
            order.setDeliveryAddressId(deliveryAddressId);
            order.setInvoiceAddressId(invoiceAddressId);
            order.setCreateDate(new Date());
            order.setUpdateDate(new Date());
            order.setDeleted(false);
            order.setStatus(Enums.WAITING.toString());
            order.setOrderProductList(shoppingCart.getShoppingCartProductList());

            order.setDiscountTotal(order.discount());

            /*
            TODO: productPricetotal de if koşullarını baz almıyor.
             */

            if(order.getDiscountTotal()>500){
                this.orderRepository.insert(order);
                return new Result<>(true,"You have extra discount " +order.getDiscountTotal()+ " Your shopping cart total amount " + order.productPriceTotal() ,order);
            }
            else{
                this.orderRepository.insert(order);
                return new Result<>(true,"Complete the shopping cart for 500$, get 10% discount instantly",order);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Get methods
    @Override
    public Result<List<Product>> getOrderProductList(String orderId) {
        try {
            Order order = this.orderRepository.findById(orderId).orElseThrow();
            List<Product> orderList = order.getOrderProductList();

            return new Result<List<Product>>(true,"Order product list",orderList);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Put methods
    @Override
    public Result editOrderAddresses(String orderId,String userId, String deliveryAddressId, String invoiceAddressId) {
        try {
            Order order = this.orderRepository.findById(orderId).orElseThrow();
            User user = this.userRepository.findById(userId).orElseThrow();
            Address deliveryAddress = this.addressRepository.findById(deliveryAddressId).orElseThrow();
            Address invoiceAddress = this.addressRepository.findById(invoiceAddressId).orElseThrow();
            List<Address> addressList = user.getAddressesList();
            for(Address addressItem: addressList){
                if(addressItem.getId().equals(deliveryAddressId) && addressItem.getId().equals(invoiceAddressId)){
                    order.setDeliveryAddressId(deliveryAddressId);
                    order.setInvoiceAddressId(invoiceAddressId);
                    this.orderRepository.save(order);
                    return new Result<>(true,"Addresses changed",null);
                }
                else{
                    return new Result<>(false,"Addresses Id's not available",null);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result completeOrder(String orderId) {
        try{
            Order order = this.orderRepository.findById(orderId).orElseThrow();

            if(order.isDeleted()==true){
                order.setDeleted(false);
            }
            order.setStatus(Enums.SUCCESS.toString());
            order.setUpdateDate(new Date());
            ShoppingCart shoppingCart = this.shoppingCartRepository.findById(order.getShoppingCartId()).orElseThrow();
            shoppingCart.resetShoppingCart();
            this.orderRepository.save(order);

            return new Result<>(true,"Order completed",null);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    //Delete methods
    @Override
    public Result cancelOrder(String orderId) {
        try{
            Order order = this.orderRepository.findById(orderId).orElseThrow();

            if(order.getStatus().equals(Enums.SUCCESS.toString())){

                return new Result<>(true,"completed order cannot be canceled",null);
            }
            order.setStatus(Enums.FAILED.toString());
            order.setDeleted(true);
            order.setUpdateDate(new Date());

            this.orderRepository.save(order);

            return new Result<>(true,"Order canceled",null);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
