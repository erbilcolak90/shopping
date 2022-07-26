package com.shopping.app.repositories;

import com.shopping.app.core.Result;
import com.shopping.app.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface UserRepository extends MongoRepository<User,String> {


    //This query includes name,surname and email information.
    @Query(value = "{ 'name' : ?0 }",fields = "{ 'name' : 1 , 'surname' : 1 ,'email':1 }")
    List<User> findByName(String name);

    @Query(value = "{'name' : ?0 }",fields = "{ 'name' :1, 'favoriteProductList': 1 }", sort = "{'email' : -1}")
    List<User> findByNameSortByEmailDesc(String name);

    @Query(value = "{'name' : ?0 }",fields = "{ 'name' :1, 'favoriteProductList': 1 }",sort = "{'email' : 1}")
    List<User> findByNameSortByEmailAsc(String name);

    //this query sorting from surname DESC
    @Query(value = "{'name' : ?0 }",sort = "{ 'surname' : -1 }")
    List<User> findByNameAndSurname(String name);

}
