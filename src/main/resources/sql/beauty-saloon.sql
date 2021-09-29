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
-- Dumping data for table `_role`
--

LOCK TABLES `_role` WRITE;
/*!40000 ALTER TABLE `_role` DISABLE KEYS */;
INSERT INTO `_role` VALUES (1,'Client'),(2,'Admin'),(3,'Master');
/*!40000 ALTER TABLE `_role` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_user`
--

LOCK TABLES `_user` WRITE;
/*!40000 ALTER TABLE `_user` DISABLE KEYS */;
INSERT INTO `_user` VALUES (2,'Master','Master','0939982539','master@gmail.com','master','202cb962ac59075b964b07152d234b70','male'),(3,'Эльдар','Мамедов','0939982539','eldasha02@gmail.com','el','202cb962ac59075b964b07152d234b70','male'),(4,'Марина','Васильевна','0939982551','marina@gmail.com','marina','81dc9bdb52d04dc20036dbd8313ed055','female'),(5,'Татьяна','Гордон','0939982561','tatiana@gmail.com','tatiana','81dc9bdb52d04dc20036dbd8313ed055','female'),(6,'Владимир','Хиль','0939982575','vladimir@gmail.com','vladimir','827ccb0eea8a706c4c34a16891f84e7b','male');
/*!40000 ALTER TABLE `_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_speciality`
--

DROP TABLE IF EXISTS `employee_speciality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_speciality` (
  `user_id` int NOT NULL,
  `service_item_id` int NOT NULL,
  KEY `emp_fk1` (`user_id`),
  KEY `ser_cat_fk1_idx` (`service_item_id`),
  CONSTRAINT `emp_fk1` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`),
  CONSTRAINT `ser_it_fk1` FOREIGN KEY (`service_item_id`) REFERENCES `service_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_speciality`
--

LOCK TABLES `employee_speciality` WRITE;
/*!40000 ALTER TABLE `employee_speciality` DISABLE KEYS */;
INSERT INTO `employee_speciality` VALUES (2,1),(2,3),(4,9),(4,10),(5,1),(5,12);
/*!40000 ALTER TABLE `employee_speciality` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `empolyee_rating`
--

LOCK TABLES `empolyee_rating` WRITE;
/*!40000 ALTER TABLE `empolyee_rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `empolyee_rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES (1,'IN_PROCESS'),(2,'IN_PROGRESS'),(3,'DONE'),(4,'CANCELLED');
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

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
  `order_status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `client_fk2` (`client_id`),
  KEY `emp_fk2` (`employee_id`),
  KEY `service_item_fk1` (`service_item_id`),
  KEY `order_status_fk1` (`order_status_id`),
  CONSTRAINT `client_fk2` FOREIGN KEY (`client_id`) REFERENCES `_user` (`id`),
  CONSTRAINT `emp_fk2` FOREIGN KEY (`employee_id`) REFERENCES `_user` (`id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`order_status_id`) REFERENCES `order_status` (`id`) ON DELETE CASCADE,
  CONSTRAINT `service_item_fk1` FOREIGN KEY (`service_item_id`) REFERENCES `service_item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (6,'2021-09-29 11:00:00',3,2,1,0,'2021-09-30 06:00:00','625b58a1-8c68-4215-85b3-2fb9da1de199',1),(7,'2021-09-29 11:00:00',3,2,3,0,'2021-09-30 06:00:00','625b58a1-8c68-4215-85b3-2fb9da1de199',1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_category`
--

LOCK TABLES `service_category` WRITE;
/*!40000 ALTER TABLE `service_category` DISABLE KEYS */;
INSERT INTO `service_category` VALUES (1,'СТРИЖКИ / УКЛАДКИ / ПРИЧЕСКИ','ru'),(2,'МАНИКЮР / ПЕДИКЮР','ru'),(3,'HAIRCUTS / STYLES / HAIRSTYLES','en'),(4,'MANICURE / PEDICURE','en'),(5,'МАССАЖ','ru'),(6,'ВИЗАЖ','ru'),(7,'MASSAGE','en'),(8,'VISAGE','en');
/*!40000 ALTER TABLE `service_category` ENABLE KEYS */;
UNLOCK TABLES;

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
  `same_item_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `category_fk1` (`category_id`),
  CONSTRAINT `category_fk1` FOREIGN KEY (`category_id`) REFERENCES `service_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_item`
--

LOCK TABLES `service_item` WRITE;
/*!40000 ALTER TABLE `service_item` DISABLE KEYS */;
INSERT INTO `service_item` VALUES (1,1,'стрижка',500,'ru',1),(2,1,'камуфлирование',600,'ru',2),(3,2,'маникюр',400,'ru',3),(4,2,'японский маникюр',600,'ru',4),(5,3,'haircut',500,'en',1),(6,3,'camouflage',600,'en',2),(7,4,'manicure',400,'en',3),(8,4,'japanese manicure',600,'en',4),(9,1,'окрашивание',900,'ru',5),(10,1,'стрижка машинкой',300,'ru',6),(11,1,'борода и усы',200,'ru',7),(12,1,'укладка',100,'ru',8),(13,1,'пилинг кожи головы',500,'ru',9),(14,3,'staining',900,'en',5),(15,3,'hair clipper',300,'en',6),(16,3,'beard and mustache',200,'en',7),(17,3,'stacking',100,'en',8),(18,3,'scalp peeling',500,'en',9),(19,2,'педикюр',600,'ru',10),(20,2,'подологический педикюр',800,'ru',11),(21,2,'СПА маникюр',600,'ru',12),(22,2,'СПА педикюр',800,'ru',13),(23,4,'pedicure',600,'en',10),(24,4,'podological pedicure',800,'en',11),(25,4,'SPA manicure',600,'en',12),(26,4,'SPA pedicure',800,'en',13),(27,6,'коррекция бровей',300,'ru',14),(28,6,'окрашивание бровей',300,'ru',15),(29,8,'eyebrow correction',300,'en',14),(30,8,'eyebrow coloring',300,'en',15),(31,5,'массаж лица',500,'ru',16),(32,5,'классический массаж тела',800,'ru',17),(33,7,'facial massage',500,'en',16),(34,7,'classic body massage',800,'en',17);
/*!40000 ALTER TABLE `service_item` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `user_fk1` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (3,2),(1,3),(3,4),(3,5),(2,6);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-29 23:34:07
