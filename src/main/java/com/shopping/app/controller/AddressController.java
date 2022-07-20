package com.shopping.app.controller;

import com.shopping.app.businessService.AddressService;
import com.shopping.app.core.Result;
import com.shopping.app.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/createAddress")
    public Result createAddress(@Valid @RequestBody Address address){
        return this.addressService.createAddress(address);
    }

    @PutMapping("/editAddress")
    public Result editAddress(@RequestParam String addressId,@Valid @RequestParam String country,@Valid @RequestParam String city,@RequestParam String description){
        return this.addressService.editAddress(addressId, country, city, description);
    }

    @DeleteMapping("/deleteAddress")
    public Result deleteAddress(@RequestParam String addressId){
        return this.addressService.deleteAddress(addressId);
    }
}
