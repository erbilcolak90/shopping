package com.shopping.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("Products")
public class Product {

    @Id
    private String id;
    private Date createDate;
    private Date updateDate;
    private boolean isDeleted;
    @NotNull
    private String categoryId;
    @NotBlank
    private String name;
    @Positive
    private int price;
    private String description;

}
