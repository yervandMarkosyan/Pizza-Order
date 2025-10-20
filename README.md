# 🍕 Spring Boot Pizza Order App

A full-stack web application built with Java Spring Boot and Thymeleaf for server-side rendering. This project simulates a pizza ordering system with dynamic ingredient selection, form validation, and order persistence using JDBC and an H2 in-memory database. It follows a clean MVC architecture and demonstrates custom SQL repositories, session-based order tracking, and intuitive UI rendering.

## 🚀 Technologies Used

- Java 21+
- Spring Boot
- Thymeleaf
- Spring MVC
- Spring JDBC
- H2 Database
- Lombok
- Jakarta Validation

## 📦 How to Run Locally

### 1. Clone the repository

```bash
git clone https://github.com/your-username/springboot-pizza-order-app.git
cd springboot-pizza-order-app
```

### 2. Build the project

```bash
./mvnw clean install
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

### 4. Access the app

```bash
http://localhost:8080/design
```
## 🧪 Features

- 🍕 Build-your-own pizza form with categorized ingredients (crust, sauce, cheese, toppings)
- 🧾 Order summary with delivery and payment details
- 🗃️ Custom JDBC repository for saving orders and pizzas
- 🧠 Ingredient filtering by type using model attributes
- 🛠️ Form validation for name and ingredient selection
- 🧵 Session-based order tracking across multiple pizzas

## 🛠️ Developer Notes

- H2 Console available at `http://localhost:8080/h2-console`
- Default JDBC URL: `jdbc:h2:mem:yervandPizza`
- Tables created manually via SQL or repository logic
- Logging via SLF4J (`log.info(...)`)
