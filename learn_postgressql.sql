/*
Table is used to store the data and it can be inherited
Schema is a logical container of tables and other objects inside a database
Database is a container of other objects such as schemas, tables, views, functions, indexes, etc. 
Tablespace is where PostgreSQL stores the data and it enables you to move your data to different physical location across drivers easily.
  By default, PostgreSQL provides two tablespaces: pg_defaultfor storing user’s data and pg_globalfor storing system data.
View is a virtual table that is used to simplify complex queries and to apply security for a set of records.
Function is a block reusable SQL code that returns a scalar value of a list of objects.
Operator is a symbolic function. PostgreSQL allows you to define custom operators.
Casts enable you to convert one data type into another data type. PostgreSQL allows you to override casts.
Sequences are used to manage auto-increment columns that defined in a table as a serial column.
Extensions wrap other objects including types, casts, indexes, functions, etc into a single unit.
*/

-- Open psql shell
SELECT version();
CREATE DATABASE dvdrental;

-- Open os shell
cd "C:\Program Files\PostgreSQL\10\bin"
pg_restore -U postgres -d dvdrental C:\Users\JP\Downloads\dvdrental\dvdrental.tar
  -- The -U postgres specifies the user postgres to login

-- Open psql shell
\c dvdrental

CREATE TABLE t1 (
 id serial NOT NULL PRIMARY KEY,
 bcolor VARCHAR,
 fcolor VARCHAR);

INSERT INTO t1 (bcolor, fcolor)
VALUES
 ('red', 'red'),
 ('red', 'red'),
 ('red', NULL),
 (NULL, 'red'),
 ('red', 'green'),
 ('red', 'blue'),
 ('green', 'red'),
 ('green', 'blue'),
 ('green', 'green'),
 ('blue', 'red'),
 ('blue', 'green'),
 ('blue', 'blue');


-- QUERIES --

-- SELECT, FROM, WHERE, ORDER BY, LIMIT
SELECT 
 last_name, * 
FROM 
 customer 
WHERE 
 store_id != 1
ORDER BY 
 last_name DESC, first_name ASC 
LIMIT 3 OFFSET 5;


-- SELECT DISTINCT: outputs unique tuples of the columns
SELECT DISTINCT 
 bcolor, fcolor 
FROM 
 t1 
ORDER BY 
 bcolor, fcolor;

-- SELECT DISTINCT ON: outputs unique tupes of the columns in the ON() clause and selects the first entries for the rest of the columns
SELECT DISTINCT ON (bcolor) 
 bcolor, fcolor
FROM
 t1
ORDER BY
 bcolor, fcolor;

-- http://www.postgresqltutorial.com/postgresql-in/
first_name LIKE 'B%y' AND last_name NOT ILIKE 'LoVeLaC_' 