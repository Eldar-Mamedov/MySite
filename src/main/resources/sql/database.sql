CREATE DATABASE IF NOT EXISTS beauty_saloon CHARACTER SET utf8;
USE beauty_saloon;
CREATE TABLE IF NOT EXISTS _role (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     `role` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
    );
CREATE TABLE IF NOT EXISTS _user (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     `name` varchar(20) DEFAULT NULL,
    `surname` varchar(30) DEFAULT NULL,
    `phone` varchar(15) DEFAULT NULL,
    `email` varchar(20) DEFAULT NULL,
    `login` varchar(10) DEFAULT NULL,
    `password` varchar(32) DEFAULT NULL,
    `gender` varchar(8) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email` (`email`),
    UNIQUE KEY `login` (`login`),
    UNIQUE KEY `login_2` (`login`,`email`)
    );
CREATE TABLE IF NOT EXISTS user_role (
                                         `id_role` int DEFAULT NULL,
                                         `user_id` int DEFAULT NULL,
                                         KEY `role_fk1` (`id_role`),
    KEY `user_fk1` (`user_id`),
    CONSTRAINT `user_fk1` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`),
    CONSTRAINT `role_fk1` FOREIGN KEY (`id_role`) REFERENCES `_role` (`id`)
    );
CREATE TABLE IF NOT EXISTS service_category (
                                                `id` int NOT NULL AUTO_INCREMENT,
                                                `name` varchar(30) DEFAULT NULL,
    `locale` varchar(2) DEFAULT NULL,
    PRIMARY KEY (`id`)
    );
CREATE TABLE IF NOT EXISTS service_item (
                                            `id` int NOT NULL AUTO_INCREMENT,
                                            `category_id` int NOT NULL,
                                            `service_name` varchar(30) DEFAULT NULL,
    `price` int DEFAULT NULL,
    `locale` varchar(2) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `category_fk1` (`category_id`),
    CONSTRAINT `category_fk1` FOREIGN KEY (`category_id`) REFERENCES `service_category` (`id`)
    );
CREATE TABLE IF NOT EXISTS employee_speciality (
                                                   `user_id` int NOT NULL,
                                                   `service_item_id` int NOT NULL,
                                                   KEY `emp_fk1` (`user_id`),
    KEY `ser_it_fk1` (`service_item_id`),
    CONSTRAINT `emp_fk1` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`),
    CONSTRAINT `ser_it_fk1` FOREIGN KEY (`service_item_id`) REFERENCES `service_item` (`id`)
    );
CREATE TABLE IF NOT EXISTS orders (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `date_order` timestamp,
                                      `client_id` int NOT NULL,
                                      `employee_id` int NOT NULL,
                                      `service_item_id` int NOT NULL,
                                      `is_payed` boolean default false,
                                      `date_service` timestamp,
                                      `parent_order_id` varchar(36),
    `order_status_id` int NOT NULL,
    PRIMARY KEY(`id`),
    KEY `client_fk2` (`client_id`),
    KEY `emp_fk2` (`employee_id`),
    KEY `service_item_fk1` (`service_item_id`),
    KEY `order_status_fk1` (`order_status_id`),
    CONSTRAINT `client_fk2` FOREIGN KEY (`client_id`) REFERENCES `_user` (`id`),
    CONSTRAINT `emp_fk2` FOREIGN KEY (`employee_id`) REFERENCES `_user` (`id`),
    CONSTRAINT `service_item_fk1` FOREIGN KEY (`service_item_id`) REFERENCES `service_item` (`id`),
    CONSTRAINT `order_status_fk1` FOREIGN KEY (`order_status_id`) REFERENCES `order_status` (`id`)
    );
CREATE TABLE IF NOT EXISTS order_status(
                                           `id` int NOT NULL AUTO_INCREMENT,
                                           `status` varchar(30),
    PRIMARY KEY(`id`)
    );
CREATE TABLE IF NOT EXISTS reviews (
                                       `id` int NOT NULL AUTO_INCREMENT,
                                       `client_id` int NOT NULL,
                                       `creation_date` timestamp,
                                       `message` text,
                                       `order_id` int NOT NULL,
                                       `rating_mark` int NOT NULL,
                                       PRIMARY KEY(`id`),
    KEY `client_fk3` (`client_id`),
    KEY `order_fk1` (`order_id`),
    CONSTRAINT `client_fk3` FOREIGN KEY (`client_id`) REFERENCES `_user` (`id`),
    CONSTRAINT `order_fk1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
    );
CREATE TABLE IF NOT EXISTS empolyee_rating (
                                               `id` int NOT NULL AUTO_INCREMENT,
                                               `employee_id` int NOT NULL,
                                               `review_id` int NOT NULL,
                                               `one_mark` int NOT NULL default 0,
                                               `two_mark` int NOT NULL default 0,
                                               `three_mark` int NOT NULL default 0,
                                               `four_mark` int NOT NULL default 0,
                                               `five_mark` int NOT NULL default 0,
                                               PRIMARY KEY(`id`),
    KEY `emp_fk3` (`employee_id`),
    KEY `review_fk1` (`review_id`),
    CONSTRAINT `emp_fk3` FOREIGN KEY (`employee_id`) REFERENCES `_user` (`id`),
    CONSTRAINT `review_fk1` FOREIGN KEY (`review_id`) REFERENCES `reviews` (`id`)
    );