🍔 Online Food Ordering System

 📌 Description
This project is a Java-based application that allows users to order food online.  
Users can register, login, select multiple food items, and place orders.

🛠 Technologies Used
- Java (Swing GUI)
- MySQL Database
- JDBC Connectivity

✨ Features
- User Registration & Login
- Menu Display from Database
- Multiple Item Selection
- Total Bill Calculation
- Order Placement System

---
🗄 Database Setup

 Create Database:
```sql
CREATE DATABASE food_ordering;
USE food_ordering;
Users Table:
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(50),
    password VARCHAR(50)
);
Menu Table:
CREATE TABLE menu (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    price DOUBLE
);
Orders Table:
CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    total DOUBLE,
    status VARCHAR(20)
);

▶️ How to Run
Open project in NetBeans
Connect MySQL database
Run Main.java
Login/Register
Select food items and place order
