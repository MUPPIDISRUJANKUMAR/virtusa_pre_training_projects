-- Customers Data
INSERT INTO Customers VALUES
(1,'Rahul','Hyderbad'),
(2,'Arun Kumar','Warangal'),
(3,'Uday kiran','Adilabad'),
(4,'Srujan Kumar','Hyderbad'),
(5,'Rishik Reddy','Chennai');

-- Products Data
INSERT INTO Products VALUES
(201,'Laptop','Electronics',70000),
(202,'Phone','Electronics',28000),
(203,'Headphones','Electronics',999),
(204,'Shoes','Fashion',4000),
(205,'T-Shirt','Fashion',1600),
(206,'Watch','Accessories',7500);

-- Orders Data 
INSERT INTO Orders VALUES
(1001,1,'2026-01-10'),
(1002,2,'2024-02-15'),
(1003,1,'2024-03-05'),
(1004,3,'2024-03-20'),
(1005,4,'2024-04-01');

-- Order items 
INSERT INTO Order_items VALUES
(1001,201,1),
(1001,203,2),
(1002,202,5),
(1002,205,3),
(1003,204,1),
(1003,206,2),
(1004,203,3),
(1005,201,4),
(1005,204,2);

select * from customers;
SELECT * FROM Products;
SELECT * from Orders;
SELECT * from Order_items;
