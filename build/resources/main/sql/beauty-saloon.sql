CREATE DATABASE  IF NOT EXISTS `beauty_saloon` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `beauty_saloon`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: beauty_saloon
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `_role`
--

DROP TABLE IF EXISTS `_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `_user`
--

DROP TABLE IF EXISTS `_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_user` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee_speciality`
--

DROP TABLE IF EXISTS `employee_speciality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_speciality` (
  `user_id` int NOT NULL,
  `service_category_id` int NOT NULL,
  KEY `emp_fk1` (`user_id`),
  KEY `ser_cat_fk1` (`service_category_id`),
  CONSTRAINT `emp_fk1` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`),
  CONSTRAINT `ser_cat_fk1` FOREIGN KEY (`service_category_id`) REFERENCES `service_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `empolyee_rating`
--

DROP TABLE IF EXISTS `empolyee_rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empolyee_rating` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `review_id` int NOT NULL,
  `one_mark` int NOT NULL DEFAULT '0',
  `two_mark` int NOT NULL DEFAULT '0',
  `three_mark` int NOT NULL DEFAULT '0',
  `four_mark` int NOT NULL DEFAULT '0',
  `five_mark` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `emp_fk3` (`employee_id`),
  KEY `review_fk1` (`review_id`),
  CONSTRAINT `emp_fk3` FOREIGN KEY (`employee_id`) REFERENCES `_user` (`id`),
  CONSTRAINT `review_fk1` FOREIGN KEY (`review_id`) REFERENCES `reviews` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_order` timestamp NULL DEFAULT NULL,
  `client_id` int NOT NULL,
  `employee_id` int NOT NULL,
  `service_item_id` int NOT NULL,
  `is_payed` tinyint(1) DEFAULT '0',
  `date_service` timestamp NULL DEFAULT NULL,
  `parent_order_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `client_fk2` (`client_id`),
  KEY `emp_fk2` (`employee_id`),
  KEY `service_item_fk1` (`service_item_id`),
  CONSTRAINT `client_fk2` FOREIGN KEY (`client_id`) REFERENCES `_user` (`id`),
  CONSTRAINT `emp_fk2` FOREIGN KEY (`employee_id`) REFERENCES `_user` (`id`),
  CONSTRAINT `service_item_fk1` FOREIGN KEY (`service_item_id`) REFERENCES `service_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `client_id` int NOT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `message` text,
  `order_id` int NOT NULL,
  `rating_mark` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `client_fk3` (`client_id`),
  KEY `order_fk1` (`order_id`),
  CONSTRAINT `client_fk3` FOREIGN KEY (`client_id`) REFERENCES `_user` (`id`),
  CONSTRAINT `order_fk1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `service_category`
--

DROP TABLE IF EXISTS `service_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `locale` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `service_item`
--

DROP TABLE IF EXISTS `service_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_id` int NOT NULL,
  `service_name` varchar(30) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `locale` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_fk1` (`category_id`),
  CONSTRAINT `category_fk1` FOREIGN KEY (`category_id`) REFERENCES `service_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id_role` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  KEY `role_fk1` (`id_role`),
  KEY `user_fk1` (`user_id`),
  CONSTRAINT `role_fk1` FOREIGN KEY (`id_role`) REFERENCES `_role` (`id`),
  CONSTRAINT `user_fk1` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-26 20:32:39
