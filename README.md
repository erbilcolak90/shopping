# shopping

this project is based on a simple shopping website. Users can write the price, category, name 
and description of the products they will put on sale. It can create a favorite product list. 
Users must specify order address and billing address. They can create an order and complete the shopping. 
It offers additional discounts to encourage shopping during order completion.

### Information
- This project deployed from heroku.The project database uses mongoDB Atlas.
- Project database an open to request.
- If you run this project your localhost; you must be edit to postman url area.

---

### Postman Collections

[Click here for postman collection.zip](https://github.com/erbilcolak90/shopping/files/9258827/Shopping.postman_collection.zip)

[json examples](https://github.com/erbilcolak90/shopping/blob/main/jsonFormatExamples.json)


## Entities

- User
- ShoppingCart
- Product
- Order
- Category
- Address

### User

It is the class created for the user in the system.

- Id : `String`
- createDate : `Date`
- updateDate : `Date`
- isDeleted : `boolean`
- name : ``String``
- surname : ``String``
- password : ``String``
- email : ``String``
- shoppingCart : ``boolean``
- addressesList : ``List<Address>``
- favoriteProductList : ``List<Product>``

### ShoppingCart

It is the class created for the shoppingCart in the system.
User can have only one shoppingCart.

- Id : ``String``
- userId : ``String``
- shoppingCartProductList : ``List<Product>``
- total : ``int``

### Product 

It is the class created for the product in the system.

- Id : `String`
- createDate : `Date`
- updateDate : `Date`
- isDeleted : `boolean`
- categoryId : ``String``
- name : ``String``
- price : ``int``
- description : ``String``

### Order

It is the class created for the Order in the system.

- Id : `String`
- createDate : `Date`
- updateDate : `Date`
- isDeleted : `boolean`
- orderProductList : ``List<Product>``
- discountTotal : ``double``
- shoppinCartId : ``String``
- userId : ``String``
- deliveryAddressId : ``String``
- invoiceAddressId : ``String``
- status : ``String``

_Note:_ The orderProductList is obtained with the shoppingCart Id. 
The total amount and the discount amount are determined separately.

### Category

It is the class created for the Category in the system.

- Id : `String`
- createDate : `Date`
- updateDate : `Date`
- isDeleted : `boolean`
- name : ``String``
- parentCategoryId : ``String``

_Note:_ A parent category was used instead of a subcategory to categorize the products.

### Address

It is the class created for the Category in the system.

- Id : `String`
- createDate : `Date`
- updateDate : `Date`
- isDeleted : `boolean`
- name : ``String``
- country : ``String``
- city : ``String``
- description : ``String``
- userId : ``String``

### Rest Api

````
RestController("/user")
RestController("/shoppinCart")
RestController("/product")
RestController("/order")
RestController("/category")
RestController("/address")
````

## Create User

### Request 

````
method: POST
url: /user/createUser
requestSample: http://localhost:8080/user/createUser
requestParams: -
requestBody: {
  name: String,
  surname : String,
  password : String,
  email : String
}
````

### Response

````
{
    "success": true,
    "message": "User created",
    "data": {
        "id": "62d93afabd9a2259782f8434",
        "createDate": "2022-07-21T11:39:38.645+00:00",
        "updateDate": "2022-07-21T11:39:38.645+00:00",
        "name": erbil,
        "surname": "colak",
        "password": "12345",
        "email": "erbilcolak@gmail.com",
        "shoppingCart": false,
        "addressesList": [],
        "favoriteProductList": [],
        "deleted": false
    }
}
````

---

## Get a specific User

### Request

````
method: GET
url: /user/getUser
requestSample: http://localhost:8080/user/getUser?userId=1
requestParams: userId: String
requestBody: -

````
### Response

````
{
    "success": true,
    "message": "User info :",
    "data": {
        "id": "1",
        "createDate": "2022-07-21T11:48:33.687+00:00",
        "updateDate": "2022-07-21T11:48:33.687+00:00",
        "name": halide edip,
        "surname": "adıvar",
        "password": "12345",
        "email": "halideedip@gmail.com",
        "shoppingCart": false,
        "addressesList": [],
        "favoriteProductList": [],
        "deleted": false
    }
}
````

---

## FindByName

_Note_: Method fields (name,surname and email)

### Request
````
method: GET
url: /user/findByName
requestSample: http://localhost:8080/user/findByName
requestParams: name: String
requestBody: -

````

### Response
````
{
    "success": true,
    "message": "data listed",
    "data": [
        {
            "id": "62db0b9c0eaf3a282c22aa02",
            "createDate": null,
            "updateDate": null,
            "name": "cahit",
            "surname": "arf",
            "password": null,
            "email": "cahit@gmail.com",
            "shoppingCart": false,
            "addressesList": [],
            "favoriteProductList": [],
            "deleted": false
        },
        ...
    ]
}
````

---

## FindByNameSortByEmailDesc

_Note_: Method fields is (name,favoriteProductList,)

### Request
````
method: GET
url: /user/findByNameSortByEmailDesc
requestSample: http://localhost:8080/user/findByNameSortByEmailDesc
requestParams: name : String
requestBody: -

````

### Response
````
{
    "success": true,
    "message": "Data Listed ",
    "data": [
       
        {
            "id": "62def38d3816b23ea8b3dd55",
            "createDate": null,
            "updateDate": null,
            "name": "cahit",
            "surname": null,
            "password": null,
            "email": null,
            "shoppingCart": false,
            "addressesList": [],
            "favoriteProductList": [
                {
                    "id": "62dc17edcf4cc45323bec79c",
                    "createDate": "2022-07-23T15:46:53.923+00:00",
                    "updateDate": "2022-07-23T15:54:43.625+00:00",
                    "categoryId": "62dc17c9cf4cc45323bec79b",
                    "name": "uzun kollo polo",
                    "price": 250,
                    "description": "polo yaka",
                    "deleted": false
                }
            ],
            "deleted": false
        },
     ...
    ]
}
````

---

## FindByNameSortByEmailAsc

_Note_: Method fields is (name,favoriteProductList,)

### Request
````
method: GET
url: /user/findByNameSortByEmailAsc
requestSample: http://localhost:8080/user/findByNameSortByEmailAsc
requestParams: name : String
requestBody: -

````

### Response
````
{
    "success": true,
    "message": "Data Listed ",
    "data": [
       
        {
            "id": "62def38d3816b23ea8b3dd55",
            "createDate": null,
            "updateDate": null,
            "name": "cahit",
            "surname": null,
            "password": null,
            "email": null,
            "shoppingCart": false,
            "addressesList": [],
            "favoriteProductList": [
                {
                    "id": "62dc17edcf4cc45323bec79c",
                    "createDate": "2022-07-23T15:46:53.923+00:00",
                    "updateDate": "2022-07-23T15:54:43.625+00:00",
                    "categoryId": "62dc17c9cf4cc45323bec79b",
                    "name": "uzun kollo polo",
                    "price": 250,
                    "description": "polo yaka",
                    "deleted": false
                }
            ],
            "deleted": false
        },
     ...
    ]
}
````

---

## FindByNameAndSurname

_Notes_: Method sort = 'surname' : -1

### Request
````
method: GET
url: /user/findByNameAndSurname
requestSample: http://localhost:8080/user/findByNameAndSurname
requestParams: name : String
requestBody: -

````

### Response
````
{
    "success": true,
    "message": "Data Listed :",
    "data": [
        {
            "id": "62def05ab6d11154997a3670",
            "createDate": "2022-07-25T19:34:50.781+00:00",
            "updateDate": "2022-07-25T19:34:50.781+00:00",
            "name": "cahit",
            "surname": "yalçın",
            "password": "12345",
            "email": "cahityalcin@gmail.com",
            "shoppingCart": false,
            "addressesList": [],
            "favoriteProductList": [],
            "deleted": false
        },
        {
            "id": "62debb6368e84c5d099ed94b",
            "createDate": "2022-07-25T15:48:51.466+00:00",
            "updateDate": "2022-07-26T17:43:03.205+00:00",
            "name": "cahit",
            "surname": "sami",
            "password": "12345",
            "email": "cahisami@gmail.com",
            "shoppingCart": false,
            "addressesList": [],
            "favoriteProductList": [
                {
                    "id": "62dc17edcf4cc45323bec79c",
                    "createDate": "2022-07-23T15:46:53.923+00:00",
                    "updateDate": "2022-07-23T15:54:43.625+00:00",
                    "categoryId": "62dc17c9cf4cc45323bec79b",
                    "name": "uzun kollo polo",
                    "price": 250,
                    "description": "polo yaka",
                    "deleted": false
                }
            ],
            "deleted": false
        },
        ...
    ]
}
````

---

## PageableUser

### Request
````
method: GET
url: /user/pageableUser
requestSample: http://localhost:8080/user/pageableUser
requestParams: -
requestBody: -

````

### Response
````
{
    "success": true,
    "message": "Data Listed",
    "data": {
        "content": [
            {
                "id": "62def58cd964a51ef227882c",
                "createDate": "2022-07-25T19:57:00.445+00:00",
                "updateDate": "2022-07-25T19:57:00.445+00:00",
                "name": "cahit",
                "surname": "kuzhun",
                "password": "12345",
                "email": "cahitkuzhun@gmail.com",
                "shoppingCart": false,
                "addressesList": [],
                "favoriteProductList": [],
                "deleted": false
            },
            ...
        ],
        "pageable": {
            "sort": {
                "empty": false,
                "sorted": true,
                "unsorted": false
            },
            "offset": 6,
            "pageNumber": 2,
            "pageSize": 3,
            "unpaged": false,
            "paged": true
        },
        "last": false,
        "totalElements": 15,
        "totalPages": 5,
        "size": 3,
        "number": 2,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "first": false,
        "numberOfElements": 3,
        "empty": false
    }
}
````

---

## Get All User

### Request

````
method: GET
url: /user/getAllUsers
requestSample: http://localhost:8080/user/getAllUsers
requestParams: -
requestBody: -
````
### Response

````
{
    "success": true,
    "message": "Users List",
    "data": [
        {
            "id": "1",
            "createDate": "2022-07-21T11:39:38.645+00:00",
            "updateDate": "2022-07-21T11:39:38.645+00:00",
            "name": erbil,
            "surname": "colak",
            "password": "12345",
            "email": "erbilcolak@gmail.com",
            "shoppingCart": false,
            "addressesList": [],
            "favoriteProductList": [],
            "deleted": false
        },
        ...
    ]
}
````

---

## GetFavoriteProductList

### Request

````
method: GET
url: /user/getFavoriteProductList
requestSample: http://localhost:8080/user/getFavoriteProductList
requestParams: userId : String 
requestBody: -
````

### Response

````
{

"success": true,
    "message": "Favorite product list",
    "data": [
        {
            "id": "1",
            "createDate": "2022-07-21T11:39:38.645+00:00",
            "updateDate": "2022-07-21T11:39:38.645+00:00",
            "categoryId": 2,
            "name": "Mercedes e250",
            "price" : 50000,
            "description" : "sahibinden temiz araç"
           
        },
        ...
    ]
````

---

## GetAddresses

### Request

````
method: GET
url: /user/getAddresses
requestSample: http://localhost:8080/user/getAddresses
requestParams: userId : String 
requestBody: -

````

### Response

````
{
    "success": true,
    "message": "Address list",
    "data": [
        {
            "id": "62dace1e78a7907c81532739",
            "createDate": "2022-07-22T16:19:42.167+00:00",
            "updateDate": "2022-07-22T16:19:42.167+00:00",
            "name": "ev Melih Cevdet",
            "country": "TR",
            "city": "istanbul",
            "description": "kadıköy",
            "userId": "62dace0978a7907c81532738",
            "deleted": false
        },
      ...
    ]
}
````

---

## Change Password

### Request

````
method: PUT
url: /user/changePassword
requestSample: http://localhost:8080/user/changePassword
requestParams: userId : String , currentPassword : String , newPassword : String 
requestBody: -

````

### Request

````
{
    "success": true,
    "message": "password changed",
    "data": null
}
````

---

## Change Email

### Request

````
method: PUT
url: /user/changeEmail
requestSample: http://localhost:8080/user/changeEmail
requestParams: userId : String , email: String 
requestBody: -
````

### Response

````
{
    "success": true,
    "message": "email changed",
    "data": null
}
````

---

## Add to Favorite List

### Request

````
method: PUT
url: /user/addFavoriteList
requestSample: http://localhost:8080/user/addFavoriteList
requestParams: userId : String , productId : String 
requestBody: -
````

### Response

````
{
    "success": true,
    "message": "Product added your favorite list ",
    "data": [
        {
            "id": "1",
            "createDate": "2022-07-21T12:09:21.945+00:00",
            "updateDate": "2022-07-21T12:09:21.945+00:00",
            "categoryId": "62d9412ddf77443e0cbc12e6",
            "name": "Mercedes e250",
            "price": 50000,
            "description": "sahibinden temiz araç",
            "deleted": false
        }
        ]
}

````

---

## Delete User

### Request

````
method: DELETE
url: /user/deleteUser
requestSample: http://localhost:8080/user/deleteUser
requestParams: userId : String  
requestBody: -
````

### Response

````
{
    "success": true,
    "message": "User deleted",
    "data": null
}

````

---

## Create Shopping Cart

### Request

````
method: POST
url: /shoppingCart/createShoppingCart
requestSample: http://localhost:8080/shoppingCart/createShoppingCart
requestParams: - 
requestBody:{
 userId : String
}

````

### Response

````
{
    "success": true,
    "message": "Shopping Cart is ready",
    "data": {
        "id": "62db0ba90eaf3a282c22aa03",
        "userId": "62db0b9c0eaf3a282c22aa02",
        "shoppingCartProductList": [],
        "total": 0
    }
}
````

---

## Get Shopping Cart Product List

### Request

````
method: GET
url: /shoppingCart/getShoppingCartProductList
requestSample: http://localhost:8080/getShoppingCartProductList
requestParams: shoppingCartId : String 
requestBody: -
````

### Response

````
{
    "success": true,
    "message": "Shopping Cart Product List",
    "data":  [
        {
            "id": "1",
            "createDate": "2022-07-21T12:09:21.945+00:00",
            "updateDate": "2022-07-21T12:09:21.945+00:00",
            "categoryId": "62d9412ddf77443e0cbc12e6",
            "name": "Mercedes e250",
            "price": 50000,
            "description": "sahibinden temiz araç",
            "deleted": false
        }
        ...
        ]
}
````

---

## Remove Item From Shopping Cart

### Request

````
method: PUT
url: /shoppingCart/removeItemFromShopping
requestSample: http://localhost:8080/removeItemFromShopping
requestParams: shoppingCartId : String , productId : String , count : int 
requestBody: -
````

### Response

````
{
    "success": true,
    "message": "Product removed at cart",
    "data": null
}
````

---

## Add Product to Shopping Cart

### Request

````
method: PUT
url: /shoppingCart/addProductToShoppingCart
requestSample: http://localhost:8080/addProductToShoppingCart
requestParams: shoppingCartId : String , productId : String , count : int 
requestBody: -

````

### Response

````
{
    "success": true,
    "message": "Product(s) added your cart",
    "data": null
}
````

---

## Reset Cart

### Request

````
method: PUT
url: /shoppingCart/resetCart
requestSample: http://localhost:8080/resetCart
requestParams: shoppingCartId : String 
requestBody: -

````

### Response

````
{
    "success": true,
    "message": "Cart is empty",
    "data": null
}
````

---

## Create Product

### Request

````
method: POST
url: /product/createProduct
requestSample: http://localhost:8080/resetCart
requestParams: -
requestBody: {
    categoryId : String ,
    name : String ,
    price : int ,
    description : String 
}

````

### Response

````
{
    "success": true,
    "message": "Product created",
    "data": null
}
````

---

## Get Product

### Request

````
method: GET
url: /product/getProduct
requestSample: http://localhost:8080/product/getProduct
requestParams: productId : String 
requestBody: -
````

### Response

````
{
    "success": true,
    "message": "Product is listed",
    "data":  [
        {
            "id": "1",
            "createDate": "2022-07-21T12:09:21.945+00:00",
            "updateDate": "2022-07-21T12:09:21.945+00:00",
            "categoryId": "62d9412ddf77443e0cbc12e6",
            "name": "Mercedes e250",
            "price": 50000,
            "description": "sahibinden temiz araç",
            "deleted": false
        }
        ]
}
````

---

## Get All Product

### Request

````
method: GET
url: /product/getAllProduct
requestSample: http://localhost:8080/product/getAllProduct
requestParams: -
requestBody: -

````

### Response

````
{
    "success": true,
    "message": "Product is listed",
    "data":  [
        {
            "id": "1",
            "createDate": "2022-07-21T12:09:21.945+00:00",
            "updateDate": "2022-07-21T12:09:21.945+00:00",
            "categoryId": "62d9412ddf77443e0cbc12e6",
            "name": "Mercedes e250",
            "price": 50000,
            "description": "sahibinden temiz araç",
            "deleted": false
        }
        ...
        ]
}


````

---

## Change Product Name

### Request

````
method: PUT
url: /product/changeProductName
requestSample: http://localhost:8080/product/changeProductName
requestParams: productId : String , productName : String
requestBody: -
````

### Response

````
{
    "success": true,
    "message": "Product name changed",
    "data": null
}

````

---

## Change Product Price

### Request

````
method: PUT
url: /product/changeProductPrice
requestSample: http://localhost:8080/product/changeProductPrice
requestParams: productId : String , productPrice : int
requestBody: -


````

### Response

````
{
    "success": true,
    "message": "Product price changed",
    "data": null
}
````

---

## Change Product Category 

### Request

````
method: PUT
url: /product/changeProductCategory
requestSample: http://localhost:8080/product/changeProductCategory
requestParams: productId : String , categoryId : String
requestBody: -
````

### Response

````
{
    "success": true,
    "message": "Product category is changed",
    "data": null
}

````

---

## Delete Product

### Request

````
method: DELETE
url: /product/deleteProduct
requestSample: http://localhost:8080/product/deleteProduct
requestParams: productId : String 
requestBody: -

````

### Response

````
{
    "success": true,
    "message": "Product deleted",
    "data": null
}

````

---

## Create Order

### Request

````
method: POST
url: /order/createOrder
requestSample: http://localhost:8080/createOrder
requestParams: shoppingCartId : String, delivieryAddressId : String, invoiceAddressId : String
requestBody: -

````

### Response

````
{
    "success": true,
    "message": "You have extra discount % 10...fill cart get more discount",
    "data": {
        "id": "62dc033b2f699e722b6417e3",
        "createDate": "2022-07-23T14:18:35.799+00:00",
        "updateDate": "2022-07-23T14:18:35.799+00:00",
        "orderProductList": [
            {
                "id": "62daff54d1ba7f5818dbfaed",
                "createDate": "2022-07-22T19:49:40.389+00:00",
                "updateDate": "2022-07-22T19:49:40.389+00:00",
                "categoryId": "62d9412ddf77443e0cbc12e6",
                "name": "seat cordoba",
                "price": 20000,
                "description": "temiz araç",
                "deleted": false
            },
            ...
        ],
        "discountTotal": 8000.0,
        "shoppingCartId": "62db15260fca4522e35f8050",
        "userId": "62db151b0fca4522e35f804f",
        "deliveryAddressId": "62dbff80d84cc70d2ca70216",
        "invoiceAddressId": "62dbff80d84cc70d2ca70216",
        "status": "WAITING",
        "deleted": false
    }
}
````

---

## Get Order Product List

### Request

````
method: GET
url: /order/getOrderProductList
requestSample: http://localhost:8080/order/getOrderProductList
requestParams: orderId : String,
requestBody: -

````

### Response

````
{
    "success": true,
    "message": "Order product list",
    "data": [ {
            "id": "1",
            "createDate": "2022-07-19T12:09:21.945+00:00",
            "updateDate": "2022-07-21T12:09:21.945+00:00",
            "discountTotal": "10000",
            "shoppingCartId": "1",
            "userId": 2,
            "deliveryAddressId": "2",
            "invoiceAddressId" : "2",
            "status" : "WAITING"
        }
        ...
    ]
}
````

---

## Edit Order Addresses

### Request

````
method: PUT
url: /order/editOrderAddresses
requestSample: http://localhost:8080/order/editOrderAddresses
requestParams: orderId : String, deliveryAddressId : String , invoiceAddressId : String
requestBody: -

````

### Response

````
{
    "success": true,
    "message": "Addresses changed",
    "data": null
}

````

---

## Complete Order

### Request

````
method: PUT
url: /order/completeOrder
requestSample: http://localhost:8080/order/completeOrder
requestParams: orderId : String
requestBody: -
````

### Response

````
{
    "success": true,
    "message": "Order completed",
    "data": null
}
````

---

## Cancel Order

### Request

````
method: DELETE
url: /order/cancelOrder
requestSample: http://localhost:8080/order/cancelOrder
requestParams: orderId : String
requestBody: -


````

### Response

````
{
    "success": true,
    "message": "Order canceled",
    "data": null
}

````

---

## Create Category

### Request

````
method: POST
url: /category/createCategory
requestSample: http://localhost:8080/createCategory
requestParams: -
requestBody: {
    name : String,
    patrentCategoryId : String
}

````

### Response

````
{
    "success": true,
    "message": "Category is created",
    "data": {
        "id": "1",
        "createDate": "2022-07-22T13:22:08.268+00:00",
        "updateDate": "2022-07-22T13:22:08.268+00:00",
        "name": "ev",
        "parentCategoryId": 2,
        "deleted": false
    }
}
````

---

## Get All Category

### Request

````
method: GET
url: /category/getAllCategory
requestSample: http://localhost:8080/getAllCategory
requestParams: -
requestBody: -
````

### Response

````
{
    "success": true,
    "message": "All categories listed",
    "data": [
        {
            "id": "1",
            "createDate": "2022-07-21T12:06:05.491+00:00",
            "updateDate": "2022-07-21T12:06:05.491+00:00",
            "name": "araba",
            "parentCategoryId": 3,
            "deleted": false
        },
        {
            "id": "2",
            "createDate": "2022-07-22T13:22:08.268+00:00",
            "updateDate": "2022-07-22T13:22:08.268+00:00",
            "name": "ev",
            "parentCategoryId": 4,
            "deleted": false
        }
        ....
    ]
}
````

---

## Create Address

### Request

````
method: POST
url: /address/createAddress
requestSample: http://localhost:8080/address/createAddress
requestParams: -
requestBody: {
    name : String,
    country : String ,
    city : String ,
    description : String,
    userId : String
}
````

### Response

````
{
    "success": true,
    "message": "Address created",
    "data": {
        "id": "1",
        "createDate": "2022-07-22T10:27:24.837+00:00",
        "updateDate": "2022-07-22T10:27:24.837+00:00",
        "name": "ev",
        "country": "TR",
        "city": "istanbul",
        "description": "kadıköy",
        "userId": "2",
        "deleted": false
    }
}
````

---

## Edit Address

### Request

````
method: PUT
url: /address/editAddress
requestSample: http://localhost:8080/address/editAddress
requestParams: addressId : String , country : String , city : String , description : Sting
requestBody: -

````

### Response

````
{
    "success": true,
    "message": "Address changed",
    "data": {
        "id": "2",
        "createDate": "2022-07-22T13:33:22.682+00:00",
        "updateDate": "2022-07-22T13:35:20.452+00:00",
        "name": "iş",
        "country": "TR",
        "city": "İstanbul",
        "description": "Mecidiyeköy",
        "userId": "3",
        "deleted": false
    }
}
````

---

## Delete Address

### Request

````
method: DELETE
url: /address/deleteAddress
requestSample: http://localhost:8080/address/deleteAddress
requestParams: -
requestBody:
````

### Response

````
{
    "success": true,
    "message": "Address deleted",
    "data": null
}
````

---
