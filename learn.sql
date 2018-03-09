-- CREATING SCHEMAS
DROP SCHEMA IF EXISTS testy; 
CREATE SCHEMA IF NOT EXISTS testy; 
USE testy; 

-- ==================================================
-- CREATING TABLES WITH VARIOUS DATA TYPES AND CONSTRAINTS
CREATE TABLE users(
	id int NOT NULL AUTO_INCREMENT, 
    username varchar(50) NOT NULL, 
    bio varchar(500) DEFAULT 'hi (o\'u\'o)', 
    height DOUBLE, 
    PRIMARY KEY(id)
);

CREATE TABLE items(
	id int NOT NULL AUTO_INCREMENT, 
    obj_name varchar(50) NOT NULL, 
    PRIMARY KEY(id)
);

CREATE TABLE purchases(
	id int NOT NULL AUTO_INCREMENT, 
    user_id int, -- item purchaser may be ¿mysterious¿
    item_id int NOT NULL, 
    purchase_price DOUBLE NOT NULL, 
    FOREIGN KEY(user_id) REFERENCES users(id), 
	FOREIGN KEY(item_id) REFERENCES items(id), 
    PRIMARY KEY(id)
);

CREATE TABLE useless_table(
	id int NOT NULL AUTO_INCREMENT, 
    useless_col int, 
    PRIMARY KEY(id)
);

-- ==================================================
-- EDITING TABLES
ALTER TABLE useless_table 
DROP COLUMN useless_col;

ALTER TABLE useless_table 
ADD useful_col int;

ALTER TABLE useless_table 
CHANGE id useful_id varchar(10);

RENAME TABLE useless_table TO useful_table;

TRUNCATE TABLE useful_table; -- delete all entries

DROP TABLE useful_table; -- deletes the table

-- ==================================================
-- DISPLAYING DATABASE STRUCTURES
SHOW DATABASES;
SHOW TABLES FROM testy;
SHOW COLUMNS FROM users;

-- ==================================================
-- CREATING ROWS
INSERT INTO users(id, username, bio, height) 
VALUES (200, 'dummy data', 'dummy data', 0);

INSERT INTO users(username) 
VALUES ('this is a required field'), 
	('this is another required field'), 
    ('what is love?');
    
INSERT INTO items(obj_name) 
VALUES ('batarang'), 
	('bat mobile'), 
    ('bat cow');
    
INSERT INTO purchases(user_id, item_id, purchase_price) 
VALUES (200, 1, 1000.0), (200, 2, 1000.5), 
	(201, 2, 2000.0), (201, 3, 2000.5), 
    (NULL, 3, 3000.0), (NULL, 1, 3000.5), (NULL, 1, 1);

-- ==================================================
-- EDITING ROWS
UPDATE users 
SET bio = 'i\'m batman', 
	username = 'batman', 
    height = 100.5 
WHERE id = 200;

DELETE FROM users 
WHERE id = 202;

-- ==================================================
-- CREATING QUERIES
SELECT * 
FROM items 
WHERE obj_name LIKE 'bat %' AND -- if the obj_name starts with "bat " and
	id = ANY (SELECT item_id FROM PURCHASES WHERE user_id IS NULL); -- the item_id is purchased by a mysterious user_id

SELECT DISTINCT user_id AS purchasers -- get distinct user_ids
FROM purchases 
WHERE user_id IS NOT NULL; -- if the user_id belongs to a non mysterious person

SELECT *, purchase_price * (1 - 0.25) AS sale25off_price -- add a new column that shows the sale price
FROM purchases 
WHERE purchase_price % 1 = 0 -- get only whole number purchase_price
ORDER BY purchase_price DESC, item_id 
LIMIT 1, 2; -- get the 2 suceeding largest values after the 1st largest value

(SELECT * 
FROM purchases 
WHERE purchase_price BETWEEN 2000 AND 3000 -- take purchase_price price between 2000 and 3000
) UNION ( -- use UNION ALL to keep duplicates
SELECT * 
FROM purchases 
WHERE (NOT purchase_price < 2000) AND (NOT purchase_price > 3000)); -- take the same data but through the composition of logical statements

SELECT *
FROM ( -- create a subquery that consists of the original table and a new table with scaled up purchase_price
	(SELECT * FROM purchases AS X) 
    UNION ALL 
    (SELECT id, user_id, item_id, purchase_price * 1.5 FROM purchases AS Y)
) AS X_UA_Y
WHERE purchase_price >= (SELECT AVG(purchase_price) FROM purchases); -- take everything above the original average price

SELECT id % 3, GROUP_CONCAT(id) AS ids, SUM(purchase_price), COUNT(purchase_price), AVG(purchase_price) -- aggregate functions combines multiple rows together, while GROUP_CONCAT() shows the individual entries for a group
FROM purchases 
GROUP BY id % 3 -- GROUP BY chooses how the data is aggregated
HAVING COUNT(purchase_price) = 2 -- WHERE for GROUP BY, filters group using a condition
ORDER BY id % 3 DESC;

-- ==================================================
-- JOINING TABLES
SELECT *, CONCAT(u.id, ',', p.user_id) AS joined_id 
FROM users AS u 
INNER JOIN purchases AS p ON p.user_id = u.id; -- pick elements in (u \cap p)
    
SELECT *, CONCAT(u.id, ',', p.user_id) AS joined_id 
FROM users AS u 
LEFT OUTER JOIN purchases AS p ON p.user_id = u.id;  -- pick elements in (u \cap p) \cup u
    
SELECT *, CONCAT(u.id, ',', p.user_id) AS joined_id 
FROM users AS u 
RIGHT OUTER JOIN purchases AS p ON p.user_id = u.id; -- pick elements in (u \cap p) \cup p

SELECT * 
FROM users AS u -- example of multiple joins
INNER JOIN purchases AS p ON p.user_id = u.id
INNER JOIN items AS i ON i.id = p.item_id;

-- ==================================================
-- MANIPULATING VIEWS (DYNAMIC VIRTUAL TABLES)

CREATE OR REPLACE VIEW users_copy AS 
	SELECT *, 42 as meaning_of_life FROM users;

SELECT * FROM users_copy;

DROP VIEW IF EXISTS users_copy;

-- TODO: indexing: creates a external-structured-version of a table that enables the use of faster retrieval algorithms (e.g. divide-and-conquer) at the cost of memory space.

-- ==================================================
-- STORED PROCEDURES

DELIMITER $$
CREATE PROCEDURE GetAllProducts()
   BEGIN
   SELECT *  FROM items;
END$$
DELIMITER ;

CALL GetAllProducts();

/*
A stored procedure is a group of SQL statements that has been created and stored in the database. A stored procedure will accept input parameters so that a single procedure can be used over the network by several clients using different input data. A stored procedures will reduce network traffic and increase the performance. If we modify a stored procedure all the clients will get the updated stored procedure.


Advantages of using stored procedures
- A stored procedure allows modular programming.
-- You can create the procedure once, store it in the database, and call it any number of times in your program.

- A stored procedure allows faster execution.
-- If the operation requires a large amount of SQL code that is performed repetitively, stored procedures can be faster. They are parsed and optimized when they are first executed, and a compiled version of the stored procedure remains in a memory cache for later use. This means the stored procedure does not need to be reparsed and reoptimized with each use, resulting in much faster execution times.

-A stored procedure can reduce network traffic.
-- An operation requiring hundreds of lines of Transact-SQL code can be performed through a single statement that executes the code in a procedure, rather than by sending hundreds of lines of code over the network.

- Stored procedures provide better security to your data
-- Users can be granted permission to execute a stored procedure even if they do not have permission to execute the procedure's statements directly.

In SQL Server we have different types of stored procedures:
- System stored procedures: procedures are stored in the master database
- User-defined stored procedures: procedures are stored in a user database and are typically designed to complete the tasks in the user database
- Extended stored Procedures: procedures that call functions from DLL files
*/