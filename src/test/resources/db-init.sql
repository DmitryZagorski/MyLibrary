CREATE TABLE book_picture (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  path varchar(255) NOT NULL,
  book_id int NOT NULL,
  PRIMARY KEY (id),
  KEY book_id (book_id),
  CONSTRAINT book_picture FOREIGN KEY (book_id) REFERENCES books (id)
);

CREATE TABLE books (
  id int NOT NULL AUTO_INCREMENT,
  title varchar(100) NOT NULL,
  author varchar(100) NOT NULL,
  issue_date date NOT NULL,
  genre_id int NOT NULL,
  PRIMARY KEY (id),
  KEY genre_id (genre_id),
  CONSTRAINT books FOREIGN KEY (genre_id) REFERENCES genres (id)
);

CREATE TABLE cart (
  id int NOT NULL AUTO_INCREMENT,
  customer_id int NOT NULL,
  PRIMARY KEY (id),
  KEY customer_id (customer_id),
  CONSTRAINT cart FOREIGN KEY (customer_id) REFERENCES customers (id)
);

CREATE TABLE cart_books (
  id int NOT NULL AUTO_INCREMENT,
  book_id int NOT NULL,
  quantity int NOT NULL,
  cart_id int NOT NULL,
  PRIMARY KEY (id),
  KEY cart_id (cart_id),
  KEY book_id (book_id),
  CONSTRAINT cart_books FOREIGN KEY (cart_id) REFERENCES cart (id),
  CONSTRAINT cart_books FOREIGN KEY (book_id) REFERENCES books (id)
);

CREATE TABLE catalog (
  id int NOT NULL AUTO_INCREMENT,
  book_id int NOT NULL,
  total_quantity int NOT NULL,
  free_quantity int NOT NULL,
  PRIMARY KEY (id),
  KEY book_id (book_id),
  CONSTRAINT catalog FOREIGN KEY (book_id) REFERENCES books (id)
);

CREATE TABLE customers (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  surname varchar(100) NOT NULL,
  address varchar(200) NOT NULL,
  email varchar(100) NOT NULL,
  date_of_sign_up date NOT NULL,
  login varchar(100) NOT NULL,
  password varchar(45) DEFAULT NULL,
  role varchar(45) NOT NULL,
  locked tinyint(1) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE genres (
  id int NOT NULL AUTO_INCREMENT,
  title varchar(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE order_books (
  id int NOT NULL AUTO_INCREMENT,
  book_id int NOT NULL,
  quantity int NOT NULL,
  order_id int NOT NULL,
  PRIMARY KEY (id),
  KEY order_id (order_id),
  KEY book_id (book_id),
  CONSTRAINT order_books FOREIGN KEY (order_id) REFERENCES orders (id),
  CONSTRAINT order_books FOREIGN KEY (book_id) REFERENCES books (id)
);

CREATE TABLE orders (
  id int NOT NULL AUTO_INCREMENT,
  total_quantity int NOT NULL,
  customer_id int NOT NULL,
  date_of_creation date NOT NULL,
  expiration_date date NOT NULL,
  place_of_reading_id int NOT NULL,
  cart_id int NOT NULL,
  active tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  KEY customer_id (customer_id),
  KEY cart_id (cart_id),
  KEY place_of_reading_id (place_of_reading_id),
  CONSTRAINT orders FOREIGN KEY (customer_id) REFERENCES customers (id),
  CONSTRAINT orders FOREIGN KEY (cart_id) REFERENCES cart (id),
  CONSTRAINT orders FOREIGN KEY (place_of_reading_id) REFERENCES place_of_reading (id)
);

CREATE TABLE place_of_reading (
  id int NOT NULL AUTO_INCREMENT,
  place_title varchar(200) NOT NULL,
  PRIMARY KEY (id)
);
