package com.shopping.app.controller;

import com.shopping.app.businessService.AddressService;
import com.shopping.app.businessService.ProductService;
import com.shopping.app.businessService.UserService;
import com.shopping.app.core.Result;
import com.shopping.app.entities.Address;
import com.shopping.app.entities.Product;
import com.shopping.app.entities.User;
import com.shopping.app.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private ProductService productService;

    private AddressService addressService;

    @Autowired
    public UserController(UserService userService, ProductService productService, AddressService addressService) {
        this.userService = userService;
        this.productService = productService;
        this.addressService = addressService;
    }

    @PostMapping("/createUser")
    public Result createUser(@RequestBody User user){
        return this.userService.createUser(user);
    }

    @GetMapping("/getUser")
    public Result getUser(@RequestParam String userId){
        return this.userService.getUser(userId);
    }

    @GetMapping("/findByName")
    public Result<List<User>> findByName(@RequestParam String name){
        return this.userService.findByName(name);
    }

    @GetMapping("/findByNameSortByEmailDesc")
    public Result<List<User>> findByNameSortByEmailDesc(@RequestParam String name){
        return this.userService.findByNameSortByEmailDesc(name);
    }

    @GetMapping("/findByNameSortByEmailAsc")
    public Result<List<User>> findByNameSortByEmailAsc(@RequestParam String name){
        return this.userService.findByNameSortByEmailAsc(name);
    }

    @GetMapping("/findByNameAndSurname")
    public Result<List<User>> findByNameAndSurname(@RequestParam String name){
        return this.userService.findByNameAndSurname(name);
    }

    @GetMapping("/pageableUser")
    public Result<Page<User>> pageableUser(@RequestParam int pageNumber,@RequestParam int pageSize){
        return this.userService.pageableUser(pageNumber, pageSize);
    }

    @GetMapping("/getAllUsers")
    public Result<List<User>> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @GetMapping("/getFavoriteProductList")
    public Result<List<Product>> getFavoriteProductList(@RequestParam String userId){
        return this.userService.getFavoriteProductList(userId);
    }

    @GetMapping("/getAddresses")
    public Result<List<Address>> getAddresses(@RequestParam String userId){
        return this.userService.getAddresses(userId);
    }

    @PutMapping("/changePassword")
    public Result changePassword(@RequestParam String userId,@Valid @RequestParam String currentPassword,@Valid @RequestParam String newPassword){
        return this.userService.changePassword(userId,currentPassword,newPassword);
    }

    @PutMapping("/changeEmail")
    public Result changeEmail(@RequestParam String userId,@Valid @RequestParam String email){
        return this.userService.changeEmail(userId,email);
    }

    @PutMapping("/addFavoriteList")
    public Result addToFavoriteList(@RequestParam String userId,@RequestParam String productId){
        return this.userService.addToFavoriteList(userId,productId);
    }

    @DeleteMapping("/deleteUser")
    public Result deleteUser(@RequestParam String userId){
        return this.userService.deleteUser(userId);
    }


}
