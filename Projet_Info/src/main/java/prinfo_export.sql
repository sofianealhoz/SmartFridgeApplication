-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: prinfo
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `fridgeingredients`
--

DROP TABLE IF EXISTS `fridgeingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fridgeingredients` (
  `fridge_ingredient_id` int NOT NULL AUTO_INCREMENT,
  `fridge_id` int DEFAULT NULL,
  `ingredient_id` int DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`fridge_ingredient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fridgeingredients`
--

LOCK TABLES `fridgeingredients` WRITE;
/*!40000 ALTER TABLE `fridgeingredients` DISABLE KEYS */;
/*!40000 ALTER TABLE `fridgeingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fridges`
--

DROP TABLE IF EXISTS `fridges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fridges` (
  `fridge_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`fridge_id`)
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fridges`
--

LOCK TABLES `fridges` WRITE;
/*!40000 ALTER TABLE `fridges` DISABLE KEYS */;
INSERT INTO `fridges` VALUES (20,NULL,'user','2024-01-22 17:38:48','2024-01-22 17:38:48'),(21,NULL,'default','2024-01-22 17:38:48','2024-01-22 17:38:48'),(22,NULL,'default','2024-01-22 17:38:48','2024-01-22 17:38:48'),(23,NULL,'ahmed','2024-01-22 17:39:15','2024-01-22 17:39:15'),(24,NULL,'test','2024-01-22 17:41:08','2024-01-22 17:41:08'),(25,NULL,'user','2024-01-22 17:57:17','2024-01-22 17:57:17'),(26,NULL,'default','2024-01-22 17:57:17','2024-01-22 17:57:17'),(27,NULL,'default','2024-01-22 17:57:17','2024-01-22 17:57:17'),(28,NULL,'ahmed','2024-01-22 17:57:22','2024-01-22 17:57:22'),(29,NULL,'test','2024-01-22 17:57:30','2024-01-22 17:57:30'),(30,NULL,'user','2024-01-22 18:03:48','2024-01-22 18:03:48'),(31,NULL,'default','2024-01-22 18:03:48','2024-01-22 18:03:48'),(32,NULL,'default','2024-01-22 18:03:48','2024-01-22 18:03:48'),(33,NULL,'user','2024-01-22 18:07:33','2024-01-22 18:07:33'),(34,NULL,'default','2024-01-22 18:07:33','2024-01-22 18:07:33'),(35,NULL,'default','2024-01-22 18:07:33','2024-01-22 18:07:33'),(36,NULL,'ahmed','2024-01-22 18:08:07','2024-01-22 18:08:07'),(37,NULL,'user','2024-01-22 18:08:20','2024-01-22 18:08:20'),(38,NULL,'default','2024-01-22 18:08:20','2024-01-22 18:08:20'),(39,NULL,'default','2024-01-22 18:08:20','2024-01-22 18:08:20'),(40,NULL,'ahmed','2024-01-22 18:08:30','2024-01-22 18:08:30'),(41,NULL,'user','2024-01-22 18:12:19','2024-01-22 18:12:19'),(42,NULL,'default','2024-01-22 18:12:19','2024-01-22 18:12:19'),(43,NULL,'default','2024-01-22 18:12:19','2024-01-22 18:12:19'),(44,NULL,'ahmed','2024-01-22 18:12:36','2024-01-22 18:12:36'),(45,NULL,'user','2024-01-22 18:14:00','2024-01-22 18:14:00'),(46,NULL,'default','2024-01-22 18:14:00','2024-01-22 18:14:00'),(47,NULL,'default','2024-01-22 18:14:00','2024-01-22 18:14:00'),(48,NULL,'ahmed','2024-01-22 18:14:09','2024-01-22 18:14:09'),(49,NULL,'user','2024-01-22 18:20:53','2024-01-22 18:20:53'),(50,NULL,'default','2024-01-22 18:20:53','2024-01-22 18:20:53'),(51,NULL,'default','2024-01-22 18:20:53','2024-01-22 18:20:53'),(52,NULL,'ahmed','2024-01-22 18:20:59','2024-01-22 18:20:59'),(53,NULL,'user','2024-01-22 18:22:09','2024-01-22 18:22:09'),(54,NULL,'default','2024-01-22 18:22:09','2024-01-22 18:22:09'),(55,NULL,'default','2024-01-22 18:22:09','2024-01-22 18:22:09'),(56,NULL,'ahmed','2024-01-22 18:22:14','2024-01-22 18:22:14'),(57,NULL,'user','2024-01-22 18:28:16','2024-01-22 18:28:16'),(58,NULL,'default','2024-01-22 18:28:16','2024-01-22 18:28:16'),(59,NULL,'default','2024-01-22 18:28:16','2024-01-22 18:28:16'),(60,NULL,'ahmed','2024-01-22 18:28:22','2024-01-22 18:28:22'),(61,NULL,'user','2024-01-22 18:44:09','2024-01-22 18:44:09'),(62,NULL,'default','2024-01-22 18:44:09','2024-01-22 18:44:09'),(63,NULL,'default','2024-01-22 18:44:09','2024-01-22 18:44:09'),(64,NULL,'ahmed','2024-01-22 18:44:15','2024-01-22 18:44:15'),(65,NULL,'user','2024-01-22 18:53:56','2024-01-22 18:53:56'),(66,NULL,'default','2024-01-22 18:53:56','2024-01-22 18:53:56'),(67,NULL,'default','2024-01-22 18:53:56','2024-01-22 18:53:56'),(68,NULL,'user','2024-01-22 19:07:58','2024-01-22 19:07:58'),(69,NULL,'default','2024-01-22 19:07:58','2024-01-22 19:07:58'),(70,NULL,'default','2024-01-22 19:07:58','2024-01-22 19:07:58'),(71,NULL,'ahmed','2024-01-22 19:08:03','2024-01-22 19:08:03'),(72,NULL,'user','2024-01-22 19:59:52','2024-01-22 19:59:52'),(73,NULL,'default','2024-01-22 19:59:52','2024-01-22 19:59:52'),(74,NULL,'default','2024-01-22 19:59:52','2024-01-22 19:59:52'),(75,NULL,'ahmed','2024-01-22 20:00:04','2024-01-22 20:00:04'),(76,NULL,'user','2024-01-22 20:00:56','2024-01-22 20:00:56'),(77,NULL,'default','2024-01-22 20:00:56','2024-01-22 20:00:56'),(78,NULL,'default','2024-01-22 20:00:56','2024-01-22 20:00:56'),(79,NULL,'ahmed','2024-01-22 20:01:00','2024-01-22 20:01:00'),(80,NULL,'user','2024-01-22 20:01:53','2024-01-22 20:01:53'),(81,NULL,'default','2024-01-22 20:01:53','2024-01-22 20:01:53'),(82,NULL,'default','2024-01-22 20:01:53','2024-01-22 20:01:53'),(83,NULL,'ahmed','2024-01-22 20:02:00','2024-01-22 20:02:00'),(84,NULL,'user','2024-01-22 20:08:35','2024-01-22 20:08:35'),(85,NULL,'default','2024-01-22 20:08:35','2024-01-22 20:08:35'),(86,NULL,'default','2024-01-22 20:08:35','2024-01-22 20:08:35'),(87,NULL,'ahmed','2024-01-22 20:08:40','2024-01-22 20:08:40'),(88,NULL,'user','2024-01-22 20:20:08','2024-01-22 20:20:08'),(89,NULL,'default','2024-01-22 20:20:08','2024-01-22 20:20:08'),(90,NULL,'default','2024-01-22 20:20:08','2024-01-22 20:20:08'),(91,NULL,'ahmed','2024-01-22 20:20:12','2024-01-22 20:20:12'),(92,NULL,'user','2024-01-22 20:21:29','2024-01-22 20:21:29'),(93,NULL,'default','2024-01-22 20:21:29','2024-01-22 20:21:29'),(94,NULL,'default','2024-01-22 20:21:29','2024-01-22 20:21:29'),(95,NULL,'ahmed','2024-01-22 20:21:34','2024-01-22 20:21:34'),(96,NULL,'user','2024-01-22 20:23:10','2024-01-22 20:23:10'),(97,NULL,'default','2024-01-22 20:23:10','2024-01-22 20:23:10'),(98,NULL,'default','2024-01-22 20:23:10','2024-01-22 20:23:10'),(99,NULL,'ahmed','2024-01-22 20:23:14','2024-01-22 20:23:14'),(100,NULL,'user','2024-01-22 20:24:36','2024-01-22 20:24:36'),(101,NULL,'default','2024-01-22 20:24:36','2024-01-22 20:24:36'),(102,NULL,'default','2024-01-22 20:24:36','2024-01-22 20:24:36'),(103,NULL,'ahmed','2024-01-22 20:24:43','2024-01-22 20:24:43'),(104,NULL,'user','2024-01-22 20:39:53','2024-01-22 20:39:53'),(105,NULL,'default','2024-01-22 20:39:53','2024-01-22 20:39:53'),(106,NULL,'default','2024-01-22 20:39:53','2024-01-22 20:39:53'),(107,NULL,'ahmed','2024-01-22 20:39:58','2024-01-22 20:39:58'),(108,NULL,'user','2024-01-22 20:44:42','2024-01-22 20:44:42'),(109,NULL,'default','2024-01-22 20:44:42','2024-01-22 20:44:42'),(110,NULL,'default','2024-01-22 20:44:42','2024-01-22 20:44:42'),(111,NULL,'ahmed','2024-01-22 20:44:50','2024-01-22 20:44:50'),(112,NULL,'user','2024-01-22 20:46:29','2024-01-22 20:46:29'),(113,NULL,'default','2024-01-22 20:46:29','2024-01-22 20:46:29'),(114,NULL,'default','2024-01-22 20:46:29','2024-01-22 20:46:29'),(115,NULL,'ahmed','2024-01-22 20:46:47','2024-01-22 20:46:47'),(116,NULL,'user','2024-01-22 20:48:32','2024-01-22 20:48:32'),(117,NULL,'default','2024-01-22 20:48:32','2024-01-22 20:48:32'),(118,NULL,'default','2024-01-22 20:48:32','2024-01-22 20:48:32'),(119,NULL,'ahmed','2024-01-22 20:48:38','2024-01-22 20:48:38'),(120,NULL,'user','2024-01-22 20:54:03','2024-01-22 20:54:03'),(121,NULL,'default','2024-01-22 20:54:03','2024-01-22 20:54:03'),(122,NULL,'default','2024-01-22 20:54:03','2024-01-22 20:54:03'),(123,NULL,'ahmed','2024-01-22 20:54:20','2024-01-22 20:54:20'),(124,NULL,'user','2024-01-22 20:54:57','2024-01-22 20:54:57'),(125,NULL,'default','2024-01-22 20:54:57','2024-01-22 20:54:57'),(126,NULL,'default','2024-01-22 20:54:57','2024-01-22 20:54:57'),(127,NULL,'ahmed','2024-01-22 20:55:18','2024-01-22 20:55:18'),(128,NULL,'user','2024-01-22 20:58:34','2024-01-22 20:58:34'),(129,NULL,'default','2024-01-22 20:58:35','2024-01-22 20:58:35'),(130,NULL,'default','2024-01-22 20:58:35','2024-01-22 20:58:35'),(131,NULL,'ahmed','2024-01-22 20:58:40','2024-01-22 20:58:40'),(132,NULL,'user','2024-01-22 21:02:44','2024-01-22 21:02:44'),(133,NULL,'default','2024-01-22 21:02:44','2024-01-22 21:02:44'),(134,NULL,'default','2024-01-22 21:02:44','2024-01-22 21:02:44'),(135,NULL,'ahmed','2024-01-22 21:02:49','2024-01-22 21:02:49'),(136,NULL,'user','2024-01-22 21:06:44','2024-01-22 21:06:44'),(137,NULL,'default','2024-01-22 21:06:44','2024-01-22 21:06:44'),(138,NULL,'default','2024-01-22 21:06:44','2024-01-22 21:06:44'),(139,NULL,'ahmed','2024-01-22 21:06:48','2024-01-22 21:06:48'),(140,NULL,'user','2024-01-22 21:08:50','2024-01-22 21:08:50'),(141,NULL,'default','2024-01-22 21:08:50','2024-01-22 21:08:50'),(142,NULL,'default','2024-01-22 21:08:50','2024-01-22 21:08:50'),(143,NULL,'ahmed','2024-01-22 21:08:54','2024-01-22 21:08:54'),(144,NULL,'user','2024-01-22 21:44:27','2024-01-22 21:44:27'),(145,NULL,'default','2024-01-22 21:44:27','2024-01-22 21:44:27'),(146,NULL,'default','2024-01-22 21:44:27','2024-01-22 21:44:27'),(147,NULL,'ahmed','2024-01-22 21:44:32','2024-01-22 21:44:32'),(148,NULL,'user','2024-01-22 21:49:12','2024-01-22 21:49:12'),(149,NULL,'default','2024-01-22 21:49:12','2024-01-22 21:49:12'),(150,NULL,'default','2024-01-22 21:49:12','2024-01-22 21:49:12'),(151,NULL,'ahmed','2024-01-22 21:49:16','2024-01-22 21:49:16'),(152,NULL,'user','2024-01-22 21:54:12','2024-01-22 21:54:12'),(153,NULL,'default','2024-01-22 21:54:12','2024-01-22 21:54:12'),(154,NULL,'default','2024-01-22 21:54:12','2024-01-22 21:54:12'),(155,NULL,'ahmed','2024-01-22 21:54:16','2024-01-22 21:54:16'),(156,NULL,'user','2024-01-22 21:58:21','2024-01-22 21:58:21'),(157,NULL,'default','2024-01-22 21:58:21','2024-01-22 21:58:21'),(158,NULL,'default','2024-01-22 21:58:21','2024-01-22 21:58:21'),(159,NULL,'ahmed','2024-01-22 21:58:25','2024-01-22 21:58:25'),(160,NULL,'user','2024-01-22 21:59:57','2024-01-22 21:59:57'),(161,NULL,'default','2024-01-22 21:59:57','2024-01-22 21:59:57'),(162,NULL,'default','2024-01-22 21:59:57','2024-01-22 21:59:57'),(163,NULL,'ahmed','2024-01-22 22:00:03','2024-01-22 22:00:03'),(164,NULL,'user','2024-01-22 22:03:17','2024-01-22 22:03:17'),(165,NULL,'default','2024-01-22 22:03:17','2024-01-22 22:03:17'),(166,NULL,'default','2024-01-22 22:03:17','2024-01-22 22:03:17'),(167,NULL,'ahmed','2024-01-22 22:03:22','2024-01-22 22:03:22'),(168,NULL,'user','2024-01-22 22:04:04','2024-01-22 22:04:04'),(169,NULL,'default','2024-01-22 22:04:04','2024-01-22 22:04:04'),(170,NULL,'default','2024-01-22 22:04:04','2024-01-22 22:04:04'),(171,NULL,'ahmed','2024-01-22 22:04:09','2024-01-22 22:04:09'),(172,NULL,'user','2024-01-22 22:27:13','2024-01-22 22:27:13'),(173,NULL,'default','2024-01-22 22:27:13','2024-01-22 22:27:13'),(174,NULL,'default','2024-01-22 22:27:13','2024-01-22 22:27:13'),(175,NULL,'ahmed','2024-01-22 22:27:17','2024-01-22 22:27:17'),(176,NULL,'user','2024-01-22 22:28:13','2024-01-22 22:28:13'),(177,NULL,'default','2024-01-22 22:28:13','2024-01-22 22:28:13'),(178,NULL,'default','2024-01-22 22:28:13','2024-01-22 22:28:13'),(179,NULL,'ahmed','2024-01-22 22:28:18','2024-01-22 22:28:18'),(180,NULL,'user','2024-01-22 22:31:33','2024-01-22 22:31:33'),(181,NULL,'default','2024-01-22 22:31:34','2024-01-22 22:31:34'),(182,NULL,'default','2024-01-22 22:31:34','2024-01-22 22:31:34'),(183,NULL,'ahmed','2024-01-22 22:31:37','2024-01-22 22:31:37'),(184,NULL,'user','2024-01-22 22:40:07','2024-01-22 22:40:07'),(185,NULL,'default','2024-01-22 22:40:07','2024-01-22 22:40:07'),(186,NULL,'default','2024-01-22 22:40:07','2024-01-22 22:40:07'),(187,NULL,'ahmed','2024-01-22 22:40:11','2024-01-22 22:40:11'),(188,NULL,'user','2024-01-22 22:49:51','2024-01-22 22:49:51'),(189,NULL,'default','2024-01-22 22:49:51','2024-01-22 22:49:51'),(190,NULL,'default','2024-01-22 22:49:52','2024-01-22 22:49:52'),(191,NULL,'ahmed','2024-01-22 22:49:55','2024-01-22 22:49:55'),(192,NULL,'user','2024-01-22 22:59:41','2024-01-22 22:59:41'),(193,NULL,'default','2024-01-22 22:59:41','2024-01-22 22:59:41'),(194,NULL,'default','2024-01-22 22:59:41','2024-01-22 22:59:41'),(195,NULL,'ahmed','2024-01-22 22:59:45','2024-01-22 22:59:45'),(196,NULL,'user','2024-01-22 23:09:50','2024-01-22 23:09:50'),(197,NULL,'default','2024-01-22 23:09:50','2024-01-22 23:09:50'),(198,NULL,'default','2024-01-22 23:09:50','2024-01-22 23:09:50'),(199,NULL,'ahmed','2024-01-22 23:09:59','2024-01-22 23:09:59'),(200,NULL,'user','2024-01-22 23:18:36','2024-01-22 23:18:36'),(201,NULL,'default','2024-01-22 23:18:36','2024-01-22 23:18:36'),(202,NULL,'default','2024-01-22 23:18:36','2024-01-22 23:18:36'),(203,NULL,'ahmed','2024-01-22 23:18:40','2024-01-22 23:18:40'),(204,NULL,'user','2024-01-22 23:36:50','2024-01-22 23:36:50'),(205,NULL,'default','2024-01-22 23:36:50','2024-01-22 23:36:50'),(206,NULL,'default','2024-01-22 23:36:50','2024-01-22 23:36:50'),(207,NULL,'ahmed','2024-01-22 23:36:54','2024-01-22 23:36:54'),(208,NULL,'user','2024-01-23 00:07:40','2024-01-23 00:07:40'),(209,NULL,'default','2024-01-23 00:07:40','2024-01-23 00:07:40'),(210,NULL,'default','2024-01-23 00:07:40','2024-01-23 00:07:40'),(211,NULL,'ahmed','2024-01-23 00:07:44','2024-01-23 00:07:44');
/*!40000 ALTER TABLE `fridges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredients` (
  `ingredient_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `quantity` int DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `fridge_id` int DEFAULT NULL,
  PRIMARY KEY (`ingredient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (24,'eggs',10,'2024-12-12','Meat',NULL,NULL),(31,'eggs',10,'2024-12-12','Meat','grams',23);
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredientsofrecipe`
--

DROP TABLE IF EXISTS `ingredientsofrecipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredientsofrecipe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `r_id` int DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=856 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientsofrecipe`
--

LOCK TABLES `ingredientsofrecipe` WRITE;
/*!40000 ALTER TABLE `ingredientsofrecipe` DISABLE KEYS */;
INSERT INTO `ingredientsofrecipe` VALUES (816,'eggs',6,'2024-01-23',661447,'servings'),(817,'cream cheese',6,'2024-01-23',661447,'servings'),(818,'ham',6,'2024-01-23',661447,'servings'),(819,'eggs',4,'2024-01-23',991625,''),(820,'cadbury eggs',6,'2024-01-23',991625,''),(821,'milk',1,'2024-01-23',991625,'cup'),(822,'oil',0,'2024-01-23',991625,'cup'),(823,'vanilla',1,'2024-01-23',991625,'tsp'),(824,'eggs',6,'2024-01-23',663338,'large'),(825,'eggs',2,'2024-01-23',663338,''),(826,'pork sausage',16,'2024-01-23',663338,'oz'),(827,'panko breadcrumbs',2,'2024-01-23',663338,'cup'),(828,'vegetable oil',4,'2024-01-23',663338,'cups'),(829,'g pre-made agnolotti/ravioli',13,'2024-01-23',631807,'oz'),(830,'egg',1,'2024-01-23',631807,''),(831,'breadcrumbs',2,'2024-01-23',631807,'cup'),(832,'eggs',8,'2024-01-23',641890,'large'),(833,'milk',0,'2024-01-23',641890,'cup'),(834,'salt and pepper',4,'2024-01-23',641890,'servings'),(835,'jack cheese',1,'2024-01-23',641890,'cup'),(836,'bulk sausage',1,'2024-01-23',659581,'pound'),(837,'corn meal',1,'2024-01-23',659581,'cup'),(838,'eggs',9,'2024-01-23',659581,''),(839,'brown sugar',1,'2024-01-23',633574,'cup'),(840,'brown sugar',1,'2024-01-23',633574,'cup'),(841,'eggs',4,'2024-01-23',633574,''),(842,'milk',2,'2024-01-23',633574,'cups'),(843,'vanilla',1,'2024-01-23',633574,'teaspoon'),(844,'bread crumbs',1,'2024-01-23',635964,'cup'),(845,'eggs',4,'2024-01-23',635964,''),(846,'milk',1,'2024-01-23',635964,'cup'),(847,'pch salt',1,'2024-01-23',635964,''),(848,'eggs',8,'2024-01-23',646545,'large'),(849,'american cheese',8,'2024-01-23',646545,'slices'),(850,'grain muffins',4,'2024-01-23',646545,''),(851,'heart shaped cookie cutter',3,'2024-01-23',646545,'inch'),(852,'eggs',2,'2024-01-23',1747693,'large'),(853,'kirkland signature bacon',2,'2024-01-23',1747693,'strips'),(854,'avocado',1,'2024-01-23',1747693,''),(855,'strawberries',1,'2024-01-23',1747693,'cup');
/*!40000 ALTER TABLE `ingredientsofrecipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nutrition_info`
--

DROP TABLE IF EXISTS `nutrition_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nutrition_info` (
  `nutrition_id` int NOT NULL AUTO_INCREMENT,
  `recipe_id` int DEFAULT NULL,
  `calories` double DEFAULT NULL,
  `fat` double DEFAULT NULL,
  `protein` double DEFAULT NULL,
  `carbs` double DEFAULT NULL,
  `fiber` double DEFAULT NULL,
  `sugar` double DEFAULT NULL,
  `sodium` double DEFAULT NULL,
  PRIMARY KEY (`nutrition_id`),
  KEY `recipe_id` (`recipe_id`),
  CONSTRAINT `nutrition_info_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nutrition_info`
--

LOCK TABLES `nutrition_info` WRITE;
/*!40000 ALTER TABLE `nutrition_info` DISABLE KEYS */;
INSERT INTO `nutrition_info` VALUES (1,661447,211.6,14.7,18.52,0.07,0,0.05,1013.33),(2,991625,241.29,20.72,10.57,2.52,0,2.32,119.65),(3,663338,658.24,56.33,22.23,14.86,0.9,1.48,719.03),(4,631807,965.04,28.15,40.62,134.46,8.92,8.79,1761.12),(5,641890,246.26,19.16,16.48,1.24,0,0.88,424.56),(6,659581,296.48,20.47,16.6,10.07,0.61,1.02,529.72),(7,633574,231.04,5.39,6.41,40.02,0,39.63,82.86),(8,635964,824.68,30.27,44.6,90.4,4.86,19.08,1133.2),(9,646545,212.16,12.28,12.67,13.26,1.92,3.17,529.01),(10,1747693,510.32,41.96,20.65,15.39,8.17,4.55,441.04),(11,661447,211.6,14.7,18.52,0.07,0,0.05,1013.33),(12,991625,241.29,20.72,10.57,2.52,0,2.32,119.65),(13,663338,658.24,56.33,22.23,14.86,0.9,1.48,719.03),(14,631807,965.04,28.15,40.62,134.46,8.92,8.79,1761.12),(15,641890,246.26,19.16,16.48,1.24,0,0.88,424.56),(16,659581,296.48,20.47,16.6,10.07,0.61,1.02,529.72),(17,633574,231.04,5.39,6.41,40.02,0,39.63,82.86),(18,635964,824.68,30.27,44.6,90.4,4.86,19.08,1133.2),(19,646545,212.16,12.28,12.67,13.26,1.92,3.17,529.01),(20,1747693,510.32,41.96,20.65,15.39,8.17,4.55,441.04);
/*!40000 ALTER TABLE `nutrition_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `recipe_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `imageURL` varchar(255) DEFAULT NULL,
  `allergens_list` varchar(255) DEFAULT NULL,
  `instructions_list` text,
  PRIMARY KEY (`recipe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1747694 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (631807,'Toasted\" Agnolotti (or Ravioli)','https://spoonacular.com/recipeImages/631807-556x370.jpg','','1. Preheat oven to 180 degrees Celsius (350 F) for a fan-forced oven or 200 degrees Celsius (392 F) for a convection oven.\n2. Line a baking tray with baking paper.\n3. Spray a thin layer of olive oil (any oil of your choice will do) on the baking paper. Set aside.\n4. Crack and beat an egg on a plate. On a separate plate  add breadcrumbs.\n5. Dip agnolotti in the beaten egg first.\n6. Then coat it with breadcrumbs.  Repeat step 5 and 6 with the remaining agnolotti until egg and breadcrumbs are finished.\n7. Place the crumbed agnolotti onto a baking tray. Once youve completed step 5 and 6, spray another thin layer of oil over the crumbed aganolotti. Bake the crumbed agnolotti for 25 minutes or until golden brown.\n8. Serve immediately with pasta sauce or ketchup.'),(633574,'Baked Custard','https://spoonacular.com/recipeImages/633574-556x370.jpg','','1. Combine eggs, sugar and 1/4 teaspoon salt. Slowly stir in slightly cooled milk and vanilla. Fill six 5-ounce custard cups; set in shallow pan on oven rack. Pour hot water into pan, 1 inch deep.\n2. Bake at 325 degrees for 40 to 45 minutes, or until knife inserted off- center comes out clean. Serve warm or chilled. To unmold chilled custard, first loosen edge; then slip point of knife down side to let air in. Invert.\n3. For one large custard, bake in 1 quart casserole about 60 minutes.'),(635964,'Bread Omlette','https://spoonacular.com/recipeImages/635964-556x370.jpg','','1. Pahile, pan garma garam kijiye....phir ek murga ka anda laiye....use sohlaiye.....phir chamach layiye....phir ande se sorry kahiye.....phir usko phod dijiye ..phir brad laiye .....use katiye aur uspar fevikol lagaiye aur ande se chipka dijiye .....phir palat dijiye ............phir bina plate mein rakhe..apne jaha man kare waha dal dijiye.........aur message kijiy.e'),(641890,'Easy Cheesy Scrambled Eggs','https://spoonacular.com/recipeImages/641890-556x370.jpg','','1. Scramble eggs with milk or cream and some salt and pepper.\n2. Cook in a nonstick skillet over medium heat, stirring to lift the cooked eggs from the bottom of the pan as they cook.\n3. When the eggs are just about cooked, toss in jack cheese and cook for about 1-2 minutes more just to melt the cheese.\n4. Serve as is, over toast, or in a tortilla.'),(646545,'Heart Shaped Egg & Cheese Nibbles','https://spoonacular.com/recipeImages/646545-556x370.jpg','','1. )  Melt butter in a large sprayed skillet over medium-high heat with butter and toast English Muffins on a large baking tray in the oven.\n2. )  While muffins are toasting crack eggs gently into a mixing bowl and do not whisk.  Pour eggs into greased pan and let cook until set.\n3. )  Slide the eggs onto a clean surface and using your cookie cutter cut out the heart shape around the yolk.\n4. )  Place a piece a piece of cheese on top of the toasted English muffin along with egg cut-outs. SERVE & ENJOY!!!\n5. Nic\'s Tips - If you would like the eggs more cooked, place the open-faced sandwich back in the oven on your baking sheet for a couple of minutes before serving.'),(659581,'Scotch Eggs','https://spoonacular.com/recipeImages/659581-556x370.jpg','','1. Divide sausage into 8 portions. On a lightly crumb sprinkled surface, pat out each portion to about 1/8 inch thickness.\n2. Wrap 1 sausage portion completely around 1 hard boiled egg, pressing edges together to seal.\n3. Repeat with remaining sausage and hard boiled eggs.\n4. Dip sausage-covered eggs in 1 beaten egg and then roll in breadcrumbs.\n5. Deep fry or place on baking sheet and bake in a 375 degree oven for 20 minutes until lightly browned.'),(661447,'Square Deviled Eggs','https://spoonacular.com/recipeImages/661447-556x370.jpg','','1. To make square hard boiled eggs, you\'ll need an Egg cuber or Square Egg Press. (See note in About section on where to purchase)\n2. First boil your eggs, then slide the egg inside the press and screw the top down so it pushes the egg into the corners.\n3. Let the egg cool and remove it from the mold. For better results use medium size eggs.\n4. If you intend to prepare this for a party, I suggest you buy several cubers, this way you can boil and chill several eggs at a time, or it will take you a lot of time.\n5. To prepare hard boiled eggs, place eggs in a saucepan, cover with cold water and bring to a boil over medium heat. As soon as the water comes to a full boil, let the eggs boil for 5 minutes, and then remove from heat and let stand covered in hot water 10 minutes .\n6. Filling is made with cream cheese, ham and egg yolk, it tastes very soft, it is ideal for kids.'),(663338,'The Scotch Egg','https://spoonacular.com/recipeImages/663338-556x370.jpg','','1. Hard boil your eggs.  To get perfectly hard boiled eggs, once the water is boiling, add your eggs and set your timer for 9 1/2 minutes.  As soon as your timer goes off, remove the eggs and put them in a bath of cold water.\n2. Once your eggs are cooked and shells are removed, set up an assembly line with the beaten eggs, sausage meat and bread crumbs.\n3. Now you\'ll want to take about a 1/4 cup of the ground sausage, form a disk and begin to shape it around the hardboiled egg.\n4. Take the sausage-covered egg and dip it in the beaten egg.\n5. Roll the sausage-covered egg in the bread crumbs until it is generously coated.\n6. The final step is to fry those eggs up!  Once all of your eggs are coated in the bread crumbs, heat up your oil over medium high heat.  You\'ll know your oil is at the right temperature when you drop a few bread crumbs in the pot and it begins to sizzle.  Take care not to add the eggs to the oil to early - if the oil isn\'t hot enough it will just saturate the bread crumbs and it won\'t be as tasty.\n7. Drop two eggs at a time in the hot oil and cook them until very golden brown, about 6-7 minutes.  Make sure you give the eggs enough time in the oil so the sausage fully cooks.\n8. Remove the fried eggs from the oil and place on a plate with paper towels to remove the excess oil. Cut the eggs in half or quarters and serve while hot.'),(991625,'Nutella Buttercream Cupcakes with Hidden Cadbury Egg','https://spoonacular.com/recipeImages/991625-556x370.jpg','','1. Preheat oven to 350 degrees.\n2. Grease 6 jumbo muffin tin.\n3. Combine cake mix milk, vanilla and oil. Mix till combined.\n4. Add eggs beat till mixed well. Bake for 18- 21 min\n5. After cooking cupcakes use a spoon and cut out enough area to insert Cadbury egg.\n6. Making sure the smaller part of the egg is facing up.\n7. Frost the cupcake hiding the Cadbury egg.'),(1747693,'Your Basic Low Carb Breakfast','https://spoonacular.com/recipeImages/1747693-556x370.jpg','','1. Cook 2 eggs to any style of your liking\n2. Microwave 2 strips of Kirkland Signature Fully-Cooked Bacon to your desired level of crispiness\n3. Slice the avocado in half and scoop out the insides\n4. Slice strawberries into 1/2 measuring cup\n5. Enjoy!');
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe_ingredient`
--

DROP TABLE IF EXISTS `recipe_ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe_ingredient` (
  `recipe_id` int NOT NULL,
  `ingredientsofrecipe_id` int NOT NULL,
  PRIMARY KEY (`recipe_id`,`ingredientsofrecipe_id`),
  KEY `ingredientsofrecipe_id` (`ingredientsofrecipe_id`),
  CONSTRAINT `recipe_ingredient_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`),
  CONSTRAINT `recipe_ingredient_ibfk_2` FOREIGN KEY (`ingredientsofrecipe_id`) REFERENCES `ingredientsofrecipe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe_ingredient`
--

LOCK TABLES `recipe_ingredient` WRITE;
/*!40000 ALTER TABLE `recipe_ingredient` DISABLE KEYS */;
/*!40000 ALTER TABLE `recipe_ingredient` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-23  1:09:32
