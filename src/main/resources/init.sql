DROP TABLE IF EXISTS restaurant_foods;
DROP TABLE IF EXISTS restaurant_tables;
DROP TABLE IF EXISTS foods;
DROP TABLE IF EXISTS tables;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    user_id SERIAL UNIQUE PRIMARY KEY,
    user_name varchar(16) NOT NULL,
	user_email varchar(254) NOT NULL,
    user_country varchar(254),
    user_postalCode integer,
    user_city varchar(254),
    user_adress text,
	user_password text NOT NULL,
	user_role varchar(12) NOT NULL,
	user_balance integer NOT NULL,
);

CREATE TABLE restaurants (
    restaurant_id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
);

CREATE TABLE tables (
    table_id SERIAL PRIMARY KEY,
    table_capacity integer,
    table_status boolean,
);

CREATE TABLE foods (
    food_id SERIAL PRIMARY KEY,
    food_name varchar(254),
    food_ingredients varchar(254),
    food_price integer,
);
CREATE TABLE orders (
    order_id serial primary key,
    user_id integer,
    restaurant_id integer,
    food_id integer,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id),
    FOREIGN KEY (food_id) REFERENCES foods(food_id),
);
CREATE TABLE restaurant_tables(
    restaurant_tables_id SERIAL PRIMARY KEY,
    restaurant_id integer,
    table_id integer,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id),
    FOREIGN KEY (table_id) REFERENCES tables(table_id),
);
CREATE TABLE restaurant_foods(
    restaurant_foods_id SERIAL PRIMARY KEY,
    restaurant_id integer,
    food_id integer,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id),
    FOREIGN KEY (food_id) REFERENCES foods(food_id),

)