# Web Shop
## PROJECT DESCRIPTION  
> App represents web shop.
>
> Shop have layer for administrator and customer.
>
> Administrator control products in store and manage orders by customers.
>
> Customer can choose any goods in store and add it to shopping cart for creating order.
## FEATURES 💡 :

### Application have two different layer for: admin 👑 and for Customer 👤
> #### Administrator layer
>> * Have its own design
>> * Registration 📝 & authentication 🔒 of admins 👑
>> * Administrator can:
>>> * create category and products and store it in DB 🛢
>>> * update, activate/deactivate any category or product
>>> * manage created orders by Customers


> #### Store layer
>> Store:
>> * Have its own design
>> * Shows all products
>> * Shows more detail one product
>> * Give possibility sort products by:
>> * * price
>> * * category
>>
>> Customer:
>> * Registration 📝 & authentication 🔒 of Customers 👤
>> * adding products to shopping cart 🛢
>> * changing products in cart (change quantity or delete it)
>> * creating orders 💸
>> * changing own information


## PROJECT STRUCTURE 📚 :
>**Project use 3-tier Architecture:**
>* Controller layer - gives user control under web-application (depends on his role)
>* Service layer - holds all business logic.
>* DAO layer - is responsible for communicating with the database by using CRUD methods.

## TECHNOLOGIES 🧬 :
* Spring Boot
* Spring MVC
* Spring Security
* Spring Data Jpa
* Hibernate
* MySQL
* Thymeleaf
* Bootstrap

## INSTRUCTIONS FOR LAUNCHING THE PROJECT 🗺️ :
1. Fork my repository
2. git clone
3. Edit application.properties file in admin and customer module.
4. Set the necessary parameters in :
    ```
   spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_SHEMA_NAME
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORDD
    ```
5. Run project



