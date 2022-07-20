package com.shopping.app.businessService;

import com.shopping.app.core.Result;
import com.shopping.app.entities.Address;
import com.shopping.app.entities.Product;
import com.shopping.app.entities.User;

import java.util.List;

public interface UserService{

    Result createUser(User user);

    Result getUser(String userId);

    Result<List<User>> getAllUsers();

    Result<List<Product>> getFavoriteProductList(String userId);

    Result<List<Address>> getAddresses(String userId);

    Result changePassword(String userId,String currrentPassword,String newPassword);

    Result changeEmail(String userId,String email);

    Result addToFavoriteList(String userId,String productId);

    Result deleteUser(String userId);




}
