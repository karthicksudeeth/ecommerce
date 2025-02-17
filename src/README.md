# E-Commerce Backend

This project is a simple E-commerce backend built using **Spring Boot** and **JPA** with **H2** in-memory database. It provides APIs to manage items, carts, orders, and discounts. Additionally, it supports Swagger UI for API documentation.

## Features

- **Item Management**: Add, retrieve, and list items available for sale.
- **Cart Management**: Create a cart, add items to the cart, and calculate total.
- **Order Processing**: Checkout an order, apply discount codes, and generate orders.
- **Discount System**:  Generates discount codes for every nth order through the admin api and applies discounts.

## Tech Stack

- **Spring Boot**: Framework for building Java-based web applications.
- **Spring Data JPA**: ORM (Object Relational Mapping) for database operations.
- **H2 Database**: In-memory database for testing purposes.
- **Swagger/OpenAPI**: API documentation and testing interface.
- **Lombok**: Reduces boilerplate code for model classes.

## Getting Started

### Prerequisites

- JDK 21 or higher
- Maven 3.8+
- IDE like IntelliJ IDEA or Eclipse (optional)

### Installation


1. **Build the project**:
    ```bash
    mvn clean install
    ```

2. **Run the application**:
    ```bash
    mvn spring-boot:run
    ```

    The backend server will start running on `http://localhost:8080`.

3. **Access Swagger UI**:
    Swagger UI is available at `http://localhost:8080/swagger-ui.html` for easy testing of the API endpoints.

4. **H2 Db console**:
    H2 in-memory database is available at `http://localhost:8080/h2-console`.

### Available Endpoints

#### Admin
- **POST** `/admin/generateDiscount`: Generate a discount code for every nth order (currently every 5th order).
- **GET** `/admin/analytics`: Get basic analytics, e.g., total orders count.

#### Cart
- **POST** `/cart/create`: Create a new shopping cart.
- **POST** `/cart/{cartId}/add`: Add items to an existing cart.
- **GET** `/cart/{cartId}/total`: Get the total price of the items in the cart.
- **GET** `/cart/{cartId}`: Get the details of the cart.

#### Item
- **POST** `/items`: Add multiple items to the database.
- **GET** `/items`: Retrieve all available items.
- **GET** `/items/{id}`: Retrieve a specific item by its ID.

#### Order
- **POST** `/orders/checkout/{cartId}`: Checkout an order from the cart, optionally apply a discount code.

## Database

- **H2 Database** is used for storing cart, order, and item data. The H2 console is available at `http://localhost:8080/h2-console` for testing.
- The application uses JPA to interact with the database, and Hibernate will manage the schema creation.

## Testing

 Run unit tests using the following command:

```bash
mvn test
```