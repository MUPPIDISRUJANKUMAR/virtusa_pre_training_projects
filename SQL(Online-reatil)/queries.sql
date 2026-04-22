-- 1. top-selling products
-- Simple Join Approach 
SELECT p.name,sum(oi.quantity) as total
from order_items oi join products p
ON oi.product_id=p.product_id
GROUP BY p.product_id,p.name
ORDER BY total DESC
LIMIT 1;

-- Using Subquery
SELECT p.name,new.total
FROM (SELECT product_id,sum(quantity) as total FROM order_items GROUP BY  product_id) new
join Products p on new.product_id=p.product_id
ORDER BY new.total DESC
LIMIT 1;

-- 2. most valuable customers
SELECT c.customer_id,
c.name,
COUNT(o.order_id) AS freq,
MAX(o.order_date) AS last_order_date,
SUM(oi.quantity*p.price) AS total_spent
FROM Customers c Join
Orders o ON c.customer_id=o.customer_id JOIN
Order_items oi ON o.order_id=oi.order_id JOIN
Products p ON oi.product_id=p.product_id
GROUP BY c.customer_id,c.name
ORDER BY freq DESC,total_spent DESC
LIMIT 1;

-- 3. Monthly revenue calculation
SELECT DATE_FORMAT(o.order_date,'%Y-%m') AS year_and_month,
SUM(oi.quantity*p.price) AS revenue
FROM Orders o JOIN
Order_items oi ON o.order_id =oi.order_id JOIN
Products p ON oi.product_id=p.product_id
GROUP BY year_and_month;

-- 4. Category-wise sales analysis
SELECT p.category,
SUM(oi.quantity*p.price) AS revenue
FROM Order_items oi RIGHT JOIN
Products p ON oi.product_id=p.product_id
GROUP BY p.category
ORDER BY revenue DESC;

-- with COALESCE 
SELECT p.category,
       COALESCE(SUM(oi.quantity * p.price), 0) AS revenue
FROM Order_Items oi
RIGHT JOIN Products p ON oi.product_id = p.product_id
GROUP BY p.category
ORDER BY revenue DESC;

-- 5. Detect inactive customers
SELECT c.customer_id,
c.name,
MAX(o.order_date) AS last_order_date
FROM Customers c LEFT JOIN
Orders o ON c.customer_id=o.customer_id
GROUP BY c.customer_id,c.name
HAVING MAX(o.order_date) IS NULL OR TIMESTAMPDIFF(MONTH,MAX(o.order_date),CURDATE())>=2;
 
