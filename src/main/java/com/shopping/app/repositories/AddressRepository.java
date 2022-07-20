package com.shopping.app.repositories;

import com.shopping.app.entities.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address,String> {
}
