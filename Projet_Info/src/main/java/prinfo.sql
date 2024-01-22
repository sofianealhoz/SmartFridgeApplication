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
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fridges`
--

LOCK TABLES `fridges` WRITE;
/*!40000 ALTER TABLE `fridges` DISABLE KEYS */;
INSERT INTO `fridges` VALUES (20,NULL,'user','2024-01-22 17:38:48','2024-01-22 17:38:48'),(21,NULL,'default','2024-01-22 17:38:48','2024-01-22 17:38:48'),(22,NULL,'default','2024-01-22 17:38:48','2024-01-22 17:38:48'),(23,NULL,'ahmed','2024-01-22 17:39:15','2024-01-22 17:39:15'),(24,NULL,'test','2024-01-22 17:41:08','2024-01-22 17:41:08'),(25,NULL,'user','2024-01-22 17:57:17','2024-01-22 17:57:17'),(26,NULL,'default','2024-01-22 17:57:17','2024-01-22 17:57:17'),(27,NULL,'default','2024-01-22 17:57:17','2024-01-22 17:57:17'),(28,NULL,'ahmed','2024-01-22 17:57:22','2024-01-22 17:57:22'),(29,NULL,'test','2024-01-22 17:57:30','2024-01-22 17:57:30'),(30,NULL,'user','2024-01-22 18:03:48','2024-01-22 18:03:48'),(31,NULL,'default','2024-01-22 18:03:48','2024-01-22 18:03:48'),(32,NULL,'default','2024-01-22 18:03:48','2024-01-22 18:03:48'),(33,NULL,'user','2024-01-22 18:07:33','2024-01-22 18:07:33'),(34,NULL,'default','2024-01-22 18:07:33','2024-01-22 18:07:33'),(35,NULL,'default','2024-01-22 18:07:33','2024-01-22 18:07:33'),(36,NULL,'ahmed','2024-01-22 18:08:07','2024-01-22 18:08:07'),(37,NULL,'user','2024-01-22 18:08:20','2024-01-22 18:08:20'),(38,NULL,'default','2024-01-22 18:08:20','2024-01-22 18:08:20'),(39,NULL,'default','2024-01-22 18:08:20','2024-01-22 18:08:20'),(40,NULL,'ahmed','2024-01-22 18:08:30','2024-01-22 18:08:30'),(41,NULL,'user','2024-01-22 18:12:19','2024-01-22 18:12:19'),(42,NULL,'default','2024-01-22 18:12:19','2024-01-22 18:12:19'),(43,NULL,'default','2024-01-22 18:12:19','2024-01-22 18:12:19'),(44,NULL,'ahmed','2024-01-22 18:12:36','2024-01-22 18:12:36'),(45,NULL,'user','2024-01-22 18:14:00','2024-01-22 18:14:00'),(46,NULL,'default','2024-01-22 18:14:00','2024-01-22 18:14:00'),(47,NULL,'default','2024-01-22 18:14:00','2024-01-22 18:14:00'),(48,NULL,'ahmed','2024-01-22 18:14:09','2024-01-22 18:14:09'),(49,NULL,'user','2024-01-22 18:20:53','2024-01-22 18:20:53'),(50,NULL,'default','2024-01-22 18:20:53','2024-01-22 18:20:53'),(51,NULL,'default','2024-01-22 18:20:53','2024-01-22 18:20:53'),(52,NULL,'ahmed','2024-01-22 18:20:59','2024-01-22 18:20:59'),(53,NULL,'user','2024-01-22 18:22:09','2024-01-22 18:22:09'),(54,NULL,'default','2024-01-22 18:22:09','2024-01-22 18:22:09'),(55,NULL,'default','2024-01-22 18:22:09','2024-01-22 18:22:09'),(56,NULL,'ahmed','2024-01-22 18:22:14','2024-01-22 18:22:14'),(57,NULL,'user','2024-01-22 18:28:16','2024-01-22 18:28:16'),(58,NULL,'default','2024-01-22 18:28:16','2024-01-22 18:28:16'),(59,NULL,'default','2024-01-22 18:28:16','2024-01-22 18:28:16'),(60,NULL,'ahmed','2024-01-22 18:28:22','2024-01-22 18:28:22'),(61,NULL,'user','2024-01-22 18:44:09','2024-01-22 18:44:09'),(62,NULL,'default','2024-01-22 18:44:09','2024-01-22 18:44:09'),(63,NULL,'default','2024-01-22 18:44:09','2024-01-22 18:44:09'),(64,NULL,'ahmed','2024-01-22 18:44:15','2024-01-22 18:44:15'),(65,NULL,'user','2024-01-22 18:53:56','2024-01-22 18:53:56'),(66,NULL,'default','2024-01-22 18:53:56','2024-01-22 18:53:56'),(67,NULL,'default','2024-01-22 18:53:56','2024-01-22 18:53:56'),(68,NULL,'user','2024-01-22 19:07:58','2024-01-22 19:07:58'),(69,NULL,'default','2024-01-22 19:07:58','2024-01-22 19:07:58'),(70,NULL,'default','2024-01-22 19:07:58','2024-01-22 19:07:58'),(71,NULL,'ahmed','2024-01-22 19:08:03','2024-01-22 19:08:03'),(72,NULL,'user','2024-01-22 19:59:52','2024-01-22 19:59:52'),(73,NULL,'default','2024-01-22 19:59:52','2024-01-22 19:59:52'),(74,NULL,'default','2024-01-22 19:59:52','2024-01-22 19:59:52'),(75,NULL,'ahmed','2024-01-22 20:00:04','2024-01-22 20:00:04'),(76,NULL,'user','2024-01-22 20:00:56','2024-01-22 20:00:56'),(77,NULL,'default','2024-01-22 20:00:56','2024-01-22 20:00:56'),(78,NULL,'default','2024-01-22 20:00:56','2024-01-22 20:00:56'),(79,NULL,'ahmed','2024-01-22 20:01:00','2024-01-22 20:01:00'),(80,NULL,'user','2024-01-22 20:01:53','2024-01-22 20:01:53'),(81,NULL,'default','2024-01-22 20:01:53','2024-01-22 20:01:53'),(82,NULL,'default','2024-01-22 20:01:53','2024-01-22 20:01:53'),(83,NULL,'ahmed','2024-01-22 20:02:00','2024-01-22 20:02:00'),(84,NULL,'user','2024-01-22 20:08:35','2024-01-22 20:08:35'),(85,NULL,'default','2024-01-22 20:08:35','2024-01-22 20:08:35'),(86,NULL,'default','2024-01-22 20:08:35','2024-01-22 20:08:35'),(87,NULL,'ahmed','2024-01-22 20:08:40','2024-01-22 20:08:40'),(88,NULL,'user','2024-01-22 20:20:08','2024-01-22 20:20:08'),(89,NULL,'default','2024-01-22 20:20:08','2024-01-22 20:20:08'),(90,NULL,'default','2024-01-22 20:20:08','2024-01-22 20:20:08'),(91,NULL,'ahmed','2024-01-22 20:20:12','2024-01-22 20:20:12'),(92,NULL,'user','2024-01-22 20:21:29','2024-01-22 20:21:29'),(93,NULL,'default','2024-01-22 20:21:29','2024-01-22 20:21:29'),(94,NULL,'default','2024-01-22 20:21:29','2024-01-22 20:21:29'),(95,NULL,'ahmed','2024-01-22 20:21:34','2024-01-22 20:21:34'),(96,NULL,'user','2024-01-22 20:23:10','2024-01-22 20:23:10'),(97,NULL,'default','2024-01-22 20:23:10','2024-01-22 20:23:10'),(98,NULL,'default','2024-01-22 20:23:10','2024-01-22 20:23:10'),(99,NULL,'ahmed','2024-01-22 20:23:14','2024-01-22 20:23:14'),(100,NULL,'user','2024-01-22 20:24:36','2024-01-22 20:24:36'),(101,NULL,'default','2024-01-22 20:24:36','2024-01-22 20:24:36'),(102,NULL,'default','2024-01-22 20:24:36','2024-01-22 20:24:36'),(103,NULL,'ahmed','2024-01-22 20:24:43','2024-01-22 20:24:43'),(104,NULL,'user','2024-01-22 20:39:53','2024-01-22 20:39:53'),(105,NULL,'default','2024-01-22 20:39:53','2024-01-22 20:39:53'),(106,NULL,'default','2024-01-22 20:39:53','2024-01-22 20:39:53'),(107,NULL,'ahmed','2024-01-22 20:39:58','2024-01-22 20:39:58'),(108,NULL,'user','2024-01-22 20:44:42','2024-01-22 20:44:42'),(109,NULL,'default','2024-01-22 20:44:42','2024-01-22 20:44:42'),(110,NULL,'default','2024-01-22 20:44:42','2024-01-22 20:44:42'),(111,NULL,'ahmed','2024-01-22 20:44:50','2024-01-22 20:44:50'),(112,NULL,'user','2024-01-22 20:46:29','2024-01-22 20:46:29'),(113,NULL,'default','2024-01-22 20:46:29','2024-01-22 20:46:29'),(114,NULL,'default','2024-01-22 20:46:29','2024-01-22 20:46:29'),(115,NULL,'ahmed','2024-01-22 20:46:47','2024-01-22 20:46:47'),(116,NULL,'user','2024-01-22 20:48:32','2024-01-22 20:48:32'),(117,NULL,'default','2024-01-22 20:48:32','2024-01-22 20:48:32'),(118,NULL,'default','2024-01-22 20:48:32','2024-01-22 20:48:32'),(119,NULL,'ahmed','2024-01-22 20:48:38','2024-01-22 20:48:38'),(120,NULL,'user','2024-01-22 20:54:03','2024-01-22 20:54:03'),(121,NULL,'default','2024-01-22 20:54:03','2024-01-22 20:54:03'),(122,NULL,'default','2024-01-22 20:54:03','2024-01-22 20:54:03'),(123,NULL,'ahmed','2024-01-22 20:54:20','2024-01-22 20:54:20'),(124,NULL,'user','2024-01-22 20:54:57','2024-01-22 20:54:57'),(125,NULL,'default','2024-01-22 20:54:57','2024-01-22 20:54:57'),(126,NULL,'default','2024-01-22 20:54:57','2024-01-22 20:54:57'),(127,NULL,'ahmed','2024-01-22 20:55:18','2024-01-22 20:55:18'),(128,NULL,'user','2024-01-22 20:58:34','2024-01-22 20:58:34'),(129,NULL,'default','2024-01-22 20:58:35','2024-01-22 20:58:35'),(130,NULL,'default','2024-01-22 20:58:35','2024-01-22 20:58:35'),(131,NULL,'ahmed','2024-01-22 20:58:40','2024-01-22 20:58:40'),(132,NULL,'user','2024-01-22 21:02:44','2024-01-22 21:02:44'),(133,NULL,'default','2024-01-22 21:02:44','2024-01-22 21:02:44'),(134,NULL,'default','2024-01-22 21:02:44','2024-01-22 21:02:44'),(135,NULL,'ahmed','2024-01-22 21:02:49','2024-01-22 21:02:49'),(136,NULL,'user','2024-01-22 21:06:44','2024-01-22 21:06:44'),(137,NULL,'default','2024-01-22 21:06:44','2024-01-22 21:06:44'),(138,NULL,'default','2024-01-22 21:06:44','2024-01-22 21:06:44'),(139,NULL,'ahmed','2024-01-22 21:06:48','2024-01-22 21:06:48'),(140,NULL,'user','2024-01-22 21:08:50','2024-01-22 21:08:50'),(141,NULL,'default','2024-01-22 21:08:50','2024-01-22 21:08:50'),(142,NULL,'default','2024-01-22 21:08:50','2024-01-22 21:08:50'),(143,NULL,'ahmed','2024-01-22 21:08:54','2024-01-22 21:08:54'),(144,NULL,'user','2024-01-22 21:44:27','2024-01-22 21:44:27'),(145,NULL,'default','2024-01-22 21:44:27','2024-01-22 21:44:27'),(146,NULL,'default','2024-01-22 21:44:27','2024-01-22 21:44:27'),(147,NULL,'ahmed','2024-01-22 21:44:32','2024-01-22 21:44:32'),(148,NULL,'user','2024-01-22 21:49:12','2024-01-22 21:49:12'),(149,NULL,'default','2024-01-22 21:49:12','2024-01-22 21:49:12'),(150,NULL,'default','2024-01-22 21:49:12','2024-01-22 21:49:12'),(151,NULL,'ahmed','2024-01-22 21:49:16','2024-01-22 21:49:16'),(152,NULL,'user','2024-01-22 21:54:12','2024-01-22 21:54:12'),(153,NULL,'default','2024-01-22 21:54:12','2024-01-22 21:54:12'),(154,NULL,'default','2024-01-22 21:54:12','2024-01-22 21:54:12'),(155,NULL,'ahmed','2024-01-22 21:54:16','2024-01-22 21:54:16'),(156,NULL,'user','2024-01-22 21:58:21','2024-01-22 21:58:21'),(157,NULL,'default','2024-01-22 21:58:21','2024-01-22 21:58:21'),(158,NULL,'default','2024-01-22 21:58:21','2024-01-22 21:58:21'),(159,NULL,'ahmed','2024-01-22 21:58:25','2024-01-22 21:58:25'),(160,NULL,'user','2024-01-22 21:59:57','2024-01-22 21:59:57'),(161,NULL,'default','2024-01-22 21:59:57','2024-01-22 21:59:57'),(162,NULL,'default','2024-01-22 21:59:57','2024-01-22 21:59:57'),(163,NULL,'ahmed','2024-01-22 22:00:03','2024-01-22 22:00:03'),(164,NULL,'user','2024-01-22 22:03:17','2024-01-22 22:03:17'),(165,NULL,'default','2024-01-22 22:03:17','2024-01-22 22:03:17'),(166,NULL,'default','2024-01-22 22:03:17','2024-01-22 22:03:17'),(167,NULL,'ahmed','2024-01-22 22:03:22','2024-01-22 22:03:22'),(168,NULL,'user','2024-01-22 22:04:04','2024-01-22 22:04:04'),(169,NULL,'default','2024-01-22 22:04:04','2024-01-22 22:04:04'),(170,NULL,'default','2024-01-22 22:04:04','2024-01-22 22:04:04'),(171,NULL,'ahmed','2024-01-22 22:04:09','2024-01-22 22:04:09'),(172,NULL,'user','2024-01-22 22:27:13','2024-01-22 22:27:13'),(173,NULL,'default','2024-01-22 22:27:13','2024-01-22 22:27:13'),(174,NULL,'default','2024-01-22 22:27:13','2024-01-22 22:27:13'),(175,NULL,'ahmed','2024-01-22 22:27:17','2024-01-22 22:27:17'),(176,NULL,'user','2024-01-22 22:28:13','2024-01-22 22:28:13'),(177,NULL,'default','2024-01-22 22:28:13','2024-01-22 22:28:13'),(178,NULL,'default','2024-01-22 22:28:13','2024-01-22 22:28:13'),(179,NULL,'ahmed','2024-01-22 22:28:18','2024-01-22 22:28:18'),(180,NULL,'user','2024-01-22 22:31:33','2024-01-22 22:31:33'),(181,NULL,'default','2024-01-22 22:31:34','2024-01-22 22:31:34'),(182,NULL,'default','2024-01-22 22:31:34','2024-01-22 22:31:34'),(183,NULL,'ahmed','2024-01-22 22:31:37','2024-01-22 22:31:37');
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (24,'eggs',10,'2024-12-12','Meat',NULL,NULL),(29,'eggs',10,'2024-12-12','Meat','grams',23);
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
) ENGINE=InnoDB AUTO_INCREMENT=736 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientsofrecipe`
--

LOCK TABLES `ingredientsofrecipe` WRITE;
/*!40000 ALTER TABLE `ingredientsofrecipe` DISABLE KEYS */;
INSERT INTO `ingredientsofrecipe` VALUES (733,'eggs',6,'2024-01-22',661447,'servings'),(734,'cream cheese',6,'2024-01-22',661447,'servings'),(735,'ham',6,'2024-01-22',661447,'servings');
/*!40000 ALTER TABLE `ingredientsofrecipe` ENABLE KEYS */;
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
INSERT INTO `recipe` VALUES (661447,'Square Deviled Eggs','https://spoonacular.com/recipeImages/661447-556x370.jpg','','1. To make square hard boiled eggs, you\'ll need an Egg cuber or Square Egg Press. (See note in About section on where to purchase)\n2. First boil your eggs, then slide the egg inside the press and screw the top down so it pushes the egg into the corners.\n3. Let the egg cool and remove it from the mold. For better results use medium size eggs.\n4. If you intend to prepare this for a party, I suggest you buy several cubers, this way you can boil and chill several eggs at a time, or it will take you a lot of time.\n5. To prepare hard boiled eggs, place eggs in a saucepan, cover with cold water and bring to a boil over medium heat. As soon as the water comes to a full boil, let the eggs boil for 5 minutes, and then remove from heat and let stand covered in hot water 10 minutes .\n6. Filling is made with cream cheese, ham and egg yolk, it tastes very soft, it is ideal for kids.');
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

-- Dump completed on 2024-01-22 23:33:26
