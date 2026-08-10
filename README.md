# 📚 BookHive – Bookstore Management System

BookHive is a web-based **Bookstore Management System** developed using **Java Spring Boot, Spring Data JPA, MySQL, Thymeleaf, and Bootstrap**.

The application provides separate functionality for **Users** and **Administrators**. Users can browse books and maintain their personal book collection, while administrators can manage books, users, and orders.

---

## 🚀 Features

### 👤 User Features

* User registration
* Email validation during registration
* User login and logout
* Role-based access
* Browse available books
* Add books to **My Books**
* View personal book collection
* Edit saved books
* Delete saved books

### 👨‍💼 Admin Features

* Admin-specific navigation and functionality
* Add new books
* View available books
* Edit books
* Delete books
* View registered users
* Delete users
* Create/place orders
* Select a user while creating an order
* Select books belonging to the selected user
* Calculate book total
* Select shipping option
* Calculate final order total
* View all orders
* Update order status
* Edit orders
* Delete orders

### 📦 Order Management

Orders contain:

* Order ID
* User
* Selected books
* Total price
* Shipping option
* Order status
* Order date

Available order statuses:

* Pending
* Shipped
* Completed

Shipping options:

* Standard – ₹5
* Express – ₹15

---

## 🛠️ Technologies Used

| Technology               | Purpose                         |
| ------------------------ | ------------------------------- |
| Java 17                  | Programming language            |
| Spring Boot 3.5.7        | Backend framework               |
| Spring MVC               | Web application and controllers |
| Spring Data JPA          | Database interaction            |
| Hibernate                | ORM                             |
| MySQL                    | Database                        |
| Thymeleaf                | Server-side HTML rendering      |
| Thymeleaf Layout Dialect | Page layout management          |
| Bootstrap 5              | UI styling                      |
| HTML5                    | Frontend structure              |
| CSS3                     | Custom styling                  |
| JavaScript               | Frontend functionality          |
| jQuery                   | AJAX requests                   |
| Maven                    | Dependency and build management |

---

## 🏗️ Project Architecture

The project follows a layered Spring Boot architecture:

```text
src/main/java/com/book
│
├── controller
│   ├── BookController
│   ├── MyBookListController
│   ├── OrderController
│   └── UserController
│
├── model
│   ├── Book
│   ├── MyBookList
│   ├── Order
│   └── User
│
├── repository
│   ├── BookRepository
│   ├── MyBookListRepository
│   ├── OrderRepository
│   └── UserRepository
│
└── service
    └── impl
        ├── BookServiceImpl
        ├── MyBookListServiceImpl
        ├── OrderServiceImpl
        └── UserServiceImpl
```

The frontend templates are located under:

```text
src/main/resources/templates
```

Static resources such as CSS and images are located under:

```text
src/main/resources/static
```

---

## 🗄️ Database

The application uses **MySQL**.

Database name:

```text
bookstore_management
```

The application is configured to connect to:

```text
jdbc:mysql://localhost:3306/bookstore_management
```

Hibernate automatically updates the database structure using:

```properties
spring.jpa.hibernate.ddl-auto=update
```

The main database entities are:

```text
User
Book
MyBookList
Order
```

### Entity Relationships

```text
User
 │
 ├───────────────┐
 │               │
 ▼               ▼
MyBookList      Order
                  │
                  ▼
              MyBookList
```

A user can have multiple books in their personal collection.

A user can also have multiple orders.

An order can contain multiple books from the user's collection.

---

## 🔐 User Roles

The application currently supports two roles:

### USER

A registered user receives the role:

```text
USER
```

Users can:

* Browse books
* Add books to My Books
* View their books
* Edit their books
* Delete their books

### ADMIN

Administrators receive the role:

```text
ADMIN
```

Administrators can manage:

* Books
* Users
* Orders

---

## 🌐 Main Pages

| Page              | URL                | Purpose                         |
| ----------------- | ------------------ | ------------------------------- |
| Home              | `/`                | Application homepage            |
| About             | `/about`           | About page                      |
| Register          | `/regForm`         | User registration               |
| Login             | `/loginForm`       | User login                      |
| Available Books   | `/available_books` | View available books            |
| My Books          | `/my_books`        | User's personal book collection |
| Book Registration | `/book_register`   | Add a new book                  |
| Users             | `/available_users` | Manage users                    |
| Create Order      | `/createOrder`     | Create an order                 |
| Orders            | `/orders`          | Manage orders                   |
| Logout            | `/logout`          | Logout current user             |

---

## ⚙️ Prerequisites

Before running the project, make sure the following are installed:

* Java 17 or higher
* MySQL Server
* Maven
* IDE such as IntelliJ IDEA, Eclipse, or Spring Tool Suite
* Git

---

## 🗃️ Database Setup

Start your MySQL server and create the database:

```sql
CREATE DATABASE bookstore_management;
```

You can verify it using:

```sql
SHOW DATABASES;
```

Then select the database:

```sql
USE bookstore_management;
```

Hibernate will create/update the required tables when the Spring Boot application starts.

---

## ⚙️ Application Configuration

The project uses the following database configuration:

```properties
spring.application.name=bookstore-management

spring.datasource.url=jdbc:mysql://localhost:3306/bookstore_management
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Important

Replace:

```text
YOUR_MYSQL_PASSWORD
```

with your local MySQL password.

**Do not upload your actual database password to GitHub.**

A better approach is to keep your real password in a local configuration file or environment variable and provide a safe example configuration in the repository.

---

## ▶️ How to Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/bookstore-management.git
```

### 2. Open the project

Open the project in:

* IntelliJ IDEA
* Eclipse
* Spring Tool Suite
* VS Code

### 3. Configure MySQL

Make sure MySQL is running and the database exists:

```text
bookstore_management
```

### 4. Configure database credentials

Update your local `application.properties` with your MySQL username and password.

### 5. Run the Spring Boot application

Run the main Spring Boot application class from your IDE.

Or use Maven:

```bash
mvn spring-boot:run
```

### 6. Open the application

After the application starts, open:

```text
http://localhost:8080
```

---

## 🔄 Application Flow

### New User

```text
Home
  ↓
Register
  ↓
Create Account
  ↓
Login
  ↓
USER Dashboard
  ↓
Available Books
  ↓
Add to My Books
  ↓
My Books
```

### Admin

```text
Login
  ↓
ADMIN Account
  ↓
Manage Books
  ├── Add Book
  ├── Edit Book
  └── Delete Book

Manage Users
  └── Delete User

Manage Orders
  ├── Create Order
  ├── Edit Order
  ├── Update Status
  └── Delete Order
```

---

## 📋 Example Order Workflow

An administrator can create an order by:

1. Selecting a user.
2. Loading books associated with that user.
3. Selecting one or more books.
4. Selecting a shipping option.
5. Calculating the order total.
6. Placing the order.

### Shipping Charges

```text
Standard → ₹5
Express  → ₹15
```

The final order amount is calculated as:

```text
Book Total + Shipping Cost = Final Order Total
```

---

## 🎨 Frontend

The application uses:

* Thymeleaf templates
* Bootstrap 5
* Font Awesome
* JavaScript
* jQuery

The homepage includes a book-themed carousel and responsive navigation.

---

## 🔒 Current Security Note

This project currently implements login using a custom session-based approach.

Passwords are currently handled directly by the application rather than using password hashing.

For a production application, the authentication system should be improved using:

* Spring Security
* BCrypt password hashing
* Proper authorization
* Secure session management
* CSRF protection
* Environment-based secret configuration

---

## 🔮 Future Improvements

Possible future improvements include:

* Spring Security authentication
* BCrypt password encryption
* Better role-based authorization
* Shopping cart functionality
* Book search and filtering
* Book categories and genres
* Book cover/image upload
* Pagination
* Improved order management
* Order history for users
* Stock management
* Payment gateway integration
* REST API
* Exception handling
* Unit and integration testing
* Environment-based database configuration
* Docker support
* Deployment to a cloud server

---

## 📸 Screenshots

Screenshots can be added here after uploading them to the repository.

Example:

```text
## Screenshots

### Home Page
![Home Page](screenshots/home.png)

### Login
![Login](screenshots/login.png)

### Available Books
![Available Books](screenshots/available-books.png)

### My Books
![My Books](screenshots/my-books.png)

### Order Management
![Order Management](screenshots/orders.png)
```

---

## 📁 Project Structure

```text
bookstore-management/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── book/
│   │   │           ├── controller/
│   │   │           ├── model/
│   │   │           ├── repository/
│   │   │           └── service/
│   │   │               └── impl/
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   └── img/
│   │       │
│   │       ├── templates/
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

## 👨‍💻 Project

**Project Name:** BookHive – Bookstore Management System

**Application:** `bookstore-management`

**Database:** `bookstore_management`

**Backend:** Spring Boot

**Frontend:** Thymeleaf + Bootstrap

**Database:** MySQL

---

## 📄 License

This project is created for learning and educational purposes.
