
INSERT INTO products (unitPrice,name,description,category,manufacturer,unitsInStock, conditionn, discontinued, unitsInOrder) values (500, 'iPhone 5s','Apple iPhone 5s, smartfon z 4-calowym ekranem o rozdzielczoœci 640x1136 i 8-megapikselowym aparatem','Smartfon','Apple',1000, 'Nowy', 0, 0)

INSERT INTO products (unitPrice,name,description,category,manufacturer,unitsInStock, conditionn, discontinued, unitsInOrder) values (700, 'Dell Inspiron','Dell Inspiron, 14-calowy laptop (czarny) z procesorem Intel Core 3. generacji','Laptop','Dell',1000, 'Nowy', 0, 0)

INSERT INTO products (unitPrice,name,description,category,manufacturer,unitsInStock, conditionn, discontinued, unitsInOrder) values (300, 'Nexus 7','Google Nexus 7 jest najl¿ejszym 7-calowym tabletem z 4-rdzeniowym procesorem Qualcomm Snapdragon™ S4 Pro','Tablet','Google',1000, 'Nowy', 0, 0)

INSERT INTO products (unitPrice,name,description,category,manufacturer,unitsInStock, conditionn, discontinued, unitsInOrder) values (1500, 'OnePlus One','Cudny telefon WOW!','Smartfon','OnePlus',1000, 'Nowy', 0, 0)

INSERT INTO users (username, password, enabled, email) VALUES ('admin', '$2a$10$EPYc4gKAvbbD3QpZ17qkq.fCbqB.X9saWpC0owMVlUFV3YwwICSge', 1, 'admin@webstore.pl');

INSERT INTO authorities (authority, username) VALUES ('ROLE_ADMIN', 'admin');

INSERT INTO users (username, password, enabled, email) VALUES ('supervisor', '$2a$10$uEpwW1fpQwpJ6m/QrzxsVednqn5RLTTy2asmIEJ7RF7W2b2RClC82', 1, 'supervisor@webstore.pl');

INSERT INTO authorities (authority, username) VALUES ('ROLE_SUPERVISOR', 'supervisor');

INSERT INTO users (username, password, enabled, email, customerId) VALUES ('user', '$2a$10$ocdrVujq6eagCLrn8O3Gt.c/.TYyusKpepBNk7nJRgw7BMWk8A1T6', 1, 'user@webstore.pl', 1);

INSERT INTO authorities (authority, username) VALUES ('ROLE_USER', 'user');

INSERT INTO customers (firstName, lastName, phoneNumber, noOfOrdersMade, addressId) VALUES ('Jan', 'Nowak', 789456123, 0, 1)

INSERT INTO addresses (streetName, doorNo, areaName, state, country, zipCode) VALUES ('D³uga', 9, 'Kraków', 'Ma³opolskie', 'Polska', '00-354')


