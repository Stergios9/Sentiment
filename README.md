# Sentiment Application

The Sentiment application is an online psychological support application for citizens. It provides a medical diagnosis, relaxation methods, and approaches to managing potential illness, and refers users to a mental health specialist located in the patient's area. 
The application uses **Spring Boot** and **JPA** for its backend, while the data is stored in an in-memory **H2 Database**.

## Features

- Analyze user sentiment based on thoughts.
- Store and manage reviews in an H2 database.
- REST API endpoints for submitting and viewing thoughts.

## Technologies Used

- **Java 11+**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Boot MVC**
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
    - Description: Submit a new thought to analyze the sentiment.
  
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
