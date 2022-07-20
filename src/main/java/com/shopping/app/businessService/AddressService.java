package com.shopping.app.businessService;

import com.shopping.app.core.Result;
import com.shopping.app.entities.Address;

public interface AddressService {

    Result createAddress(Address address);

    Result editAddress(String addressId,String country,String city,String description);

    Result deleteAddress(String addressId);

}
