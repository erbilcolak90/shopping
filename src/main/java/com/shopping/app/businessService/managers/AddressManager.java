package com.shopping.app.businessService.managers;

import com.shopping.app.businessService.AddressService;
import com.shopping.app.core.Result;
import com.shopping.app.entities.Address;
import com.shopping.app.entities.User;
import com.shopping.app.repositories.AddressRepository;
import com.shopping.app.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@NoArgsConstructor
@Service
public class AddressManager implements AddressService {

    private AddressRepository addressRepository;

    private UserRepository userRepository;

    @Autowired
    public AddressManager(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Result createAddress(Address address) {
        try {
            User user = this.userRepository.findById(address.getUserId()).orElseThrow();
            address.setCreateDate(new Date());
            address.setDeleted(false);
            address.setUpdateDate(new Date());

            this.addressRepository.save(address);
            user.getAddressesList().add(address);
            this.userRepository.save(user);

            return new Result<>(true, "Address created", address);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result editAddress(String addressId,String country,String city,String description) {
        try {
            Address address = this.addressRepository.findById(addressId).orElseThrow();
            if(address.isDeleted()==false){
                address.setUpdateDate(new Date());
                address.setCountry(country);
                address.setCity(city);
                address.setDescription(description);
                this.addressRepository.save(address);
                return new Result<>(true,"Address changed",address);
            }
            else{
                return new Result<>(false,"Address not found",null);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result deleteAddress(String addressId) {
        try{
            Address address = this.addressRepository.findById(addressId).orElseThrow();
            address.setDeleted(true);
            address.setUpdateDate(new Date());

            this.addressRepository.save(address);
            return new Result<>(true,"Address deleted",null);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
