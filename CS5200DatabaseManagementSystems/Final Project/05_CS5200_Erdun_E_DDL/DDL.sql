-- MariaDB dump 10.19  Distrib 10.4.28-MariaDB, for osx10.10 (x86_64)
--
-- Host: localhost    Database: beanvibes
-- ------------------------------------------------------
-- Server version	10.4.28-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Beans`
--

DROP TABLE IF EXISTS `Beans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Beans` (
  `bean_id` int(11) NOT NULL AUTO_INCREMENT,
  `varietal_id` int(11) NOT NULL,
  `farm_id` int(11) NOT NULL,
  `process_method_id` int(11) NOT NULL,
  PRIMARY KEY (`bean_id`),
  KEY `varietal_id` (`varietal_id`),
  KEY `farm_id` (`farm_id`),
  KEY `process_method_id` (`process_method_id`),
  CONSTRAINT `beans_ibfk_1` FOREIGN KEY (`varietal_id`) REFERENCES `Varietal` (`varietal_id`),
  CONSTRAINT `beans_ibfk_2` FOREIGN KEY (`farm_id`) REFERENCES `Farms` (`farm_id`),
  CONSTRAINT `beans_ibfk_3` FOREIGN KEY (`process_method_id`) REFERENCES `ProcessMethod` (`process_method_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `BrewMethod`
--

DROP TABLE IF EXISTS `BrewMethod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BrewMethod` (
  `brew_method_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`brew_method_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CityLocation`
--

DROP TABLE IF EXISTS `CityLocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CityLocation` (
  `location_id` int(11) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) DEFAULT NULL,
  `zipcode` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  CONSTRAINT `citylocation_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `Location` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Farms`
--

DROP TABLE IF EXISTS `Farms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Farms` (
  `farm_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `elevation` float DEFAULT NULL,
  `location_id` int(11) NOT NULL,
  PRIMARY KEY (`farm_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `farms_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `Location` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `GrindSize`
--

DROP TABLE IF EXISTS `GrindSize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GrindSize` (
  `grind_size_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`grind_size_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Location`
--

DROP TABLE IF EXISTS `Location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Location` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(50) NOT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProcessMethod`
--

DROP TABLE IF EXISTS `ProcessMethod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProcessMethod` (
  `process_method_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`process_method_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProductBean`
--

DROP TABLE IF EXISTS `ProductBean`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductBean` (
  `product_id` int(11) NOT NULL,
  `bean_id` int(11) NOT NULL,
  `percentage` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`product_id`,`bean_id`),
  KEY `bean_id` (`bean_id`),
  CONSTRAINT `productbean_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `Products` (`product_id`),
  CONSTRAINT `productbean_ibfk_2` FOREIGN KEY (`bean_id`) REFERENCES `Beans` (`bean_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProductTastingNote`
--

DROP TABLE IF EXISTS `ProductTastingNote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductTastingNote` (
  `product_id` int(11) NOT NULL,
  `note_id` int(11) NOT NULL,
  PRIMARY KEY (`product_id`,`note_id`),
  KEY `note_id` (`note_id`),
  CONSTRAINT `producttastingnote_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `Products` (`product_id`),
  CONSTRAINT `producttastingnote_ibfk_2` FOREIGN KEY (`note_id`) REFERENCES `TastingNote` (`note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Products`
--

DROP TABLE IF EXISTS `Products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Products` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `roaster_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `roast_level_id` int(11) NOT NULL,
  `is_limited_release` tinyint(1) DEFAULT 0,
  `price_per_100g` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `roaster_id` (`roaster_id`),
  KEY `roast_level_id` (`roast_level_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`roaster_id`) REFERENCES `Roasters` (`roaster_id`),
  CONSTRAINT `products_ibfk_2` FOREIGN KEY (`roast_level_id`) REFERENCES `RoastLevel` (`roast_level_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Rating`
--

DROP TABLE IF EXISTS `Rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rating` (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `emoji` varchar(10) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RegionLocation`
--

DROP TABLE IF EXISTS `RegionLocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RegionLocation` (
  `location_id` int(11) NOT NULL,
  `region` varchar(100) NOT NULL,
  PRIMARY KEY (`location_id`),
  CONSTRAINT `regionlocation_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `Location` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RoastBatch`
--

DROP TABLE IF EXISTS `RoastBatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RoastBatch` (
  `batch_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `roast_date` date NOT NULL,
  PRIMARY KEY (`batch_id`),
  UNIQUE KEY `unique_product_roast_date` (`product_id`,`roast_date`),
  CONSTRAINT `roastbatch_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `Products` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RoastLevel`
--

DROP TABLE IF EXISTS `RoastLevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RoastLevel` (
  `roast_level_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`roast_level_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Roasters`
--

DROP TABLE IF EXISTS `Roasters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Roasters` (
  `roaster_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `website_url` varchar(255) DEFAULT NULL,
  `location_id` int(11) NOT NULL,
  PRIMARY KEY (`roaster_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `roasters_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `Location` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TastingLogNote`
--

DROP TABLE IF EXISTS `TastingLogNote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TastingLogNote` (
  `log_id` int(11) NOT NULL,
  `note_id` int(11) NOT NULL,
  PRIMARY KEY (`log_id`,`note_id`),
  KEY `note_id` (`note_id`),
  CONSTRAINT `tastinglognote_ibfk_1` FOREIGN KEY (`log_id`) REFERENCES `TastingLogs` (`log_id`),
  CONSTRAINT `tastinglognote_ibfk_2` FOREIGN KEY (`note_id`) REFERENCES `TastingNote` (`note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TastingLogs`
--

DROP TABLE IF EXISTS `TastingLogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TastingLogs` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `batch_id` int(11) NOT NULL,
  `rating_id` int(11) NOT NULL,
  `brew_method_id` int(11) NOT NULL,
  `grind_size_id` int(11) NOT NULL,
  `water_temp` float DEFAULT NULL,
  `bloom_count` int(11) DEFAULT NULL,
  `brew_notes` text DEFAULT NULL,
  `test_date` date NOT NULL,
  PRIMARY KEY (`log_id`),
  KEY `user_id` (`user_id`),
  KEY `batch_id` (`batch_id`),
  KEY `rating_id` (`rating_id`),
  KEY `brew_method_id` (`brew_method_id`),
  KEY `grind_size_id` (`grind_size_id`),
  CONSTRAINT `tastinglogs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`),
  CONSTRAINT `tastinglogs_ibfk_2` FOREIGN KEY (`batch_id`) REFERENCES `RoastBatch` (`batch_id`),
  CONSTRAINT `tastinglogs_ibfk_3` FOREIGN KEY (`rating_id`) REFERENCES `Rating` (`rating_id`),
  CONSTRAINT `tastinglogs_ibfk_4` FOREIGN KEY (`brew_method_id`) REFERENCES `BrewMethod` (`brew_method_id`),
  CONSTRAINT `tastinglogs_ibfk_5` FOREIGN KEY (`grind_size_id`) REFERENCES `GrindSize` (`grind_size_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TastingNote`
--

DROP TABLE IF EXISTS `TastingNote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TastingNote` (
  `note_id` int(11) NOT NULL AUTO_INCREMENT,
  `note_name` varchar(30) NOT NULL,
  PRIMARY KEY (`note_id`),
  UNIQUE KEY `note_name` (`note_name`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserBeanStatus`
--

DROP TABLE IF EXISTS `UserBeanStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserBeanStatus` (
  `user_id` int(11) NOT NULL,
  `bean_id` int(11) NOT NULL,
  `is_given_up` tinyint(1) DEFAULT 0,
  `is_recent_used` tinyint(1) DEFAULT 0,
  `last_used_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`user_id`,`bean_id`),
  KEY `bean_id` (`bean_id`),
  CONSTRAINT `userbeanstatus_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`),
  CONSTRAINT `userbeanstatus_ibfk_2` FOREIGN KEY (`bean_id`) REFERENCES `Beans` (`bean_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserProductStatus`
--

DROP TABLE IF EXISTS `UserProductStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserProductStatus` (
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `is_given_up` tinyint(1) DEFAULT 0,
  `is_recent_used` tinyint(1) DEFAULT 0,
  `last_used_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`user_id`,`product_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `userproductstatus_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`),
  CONSTRAINT `userproductstatus_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `Products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserRoasterStatus`
--

DROP TABLE IF EXISTS `UserRoasterStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserRoasterStatus` (
  `user_id` int(11) NOT NULL,
  `roaster_id` int(11) NOT NULL,
  `is_given_up` tinyint(1) DEFAULT 0,
  `is_recent_used` tinyint(1) DEFAULT 0,
  `last_used_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`user_id`,`roaster_id`),
  KEY `roaster_id` (`roaster_id`),
  CONSTRAINT `userroasterstatus_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`),
  CONSTRAINT `userroasterstatus_ibfk_2` FOREIGN KEY (`roaster_id`) REFERENCES `Roasters` (`roaster_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `date_of_birth` date NOT NULL,
  `nickname` varchar(100) NOT NULL,
  `avatar_url` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `is_dark_theme` tinyint(1) DEFAULT 0,
  `notification_enabled` tinyint(1) DEFAULT 0,
  `language` varchar(20) DEFAULT 'en',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Varietal`
--

DROP TABLE IF EXISTS `Varietal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Varietal` (
  `varietal_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`varietal_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-15  0:11:15
