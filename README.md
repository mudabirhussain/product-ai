# Product Service - AI Powered E-commerce Backend

This demo repository represents the **backend product service**, which is part of an imaginary e-commerce platform. It integrates AI-powered functionalities such as:

- **Sustainability Check**: Evaluates the sustainability of products based on environmental factors.
- **AI-Powered Product Inquiry Bot**: Allows customers to inquire about products in a conversational manner.

## Technologies Used

- **Java 21**
- **PGVector Database**
- **Spring Boot**
- **Spring AI**
- **Spring Data JDBC**

## Getting Started

### Prerequisites

- Docker and Docker Compose installed
- Java 21 or higher

### Clone the Repository

To get started, clone the repository:

```bash
git clone https://github.com/your-repo/product-service-demo.git
cd product-service-demo
```

### Database Setup with Docker

The PostgreSQL database with the **PGVector** extension will be set up automatically via **Docker Compose** when the application is run for the first time.

1. **Run the Application**  
   On the first run, Docker Compose will automatically create a PostgreSQL container with PGVector installed. The database schema, including the `product` table, will be created automatically.

   Run the application with the IntelliJ

   Docker Compose will take care of starting the necessary services PGVector.

2. **Verify Product Table Creation**  
   After running the application, verify that the `product` table was created in the database by connecting to PostgreSQL via Docker and running the following SQL command:

   ```sql
   SELECT * FROM product;
   ```

3. **Insert Dummy Product Data**  
   Once the `product` table is created, insert dummy products using the provided `dump.sql` file.

### Vector Initialization

To initialize vector data for the first time, you need to modify the hardcoded logic in the project's AIConfig class file in the configs package.

1. Open the class code and locate the following configuration:

   ```java
   // Make it true, when initializing data in vector.
   boolean vectorInitialization = false;
   ```

2. Change the value of `vectorInitialization` to `true`:

   ```java
   boolean vectorInitialization = true;
   ```

3. **Run the Application Again**  
   After changing the flag, run the application again to initialize the vector data.

   The vector data will now be initialized based on your product data, you can confirm in the logs.

4. **Disable Vector Initialization**  
   After the vector data is initialized, revert the `vectorInitialization` flag back to `false` to avoid repeated initialization attempts:

   ```java
   boolean vectorInitialization = false;
   ```

### Final Steps

Once the above steps are completed, the application is all set and ready to perform the following key functionalities:

- AI-powered sustainability checks for products
- AI-powered product inquiry bot for e-commerce platforms

## Future Improvements

- The vector initialization logic is currently hardcoded but can be automated in future versions to eliminate manual intervention.
