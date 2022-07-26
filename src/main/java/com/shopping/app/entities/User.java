package com.shopping.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("Users")
public class User {

    @Id
    private String id;
    private Date createDate;
    private Date updateDate;
    private boolean isDeleted;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "surname is mandatory")
    private String surname;
    @NotBlank(message = "password is mandatory")
    private String password;
    private String email;
    private boolean shoppingCart;
    private List<Address> addressesList = new ArrayList<Address>();
    private List<Product> favoriteProductList = new ArrayList<Product>();

}