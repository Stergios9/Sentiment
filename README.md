# Sentiment Application

The **Sentiment Application** is a psychological support platform that analyzes user reviews and provides emotional sentiment feedback. The application uses **Spring Boot** and **JPA** for its backend, while the data is stored in an in-memory **H2 Database**.

## Features

- Analyze user sentiment based on reviews.
- Store and manage reviews in an H2 database.
- REST API endpoints for submitting and viewing reviews.

## Technologies Used

- **Java 11+**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database**
- **Maven**

## Getting Started

### Running the Application

1. Clone the repository:
    ```bash
    git clone https://github.com/Stergios9/Sentiment.git
    cd Sentiment
    ```

2. Build the project using Maven:
    ```bash
    mvn clean install
    ```

3. Run the application:
    ```bash
    mvn spring-boot:run
    ```

4. The application will be accessible at:
    ```
    http://localhost:8080
    ```

### Endpoints

- **Submit Review**: 
    - URL: `http://localhost:8080/reviews/submit`
    - Description: Submit a new review to analyze the sentiment.
  
- **H2 Console**:
    - URL: `http://localhost:8080/h2-console`
    - Database URL: `jdbc:h2:mem:testdb`
    - Default Username: `sa`
    - Default Password: *no password*

### H2 Database Access

To access the in-memory H2 database, visit the following URL in your browser:

- [H2 Console](http://localhost:8080/h2-console)

- Database URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

### Example Request

To submit a review, you can use the following `POST` request:

```bash
curl -X POST http://localhost:8080/reviews/submit \
    -H "Content-Type: application/json" \
    -d '{
          "review": "The product was excellent, very happy with the purchase!"
        }'
