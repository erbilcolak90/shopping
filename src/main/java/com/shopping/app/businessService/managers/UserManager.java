package com.shopping.app.businessService.managers;

import com.shopping.app.businessService.UserService;
import com.shopping.app.core.Result;
import com.shopping.app.entities.Address;
import com.shopping.app.entities.Product;
import com.shopping.app.entities.User;
import com.shopping.app.repositories.AddressRepository;
import com.shopping.app.repositories.ProductRepository;
import com.shopping.app.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
public class UserManager implements UserService {

    private UserRepository userRepository;
    private ProductRepository productRepository;

    private AddressRepository addressRepository;

    @Autowired
    public UserManager(UserRepository userRepository, ProductRepository productRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
    }

    //Post Mapping Methods
    @Override
    public Result createUser(User user) {

        try {
            user.setCreateDate(new Date());
            user.setUpdateDate(new Date());
            user.setDeleted(false);
            user.setShoppingCart(false);
            //save user to database
            this.userRepository.save(user);

            return new Result(true, "User created", user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Get Mapping Methods
    @Override
    public Result getUser(String userId) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow();

            if (user.isDeleted() == true) {
                return new Result<>(false, "User not found : ", null);
            } else {
                return new Result<>(true, "User info :", user);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<List<User>> getAllUsers() {
        try {
            List<User> userList = (List<User>) this.userRepository.findAll();


            return new Result<List<User>>(true, "Users List", userList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<List<String>> getFavoriteProductList(String userId) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow();

            return new Result<List<String>>(true, "Favorite product list :", user.getFavoriteProductList());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<List<Address>> getAddresses(String userId) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow();
            List<Address> addressList = this.addressRepository.findAll();
            List<Address> userAddressList = user.getAddressesList();
            for (Address address : addressList) {
                if (userId.equals(address.getUserId())) {
                    userAddressList.add(address);
                }
            }

            this.userRepository.save(user);

            return new Result<List<Address>>(true, "Address list", userAddressList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Put Mapping Methods

    @Override
    public Result changePassword(String userId, String currentPassword, String newPassword) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow();
            if (user.getPassword().equals(currentPassword)) {
                user.setUpdateDate(new Date());
                this.userRepository.save(user);
                return new Result(true, "password changed", null);
            } else {
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result changeEmail(String userId, String email) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow();
            user.setUpdateDate(new Date());

            this.userRepository.save(user);
            return new Result(true, "email changed", null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result addToFavoriteList(String userId, String productId) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow();
            Product product = this.productRepository.findById(productId).orElseThrow();

            for(String productItem: user.getFavoriteProductList()){
                if(productItem.equals(productId)){
                    return new Result<>(false, "The product is already in your favourites.", null);
                }

                else {
                    user.getFavoriteProductList().add(productItem);
                    user.setUpdateDate(new Date());
                    this.userRepository.save(user);
                    return new Result(true, "Product added your favorite list", null);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Delete Mapping Methods
    @Override
    public Result deleteUser(String userId) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow();
            user.setDeleted(true);
            user.setUpdateDate(new Date());

            this.userRepository.save(user);

            return new Result<>(true, "User deleted", null);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
