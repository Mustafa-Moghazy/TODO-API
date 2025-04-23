# TODO-API

A Spring Boot-based REST API for managing users and their to-do tasks. The API provides endpoints to create, update, retrieve, and delete users and their respective to-do tasks.

## Version two Features 

- User authentication with **Basic Auth**
- Role-based access (`ROLE_USER`, `ROLE_ADMIN`)
- Secure endpoints using `SecurityFilterChain`
- BCrypt password encoding
- Preloaded default admin user (`admin:admin123`)
- CSRF disabled for testing (with POST support)

## Tech Stack

- **Backend**: Java, Spring Boot
- **Data Validation**: Jakarta Bean Validation (JSR 380)
- **Database**:  MySQL
- **Data Transfer**: DTOs (Data Transfer Objects) for request and response

## Getting Started

1. **Clone the repository**

   ```bash
   git clone https://github.com/yourusername/todo-api.git
   cd todo-api
   ```
2. **Update Database Configuration**
   #### Edit the application.properties file to configure your database connection:
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
   spring.datasource.username=yourUsername
   spring.datasource.password=yourPassword
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Import the project into your preferred IDE (e.g., IntelliJ IDEA, Eclipse)**.
   
4. **Build and run the application from your IDE**.

5. > **Note:** Alternatively, you can package it into a jar and run it:
   ```bash
     mvn clean package
     java -jar target/todo-api-0.0.1-SNAPSHOT.jar
   ```

## 🧪 Default Admin User

When the app starts, it creates a default admin user:

- **Username**: `admin`  
- **Password**: `admin123`  
- **Role**: `ROLE_ADMIN`

Use this account to test secure endpoints that require admin access.


## API Endpoints

### Users

| Method | Endpoint             | Description           |
|--------|----------------------|-----------------------|
| GET    | `/api/users`         | Get all users         |
| GET    | `/api/users/{id}`    | Get user by ID        |
| POST   | `/api/users`         | Create new user       |
| PUT    | `/api/users/{userId}`| Update user by ID     |
| DELETE | `/api/users/{userId}`| Delete user by ID     |

### To-Dos

| Method | Endpoint                     | Description                |
|--------|------------------------------|----------------------------|
| GET    | `/api/todos`                 | Get all to-dos             |
| GET    | `/api/todos/{todoId}`        | Get to-do by ID            |
| GET    | `/api/todos/user/{userId}`   | Get all to-dos by user ID  |
| POST   | `/api/todos`                 | Create new to-do           |
| PUT    | `/api/todos/{todoId}`        | Update to-do by ID         |
| DELETE | `/api/todos/{todoId}`        | Delete to-do by ID         |



