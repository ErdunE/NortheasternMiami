-- MariaDB dump 10.19  Distrib 10.4.28-MariaDB, for osx10.10 (x86_64)
--
-- Host: localhost    Database: CoffeeDB
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
  `bean_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `farm_id` bigint(20) unsigned NOT NULL,
  `varietal` varchar(100) NOT NULL,
  `processing_method` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bean_id`),
  KEY `farm_id` (`farm_id`),
  CONSTRAINT `beans_ibfk_1` FOREIGN KEY (`farm_id`) REFERENCES `Farms` (`farm_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Farms`
--

DROP TABLE IF EXISTS `Farms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Farms` (
  `farm_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `farm_name` varchar(255) NOT NULL,
  `location_id` bigint(20) unsigned NOT NULL,
  `elevation` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`farm_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `farms_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `Locations` (`location_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Locations`
--

DROP TABLE IF EXISTS `Locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Locations` (
  `location_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `city` varchar(100) DEFAULT NULL,
  `region` varchar(100) DEFAULT NULL,
  `country` varchar(255) NOT NULL,
  PRIMARY KEY (`location_id`),
  UNIQUE KEY `unique_region_country` (`region`,`country`),
  UNIQUE KEY `unique_city_region_country` (`city`,`region`,`country`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Products`
--

DROP TABLE IF EXISTS `Products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Products` (
  `product_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `roaster_id` bigint(20) unsigned NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `roast_date` date NOT NULL,
  `roast_level` varchar(50) DEFAULT NULL,
  `price_per_100g` decimal(10,2) NOT NULL,
  PRIMARY KEY (`product_id`),
  KEY `roaster_id` (`roaster_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`roaster_id`) REFERENCES `Roasters` (`roaster_id`) ON DELETE CASCADE,
  CONSTRAINT `CONSTRAINT_1` CHECK (`roast_level` in ('Light','Medium','Medium-Dark','Dark'))
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Products_Beans`
--

DROP TABLE IF EXISTS `Products_Beans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Products_Beans` (
  `product_id` bigint(20) unsigned NOT NULL,
  `bean_id` bigint(20) unsigned NOT NULL,
  `percentage` decimal(5,2) DEFAULT NULL CHECK (`percentage` between 0 and 100),
  `role` varchar(10) NOT NULL,
  PRIMARY KEY (`product_id`,`bean_id`),
  KEY `bean_id` (`bean_id`),
  CONSTRAINT `products_beans_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `Products` (`product_id`) ON DELETE CASCADE,
  CONSTRAINT `products_beans_ibfk_2` FOREIGN KEY (`bean_id`) REFERENCES `Beans` (`bean_id`) ON DELETE CASCADE,
  CONSTRAINT `CONSTRAINT_1` CHECK (`role` in ('Primary','Blend'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Products_TastingNotes`
--

DROP TABLE IF EXISTS `Products_TastingNotes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Products_TastingNotes` (
  `product_id` bigint(20) unsigned NOT NULL,
  `note_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`product_id`,`note_id`),
  KEY `note_id` (`note_id`),
  CONSTRAINT `products_tastingnotes_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `Products` (`product_id`) ON DELETE CASCADE,
  CONSTRAINT `products_tastingnotes_ibfk_2` FOREIGN KEY (`note_id`) REFERENCES `TastingNotes` (`note_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Ratings`
--

DROP TABLE IF EXISTS `Ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ratings` (
  `rating_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `emoji` varchar(10) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Roasters`
--

DROP TABLE IF EXISTS `Roasters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Roasters` (
  `roaster_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `location_id` bigint(20) unsigned NOT NULL,
  `roaster_name` varchar(255) NOT NULL,
  `website` varchar(255) DEFAULT NULL,
  `is_abandoned` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`roaster_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `roasters_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `Locations` (`location_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TastingLogs`
--

DROP TABLE IF EXISTS `TastingLogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TastingLogs` (
  `log_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `product_id` bigint(20) unsigned NOT NULL,
  `tasting_date` date NOT NULL,
  `rating_id` bigint(20) unsigned DEFAULT NULL,
  `brew_method` varchar(100) DEFAULT NULL,
  `grind_size` varchar(50) DEFAULT NULL,
  `water_temperature` int(11) DEFAULT NULL CHECK (`water_temperature` between 30 and 100),
  `bloom_count` int(11) DEFAULT NULL,
  `user_notes` text DEFAULT NULL,
  PRIMARY KEY (`log_id`),
  KEY `user_id` (`user_id`),
  KEY `product_id` (`product_id`),
  KEY `rating_id` (`rating_id`),
  CONSTRAINT `tastinglogs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `tastinglogs_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `Products` (`product_id`) ON DELETE CASCADE,
  CONSTRAINT `tastinglogs_ibfk_3` FOREIGN KEY (`rating_id`) REFERENCES `Ratings` (`rating_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TastingLogs_TastingNotes`
--

DROP TABLE IF EXISTS `TastingLogs_TastingNotes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TastingLogs_TastingNotes` (
  `log_id` bigint(20) unsigned NOT NULL,
  `note_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`log_id`,`note_id`),
  KEY `note_id` (`note_id`),
  CONSTRAINT `tastinglogs_tastingnotes_ibfk_1` FOREIGN KEY (`log_id`) REFERENCES `TastingLogs` (`log_id`) ON DELETE CASCADE,
  CONSTRAINT `tastinglogs_tastingnotes_ibfk_2` FOREIGN KEY (`note_id`) REFERENCES `TastingNotes` (`note_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TastingNotes`
--

DROP TABLE IF EXISTS `TastingNotes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TastingNotes` (
  `note_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `note` varchar(255) NOT NULL,
  PRIMARY KEY (`note_id`),
  UNIQUE KEY `note` (`note`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-13 18:28:46
