package com.shopping.app.repositories;

import com.shopping.app.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User,String> {

}
