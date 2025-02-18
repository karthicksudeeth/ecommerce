# E-Commerce Application

This project is a complete **E-commerce application** built using **Spring Boot** for the backend and **React with Bootstrap** for the frontend. It provides APIs to manage items, carts, orders, and discounts, along with a user-friendly interface for interacting with the system.

## Features

### Backend
- **Item Management**: Add, retrieve, and list items available for sale.
- **Cart Management**: Create a cart, add items to the cart, and calculate total.
- **Order Processing**: Checkout an order, apply discount codes, and generate orders.
- **Discount System**: Generates discount codes for every nth order through the admin API and applies discounts.
- **API Documentation**: Integrated with **Swagger UI** for testing APIs.

### Frontend
- **Item Display**: Lists all available items with images, descriptions, and prices.
- **Cart Functionality**: Allows users to add/remove items and update quantities.
- **Checkout System**: Handles discount codes and order confirmation.
- **Responsive UI**: Styled using **Bootstrap** for a modern and mobile-friendly experience.

## Tech Stack

### Backend
- **Spring Boot**: Framework for building Java-based web applications.
- **Spring Data JPA**: ORM (Object Relational Mapping) for database operations.
- **H2 Database**: In-memory database for testing purposes.
- **Swagger/OpenAPI**: API documentation and testing interface.
- **Lombok**: Reduces boilerplate code for model classes.

### Frontend
- **React.js**: JavaScript library for building UI components.
- **Bootstrap**: UI framework for styling.
- **Axios**: For making API requests to the backend.
- **React Router**: Handles frontend routing between pages.

## Getting Started

### Prerequisites

- **Backend**:
  - JDK 21 or higher
  - Maven 3.8+
  - IDE like IntelliJ IDEA or Eclipse (optional)
- **Frontend**:
  - Node.js 18+
  - npm or yarn

### Backend Installation

1. **Build the backend**:
    ```bash
    mvn clean install
    ```

2. **Run the backend**:
    ```bash
    mvn spring-boot:run
    ```

    The backend server will start running on `http://localhost:8080`.

3. **Access Swagger UI**:
    Swagger UI is available at `http://localhost:8080/swagger-ui.html` for easy API testing.

4. **H2 Database Console**:
    Available at `http://localhost:8080/h2-console`.

### Frontend Installation

1. Navigate to the `frontend` directory:
    ```bash
    cd frontend
    ```

2. Install dependencies:
    ```bash
    npm install
    ```

3. Start the frontend server:
    ```bash
    npm start
    ```

    The frontend application will run at `http://localhost:3000`.

## Available Endpoints

### Backend API

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

## Testing

### Backend Tests
Run unit tests using:
```bash
mvn test
```

### Frontend Tests
Run frontend tests using:
```bash
npm test
```