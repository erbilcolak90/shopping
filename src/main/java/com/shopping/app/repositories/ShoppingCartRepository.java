package com.shopping.app.repositories;

import com.shopping.app.entities.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart,String> {
}
