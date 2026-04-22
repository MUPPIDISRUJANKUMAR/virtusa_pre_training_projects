CREATE DATABASE online_retail_db;
USE online_retail_db;

-- Customers Table
CREATE TABLE Customers(
customer_id int PRIMARY KEY,
name VARCHAR(50) NOT NULL,
city VARCHAR(50));

-- Products Table
CREATE TABLE Products(
product_id int PRIMARY KEY,
name VARCHAR(50) NOT NULL,
category VARCHAR(50),
price DECIMAL(10,2) NOT NULL);

-- Orders Table
CREATE TABLE Orders(
order_id int PRIMARY KEY,
customer_id  int,
order_date DATE,
FOREIGN KEY(customer_id) REFERENCES Customers(customer_id)); 

-- Order_items Table
CREATE TABLE Order_items(
order_id int,
product_id int,
quantity int NOT NULL,
FOREIGN KEY(order_id) REFERENCES Orders(order_id),
FOREIGN KEY(product_id) REFERENCES Products(product_id),
PRIMARY KEY(order_id,product_id));
