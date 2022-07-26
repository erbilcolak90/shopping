package com.shopping.app.businessService;

import com.shopping.app.core.Result;
import com.shopping.app.entities.Address;
import com.shopping.app.entities.Product;
import com.shopping.app.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface UserService{

    Result createUser(User user);

    Result getUser(String userId);

    Result<List<User>> findByName(String name);

    Result<List<User>> findByNameSortByEmailDesc(String name);

    Result<List<User>> findByNameSortByEmailAsc(String name);

    Result<List<User>> findByNameAndSurname(String name);

    Result<Page<User>> pageableUser(int pageNumber, int pageSize);

    Result<List<User>> getAllUsers();

    Result<List<Product>> getFavoriteProductList(String userId);

    Result<List<Address>> getAddresses(String userId);

    Result changePassword(String userId,String currrentPassword,String newPassword);

    Result changeEmail(String userId,String email);

    Result addToFavoriteList(String userId,String productId);

    Result deleteUser(String userId);




}
