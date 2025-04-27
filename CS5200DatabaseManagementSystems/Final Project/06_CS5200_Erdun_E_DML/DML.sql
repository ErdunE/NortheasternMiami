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
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `Beans`
--

LOCK TABLES `Beans` WRITE;
/*!40000 ALTER TABLE `Beans` DISABLE KEYS */;
INSERT INTO `Beans` VALUES (1,6,1,1),(2,9,1,1),(3,10,1,1),(4,28,1,1),(8,10,5,1),(9,10,6,1),(10,7,7,2),(11,16,8,16),(12,6,9,10),(13,12,10,2),(14,12,11,1),(15,12,12,2),(16,7,13,3),(17,10,13,3),(18,4,14,1),(19,16,15,2),(20,24,16,16),(21,12,17,2),(22,12,18,1),(23,6,19,1),(24,10,20,17),(25,6,20,17),(26,6,21,1),(27,13,22,2),(28,12,23,18),(29,10,24,18),(30,13,25,2),(31,20,26,1),(32,12,27,16),(33,28,28,2),(34,17,26,13),(35,13,29,2),(36,26,30,1),(37,27,30,1),(38,12,31,2),(39,13,32,1),(40,13,33,1),(41,26,34,15),(42,8,35,1);
/*!40000 ALTER TABLE `Beans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `BrewMethod`
--

LOCK TABLES `BrewMethod` WRITE;
/*!40000 ALTER TABLE `BrewMethod` DISABLE KEYS */;
INSERT INTO `BrewMethod` VALUES (6,'AeroPress'),(5,'Cold Brew'),(1,'Drip Machine'),(2,'Espresso'),(4,'French Press'),(7,'Moka Pot'),(3,'Pour Over'),(8,'Syphon'),(9,'Turkish');
/*!40000 ALTER TABLE `BrewMethod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `CityLocation`
--

LOCK TABLES `CityLocation` WRITE;
/*!40000 ALTER TABLE `CityLocation` DISABLE KEYS */;
INSERT INTO `CityLocation` VALUES (1,'Pittsburgh','PA','15222'),(5,'Floyd','VA','24091'),(6,'Markham','Ontario','L3R0G9'),(7,'Regers','AR','72756'),(8,'Denver','CO','80204'),(21,'Los Angeles','CA','90048'),(23,'Cambridge','MA','02138'),(25,'NY','NY','11237'),(27,'Copenhagen','Capital Region','2200'),(28,'Melbourne','Victoria','3000'),(30,'Berlin','Berlin','10437'),(31,'Rogers','AR','72756'),(32,'Copenhagen','Capital Region','2200'),(33,'Rotterdam','South Holland','3011TA'),(34,'Shibuya','Tokyo','150-0031'),(35,'Kyoto','Kyoto','600-8028'),(36,'Amsterdam','North Holland','1053RL'),(37,'Prague','Prague','11000'),(38,'Bangkok','Bangkok','10110');
/*!40000 ALTER TABLE `CityLocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Farms`
--

LOCK TABLES `Farms` WRITE;
/*!40000 ALTER TABLE `Farms` DISABLE KEYS */;
INSERT INTO `Farms` VALUES (1,'Finca Isabel',1600,9),(5,'Finca Esperanza',1800,15),(6,'Finca Test',1800,15),(7,'Finca Chami',1650,15),(8,'Worka Sakaro Washing Station',1950,16),(9,'Finca Aroma Nativo',1750,17),(10,'Inmaculada Coffee Farm',1900,17),(11,'Gaia Estate',1800,18),(12,'Finca Tio Conejo',1900,15),(13,'Aponte Village Smallholders',2000,17),(14,'Muthingini Factory',1850,19),(15,'Mullugeta Muntasha Farm',1950,16),(16,'Finca Vinka',1950,20),(17,'Hachi Estate',1850,15),(18,'El Paraiso',1900,17),(19,'Sitio San Roque',1200,22),(20,'Finca Velvet',1700,15),(21,'La Pelota Estate',1800,15),(22,'Arbegona Cooperative',2100,16),(23,'Granja Paraiso 92',1950,15),(24,'Cordillera del Fuego',1775,24),(25,'Smallholders',2000,16),(26,'La Salsa',1600,26),(27,'Finca Deborah',1950,9),(28,'Fazenda Vinhal',1150,29),(29,'Chelichele Washing Station',2000,16),(30,'Kieni Cooperative',1850,19),(31,'Janson Estate',1850,9),(32,'Chelbesa Washing Station',2000,16),(33,'Nano Challa Coop',1900,16),(34,'Finca Santa Cruz',1900,20),(35,'Doi Saket Estate',1400,38);
/*!40000 ALTER TABLE `Farms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `GrindSize`
--

LOCK TABLES `GrindSize` WRITE;
/*!40000 ALTER TABLE `GrindSize` DISABLE KEYS */;
INSERT INTO `GrindSize` VALUES (2,'Coarse'),(1,'Extra Coarse'),(7,'Extra Fine'),(6,'Fine'),(4,'Medium'),(3,'Medium-Coarse'),(5,'Medium-Fine');
/*!40000 ALTER TABLE `GrindSize` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Location`
--

LOCK TABLES `Location` WRITE;
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;
INSERT INTO `Location` VALUES (1,'United States'),(5,'United States'),(6,'Canada'),(7,'United States'),(8,'United States'),(9,'Panama'),(15,'Colombia'),(16,'Ethiopia'),(17,'Colombia'),(18,'Tanzania'),(19,'Kenya'),(20,'Ecuador'),(21,'USA'),(22,'Philippines'),(23,'USA'),(24,'Costa Rica'),(25,'USA'),(26,'Honduras'),(27,'Denmark'),(28,'Australia'),(29,'Brazil'),(30,'Germany'),(31,'USA'),(32,'Denmark'),(33,'Netherlands'),(34,'Japan'),(35,'Japan'),(36,'Netherlands'),(37,'Czech Republic'),(38,'Thailand');
/*!40000 ALTER TABLE `Location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ProcessMethod`
--

LOCK TABLES `ProcessMethod` WRITE;
/*!40000 ALTER TABLE `ProcessMethod` DISABLE KEYS */;
INSERT INTO `ProcessMethod` VALUES (18,'Anaerobic'),(5,'Anaerobic Fermentation'),(16,'Anaerobic Natural'),(17,'Anaerobic Washed'),(6,'Carbonic Maceration'),(10,'Double Fermentation'),(14,'Dry-Hulled'),(9,'Enzymatic Process'),(3,'Honey'),(12,'Hybrid Process'),(7,'Lactic Fermentation'),(2,'Natural'),(15,'Pulped Natural'),(11,'Semi-Washed'),(13,'Thermal Shock'),(1,'Washed'),(4,'Wet-Hulled'),(8,'Yeast Fermentation');
/*!40000 ALTER TABLE `ProcessMethod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ProductBean`
--

LOCK TABLES `ProductBean` WRITE;
/*!40000 ALTER TABLE `ProductBean` DISABLE KEYS */;
INSERT INTO `ProductBean` VALUES (2,1,25.00),(2,2,25.00),(2,3,25.00),(2,4,25.00),(2,8,100.00),(2,9,100.00),(22,10,100.00),(23,11,100.00),(24,12,100.00),(25,13,100.00),(26,14,100.00),(27,15,100.00),(28,16,50.00),(28,17,50.00),(29,18,100.00),(30,19,100.00),(31,20,100.00),(32,21,100.00),(33,22,100.00),(34,23,100.00),(35,24,50.00),(35,25,50.00),(36,26,100.00),(37,27,100.00),(38,28,100.00),(39,29,100.00),(40,30,100.00),(41,31,100.00),(42,32,100.00),(43,33,100.00),(44,34,100.00),(45,35,100.00),(47,36,50.00),(47,37,50.00),(48,38,100.00),(49,39,100.00),(50,40,100.00),(52,41,100.00),(53,42,100.00);
/*!40000 ALTER TABLE `ProductBean` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ProductTastingNote`
--

LOCK TABLES `ProductTastingNote` WRITE;
/*!40000 ALTER TABLE `ProductTastingNote` DISABLE KEYS */;
INSERT INTO `ProductTastingNote` VALUES (1,3),(1,11),(1,14),(1,18),(1,20),(1,24),(2,4),(2,12),(2,28),(22,8),(22,37),(22,38),(23,39),(23,40),(23,41),(23,42),(24,44),(24,45),(24,46),(25,18),(25,48),(25,49),(25,50),(26,16),(26,18),(26,51),(26,52),(27,53),(27,54),(27,55),(28,57),(28,58),(28,59),(29,61),(29,62),(29,63),(30,3),(30,19),(30,68),(30,69),(31,18),(31,70),(31,71),(32,18),(32,70),(32,76),(33,12),(33,74),(34,4),(35,16),(35,76),(35,77),(36,75),(36,76),(37,70),(37,78),(37,79),(38,81),(38,82),(38,83),(39,22),(39,86),(39,87),(40,79),(40,88),(40,89),(41,3),(41,19),(41,63),(42,41),(42,69),(42,70),(43,2),(43,28),(43,75),(44,24),(44,47),(44,88),(45,8),(45,24),(45,46),(46,9),(46,44),(46,55),(47,55),(47,69),(47,88),(48,33),(48,48),(48,74),(49,4),(49,52),(49,80),(50,13),(50,41),(50,70),(51,15),(51,25),(51,32),(52,34),(52,43),(52,54),(53,64),(53,69),(53,70);
/*!40000 ALTER TABLE `ProductTastingNote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Products`
--

LOCK TABLES `Products` WRITE;
/*!40000 ALTER TABLE `Products` DISABLE KEYS */;
INSERT INTO `Products` VALUES (1,1,'Flower Show',1,1,3.50),(2,1,'From Panama To PGH',4,0,7.93),(22,1,'Chami',3,0,8.00),(23,1,'Worka Sakaro',1,1,7.00),(24,6,'Aroma Nativo',1,1,13.00),(25,6,'Inmaculada',1,1,26.00),(26,6,'Gaia Farm',1,1,13.00),(27,7,'Tio Conejo',1,1,20.00),(28,7,'Aponte Village',2,0,9.00),(29,7,'Muthingini AA',1,0,9.00),(30,7,'Mullugeta',1,1,9.00),(31,8,'Vinka Sidra',1,1,25.00),(32,8,'Hachi Geisha #1',1,1,29.00),(33,8,'Geisha Lot 4',1,1,24.00),(34,9,'Philippines Set',1,1,26.40),(35,8,'Velvet Bliss',1,0,9.00),(36,8,'La Pelota',2,0,8.00),(37,10,'Arbegona',1,0,9.00),(38,10,'Wilton Benitez',1,1,9.00),(39,10,'Cordillera Del Fuego',1,0,9.00),(40,10,'Statement Series: Ethiopia',1,0,5.00),(41,11,'Benjamin Paz',1,1,14.00),(42,12,'Finca Deborah - Gesha',1,1,28.00),(43,13,'Rafael Vinhal',2,0,10.00),(44,5,'Benjamin Paz',1,1,16.00),(45,14,'Chelichele',1,0,11.50),(46,15,'La Palma',1,1,21.00),(47,16,'Kieni AA',1,0,12.00),(48,17,'Janson Gesha',1,1,24.00),(49,18,'Chelbesa',1,0,11.00),(50,19,'Nano Challa',1,0,10.00),(51,20,'El Vergel',1,1,15.00),(52,21,'Santa Cruz',1,1,13.00),(53,22,'Doi Saket',3,0,7.00);
/*!40000 ALTER TABLE `Products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Rating`
--

LOCK TABLES `Rating` WRITE;
/*!40000 ALTER TABLE `Rating` DISABLE KEYS */;
INSERT INTO `Rating` VALUES (1,'😖','Terrible'),(2,'😕','Not my taste'),(3,'🙂','Okay / Just fine'),(4,'😋','Pretty good'),(5,'🤩','Excellent / Loved it');
/*!40000 ALTER TABLE `Rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `RegionLocation`
--

LOCK TABLES `RegionLocation` WRITE;
/*!40000 ALTER TABLE `RegionLocation` DISABLE KEYS */;
INSERT INTO `RegionLocation` VALUES (9,'Finca Isabel'),(15,'Huila'),(16,'Gedeb'),(17,'Huila'),(18,'Mbeya'),(19,'Kirinyaga'),(20,'Loja'),(22,'Sitio San Roque'),(24,'Tarrazú'),(26,'Santa Barbara'),(29,'Cerrado Mineiro'),(38,'Chiang Mai');
/*!40000 ALTER TABLE `RegionLocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `RoastBatch`
--

LOCK TABLES `RoastBatch` WRITE;
/*!40000 ALTER TABLE `RoastBatch` DISABLE KEYS */;
INSERT INTO `RoastBatch` VALUES (2,1,'2025-04-05'),(1,1,'2025-04-08'),(3,2,'2025-03-21'),(26,22,'2025-02-10'),(27,23,'2025-02-10'),(28,24,'2025-01-10'),(29,25,'2025-01-05'),(30,26,'2025-01-07'),(31,27,'2025-02-20'),(32,28,'2024-12-22'),(33,29,'2024-12-23'),(34,30,'2024-12-23'),(35,31,'2024-10-30'),(36,32,'2024-10-15'),(37,33,'2024-11-01'),(38,34,'2025-04-13'),(39,35,'2024-10-30'),(40,36,'2024-10-31'),(41,37,'2024-11-30'),(42,38,'2023-12-02'),(43,39,'2023-12-01'),(44,40,'2024-01-01'),(45,41,'2025-04-01'),(46,42,'2025-03-18'),(47,43,'2025-03-10'),(48,44,'2024-12-18'),(49,45,'2025-03-28'),(50,46,'2025-03-30'),(51,47,'2025-03-20'),(52,48,'2025-03-10'),(53,49,'2025-03-26'),(54,50,'2025-04-01'),(55,51,'2025-03-25'),(56,52,'2025-03-15'),(57,53,'2025-04-02');
/*!40000 ALTER TABLE `RoastBatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `RoastLevel`
--

LOCK TABLES `RoastLevel` WRITE;
/*!40000 ALTER TABLE `RoastLevel` DISABLE KEYS */;
INSERT INTO `RoastLevel` VALUES (5,'Dark'),(6,'Extra Dark'),(1,'Light'),(2,'Light-Medium'),(3,'Medium'),(4,'Medium-Dark');
/*!40000 ALTER TABLE `RoastLevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Roasters`
--

LOCK TABLES `Roasters` WRITE;
/*!40000 ALTER TABLE `Roasters` DISABLE KEYS */;
INSERT INTO `Roasters` VALUES (1,'De Fer Coffee & Tea','Specialty coffee roaster and café in Pittsburgh.','https://defer.coffee',1),(5,'Red Rooster Coffee','An award-winning coffee roastery known for its ethical sourcing and small-batch roasting in the Blue Ridge Mountains.','https://redroostercoffee.com',5),(6,'Hatch Coffee','Toronto based specialty coffee roaster focused on transparency, traceability, and innovative roasting profiles.','https://hatchcarfted.com',6),(7,'Onyx Coffee Lab','Award winning specialty coffee roaster known for bold flavors and innovative sourcing.','https://onyxcoffeelab.com',7),(8,'Prodigal','A Colorado based coffee roaster focused on unique, expressive coffees with transparent sourcing and expert roasting.','https://getprodigal.com',8),(9,'Blue Bottle Coffee','Our Annual Favorite Is Back — Experience Two Coffees That Showcase the Range of Philippine Coffee','https://bluebottlecoffee.com/us/eng',21),(10,'Broadsheet','Broadsheet is a specialty coffee roaster based in Cambridge, MA, known for precise light roasts and educational transparency in sourcing.','https://broadsheetcoffee.com',23),(11,'SEY Coffee','SEY is a Brooklyn-based specialty coffee roaster, known for its ultra-light Nordic-style roast and a strict focus on terroir clarity and seasonality.','https://www.seycoffee.com',25),(12,'April Coffee Roasters','April is a Copenhagen-based specialty coffee roaster driven by innovation and transparency, well-known for competition-grade single origin coffees.','https://www.aprilcoffeeroasters.com',27),(13,'Proud Mary','Proud Mary is a Melbourne-based specialty roaster known for bold experimental coffees, direct farm relationships, and vibrant café culture.','https://www.proudmarycoffee.com.au',28),(14,'Bonanza Coffee','Bonanza is one of Berlin’s pioneers of specialty coffee, roasting light and clean to highlight terroir and processing nuances.\nWebsite: https://bonanzacoffee.de','https://bonanzacoffee.de',30),(15,'Onyx Coffee Lab','Onyx is an award-winning Arkansas-based roaster known for its precision in sourcing and a high bar for quality, presentation, and experimentation','https://onyxcoffeelab.com',31),(16,'Coffee Collective','Coffee Collective is a Copenhagen-based roaster and café known for ethical sourcing, clean Nordic-style light roasts, and farmer transparency.','https://coffeecollective.dk',32),(17,'Manhattan Coffee','Based in Rotterdam, Manhattan Coffee Roasters focuses on rare varieties and high competition-grade coffees with exceptional processing.','https://manhattancoffeeroasters.com',33),(18,'Fuglen Coffee','Fuglen Tokyo is part of the Norway-founded Fuglen brand, known for showcasing clean and expressive light roasts with Japanese brewing precision.','https://fuglencoffee.jp',34),(19,'Kurasu Kyoto','Kurasu is a Kyoto-based roastery and café specializing in clean, delicate light roasts brewed via traditional Japanese pour-over techniques.','https://kurasu.kyoto',35),(20,'Dak Coffee','Dak is an Amsterdam-based roaster with a reputation for expressive and fruit-forward light roasts, often featuring rare microlots.','https://www.dakcoffeeroasters.com',36),(21,'MABÓ Coffee','Based in Prague, MABÓ specializes in light Nordic-style roasts with focus on rare varietals and artistic presentation.','https://www.mabo.cz',37),(22,'Roots Coffee','Roots is a Bangkok-based roaster known for showcasing Thai coffee producers and building deep farm-level partnerships throughout Southeast Asia.','https://www.rootsbkk.com',38);
/*!40000 ALTER TABLE `Roasters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TastingLogNote`
--

LOCK TABLES `TastingLogNote` WRITE;
/*!40000 ALTER TABLE `TastingLogNote` DISABLE KEYS */;
INSERT INTO `TastingLogNote` VALUES (4,7),(4,11),(4,12),(5,7),(5,12),(7,1),(7,4),(7,7),(8,4),(8,11),(8,12),(9,4),(9,12),(9,28),(10,6),(10,18),(11,11),(11,18),(11,43),(12,10),(12,18),(12,47),(13,6),(13,10),(15,10),(15,47),(15,56),(16,60),(17,6),(17,60),(17,64),(18,56),(18,60),(19,11),(19,18),(21,34),(21,76),(22,10),(23,18),(24,64),(25,78),(25,80),(26,11),(27,60),(27,90),(28,34),(28,60),(28,64),(29,3),(29,19),(29,24),(30,31),(30,57),(30,80),(31,13),(31,40),(31,69),(32,48),(32,55),(32,69),(33,25),(33,32),(33,36),(34,3),(34,19),(34,63),(35,5),(35,17),(35,37),(36,2),(36,4),(36,79),(37,1),(37,44),(37,61),(38,11),(38,24),(38,46),(39,23),(39,57),(39,77),(40,27),(40,57),(40,84),(41,3),(41,19),(41,78),(42,5),(42,45),(42,71),(43,3),(43,18),(43,19),(44,7),(44,38),(44,61),(45,53),(45,79),(45,80),(46,27),(46,29),(46,47),(47,52),(47,79),(47,80);
/*!40000 ALTER TABLE `TastingLogNote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TastingLogs`
--

LOCK TABLES `TastingLogs` WRITE;
/*!40000 ALTER TABLE `TastingLogs` DISABLE KEYS */;
INSERT INTO `TastingLogs` VALUES (4,2,3,1,6,1,100,1,'testing','2025-04-12'),(5,2,3,5,5,1,12,1,'testing','2025-04-12'),(7,2,3,4,1,2,93,3,'Clean and bright taste with floral aroma','2025-04-12'),(8,2,3,5,5,1,20,1,'testing','2025-04-12'),(9,2,3,2,5,1,100,2,'huahua','2025-04-13'),(10,4,26,4,3,4,94,3,'Bright acidity with strong raspberry finish','2025-02-21'),(11,4,27,4,3,5,93,4,'Juicy and floral,very clean acidity with a subtle chocolatey aftertaste.','2025-02-18'),(12,4,28,4,3,4,94,3,'Super juicy acidity, tropical forward,crisp green tea finish.','2025-01-17'),(13,4,29,3,3,5,92,4,'Surprisingly grounded and mild for a Geisha.','2025-01-17'),(14,4,30,3,3,4,93,3,'Mellow and balanced but lacked standout character.','2025-01-16'),(15,4,31,4,3,4,93,3,'Clean body with candy like sweetness and a light guava finish.','2024-12-30'),(16,4,32,3,3,5,93,4,'Mellow and clean, delicate sweetness with papaya in aftertaste.','2024-12-30'),(17,4,33,3,3,4,94,3,'Bright acidity,juicy body with hints of roasted suger.','2024-12-30'),(18,4,34,3,3,5,92,4,'Delicate florals,silky mouthfeel, strawberry upfront with citrus tail.','2024-12-30'),(19,4,35,5,3,4,93,3,'Beautiful and intense floral and berry.','2024-11-04'),(20,4,36,4,3,4,92,3,'Great aroma, fermented funkiness','2024-10-21'),(21,4,37,4,3,4,93,3,'Crisp and clean, grapefruit, toasted','2024-11-11'),(22,4,38,2,9,2,100,1,'Testing','2025-04-13'),(23,4,39,3,3,4,94,4,'Vague floral flavor below plain toasty, under-roasted','2024-11-11'),(24,4,40,3,3,4,93,3,'Roasted cereal, with acidity… generic light roast','2024-11-11'),(25,4,41,4,3,4,93,3,'blueberry pie, hints of lavender','2023-12-04'),(26,4,42,3,3,5,92,3,'funky/bitter','2023-12-05'),(27,4,43,3,3,4,93,3,'generic light roast with some hints of orange, funk','2023-12-05'),(28,4,44,3,3,4,93,3,'generic toasty light roast','2024-01-07'),(29,3,38,4,5,1,50,2,'Testing','2025-01-08'),(30,4,3,4,7,3,100,1,'','2025-04-14'),(31,3,32,5,5,7,50,2,'Testing','2025-04-14'),(32,2,32,5,4,4,50,2,'','2025-04-14'),(33,2,28,5,9,6,80,1,'testing','2025-04-14'),(34,5,45,5,3,5,94,3,'Delicate floral aroma upfront with sweet peach and honey on finish.','2025-04-07'),(35,5,46,4,8,4,92,1,'Elegant and complex—rose aroma with white wine acidity and strawberry finish.','2025-03-25'),(36,5,47,4,4,2,95,1,'Thick, syrupy body with notes of dried apricot and creamy chocolate finish.','2025-03-18'),(37,5,48,5,1,7,10,1,'Delicate floral aroma upfront with sweet peach and honey on finish.','2025-01-23'),(38,5,30,4,7,1,89,3,'Elegant and complex—rose aroma with white wine acidity and strawberry finish.','2025-01-16'),(39,6,49,3,1,4,91,2,'Floral aroma, light body, and classic Yirgacheffe citrus-berry combo.','2025-04-02'),(40,6,48,5,2,5,70,2,'Velvety and fruit-forward with tropical acidity and creamy floral finish.','2025-04-06'),(41,7,51,4,3,3,94,3,'Bright acidity with syrupy mouthfeel and fresh florals.','2025-03-27'),(42,7,52,5,8,4,93,2,'Incredibly fragrant and juicy—long floral aftertaste and tropical sweetness.','2025-03-17'),(43,7,53,3,7,6,91,2,'Clean, structured acidity, soft stone fruit and floral hints.','2025-04-02'),(44,8,54,4,3,5,92,3,'Bright and citrus-forward, floral layers and clean tea finish.','2025-04-06'),(45,8,56,4,8,4,91,2,'Juicy and vibrant, blackberry-lime acidity with tea-like structure.','2025-03-20'),(46,9,57,3,1,4,94,2,'Balanced and smooth, light maple sweetness with mild citrus finish.','2025-04-08'),(47,10,37,5,6,1,97,1,'Balanced and smooth, light maple sweetness with mild citrus finish.','2025-04-06');
/*!40000 ALTER TABLE `TastingLogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TastingNote`
--

LOCK TABLES `TastingNote` WRITE;
/*!40000 ALTER TABLE `TastingNote` DISABLE KEYS */;
INSERT INTO `TastingNote` VALUES (7,'Almond'),(12,'Apple'),(86,'Apple Pie'),(75,'Apricot'),(11,'Berry'),(52,'Black Tea'),(79,'Blueberry'),(80,'BlueBerry Pie'),(4,'Brown Sugar'),(2,'Caramel'),(88,'Cherry'),(28,'Chocolate'),(22,'Cinnamon'),(10,'Citrus'),(23,'Clove'),(30,'Cocoa'),(84,'Costa Rica'),(77,'Cranberry'),(29,'Dark Chocolate'),(57,'Dried Cheery'),(31,'Earthy'),(27,'Fermented'),(18,'Floral'),(47,'Fruity'),(60,'Genric'),(87,'Golden Raisin'),(16,'Grape'),(76,'Grapefruit'),(46,'Green Tea'),(8,'Hazelnut'),(24,'Herbal'),(3,'Honey'),(19,'Jasmine'),(78,'Lavender'),(74,'Lemongrass'),(82,'Lychee'),(71,'Mandarin'),(45,'Mango'),(5,'Maple'),(17,'Melon'),(37,'Milk Chocolate'),(42,'Mixed Berries'),(81,'Muscat Grape'),(51,'Nougat'),(40,'Nutella'),(6,'Nutty'),(90,'Orange'),(83,'Orange Blossom'),(59,'Papaya'),(44,'Passion Fruit'),(63,'Peach'),(9,'Peanut'),(13,'Pear'),(48,'Pineapple'),(55,'Pink Guava'),(69,'Pink Lemonade'),(41,'Plum'),(70,'Raspberry'),(39,'Raspberry Tea'),(38,'Raspberry Yogurt'),(58,'Raw Honey'),(62,'Raw Sugar'),(64,'Roasty'),(20,'Rose'),(26,'Rum'),(35,'Savory'),(33,'Smoky'),(56,'Smooth'),(21,'Spicy'),(14,'Stone Fruit'),(68,'Strawberry'),(49,'Strawberry Jam'),(1,'Sweet'),(43,'Tea'),(34,'Toasted'),(89,'Toffee'),(15,'Tropical Fruit'),(36,'Umami'),(61,'Valencia Orange'),(54,'Vanilla'),(53,'Watermelon'),(50,'White Wine'),(25,'Winey'),(32,'Woody');
/*!40000 ALTER TABLE `TastingNote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `UserBeanStatus`
--

LOCK TABLES `UserBeanStatus` WRITE;
/*!40000 ALTER TABLE `UserBeanStatus` DISABLE KEYS */;
INSERT INTO `UserBeanStatus` VALUES (2,1,0,1,'2025-04-14 11:39:41'),(2,2,0,1,'2025-04-11 18:35:35'),(2,3,0,1,'2025-04-11 18:35:35'),(2,4,0,1,'2025-04-11 18:35:35'),(2,8,0,1,'2025-04-13 01:54:34'),(2,9,0,1,'2025-04-13 01:54:34'),(2,12,0,1,'2025-04-14 11:45:12'),(2,16,0,1,'2025-04-14 03:52:20'),(2,17,0,1,'2025-04-14 03:52:20'),(3,16,0,1,'2025-04-14 03:15:54'),(3,17,0,1,'2025-04-14 03:15:54'),(3,23,0,1,'2025-04-13 23:27:47'),(4,1,0,1,'2025-04-14 01:52:03'),(4,2,0,1,'2025-04-14 01:52:03'),(4,3,0,1,'2025-04-14 01:52:03'),(4,4,0,1,'2025-04-14 01:52:03'),(4,8,0,1,'2025-04-14 01:52:03'),(4,9,0,1,'2025-04-14 01:52:03'),(4,10,0,1,'2025-04-13 11:36:29'),(4,11,0,1,'2025-04-13 12:16:58'),(4,12,0,1,'2025-04-13 12:24:42'),(4,13,0,1,'2025-04-13 12:27:43'),(4,14,0,1,'2025-04-13 12:29:54'),(4,15,0,1,'2025-04-13 12:39:47'),(4,16,0,1,'2025-04-13 12:43:08'),(4,17,0,1,'2025-04-13 12:43:08'),(4,18,0,1,'2025-04-13 12:45:31'),(4,19,0,1,'2025-04-13 12:49:24'),(4,20,0,1,'2025-04-13 12:58:28'),(4,21,0,1,'2025-04-13 13:00:58'),(4,22,0,1,'2025-04-13 13:05:15'),(4,23,0,1,'2025-04-13 14:10:26'),(4,24,0,1,'2025-04-13 14:44:50'),(4,25,0,1,'2025-04-13 14:44:50'),(4,26,0,1,'2025-04-13 14:46:44'),(4,27,0,1,'2025-04-13 14:49:51'),(4,28,0,1,'2025-04-13 14:53:03'),(4,29,0,1,'2025-04-13 14:55:14'),(4,30,0,1,'2025-04-13 14:57:42'),(5,14,0,1,'2025-04-14 23:03:32'),(5,31,0,1,'2025-04-14 22:54:27'),(5,32,0,1,'2025-04-14 22:59:04'),(5,33,0,1,'2025-04-14 23:01:12'),(5,34,0,1,'2025-04-14 23:02:47'),(6,34,0,1,'2025-04-14 23:14:14'),(6,35,0,1,'2025-04-14 23:10:09'),(7,36,0,1,'2025-04-14 23:20:33'),(7,37,0,1,'2025-04-14 23:20:33'),(7,38,0,1,'2025-04-14 23:22:43'),(7,39,0,1,'2025-04-14 23:26:04'),(8,40,0,1,'2025-04-14 23:33:24'),(8,41,0,1,'2025-04-14 23:38:08'),(9,42,0,1,'2025-04-14 23:51:28'),(10,22,0,1,'2025-04-14 23:53:32');
/*!40000 ALTER TABLE `UserBeanStatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `UserProductStatus`
--

LOCK TABLES `UserProductStatus` WRITE;
/*!40000 ALTER TABLE `UserProductStatus` DISABLE KEYS */;
INSERT INTO `UserProductStatus` VALUES (2,1,0,1,'2025-04-10 23:48:32'),(2,2,0,1,'2025-04-14 11:39:20'),(2,24,0,1,'2025-04-14 11:45:12'),(2,28,0,1,'2025-04-14 03:52:20'),(3,28,0,1,'2025-04-14 03:15:54'),(3,34,0,1,'2025-04-13 23:27:47'),(4,2,0,1,'2025-04-14 01:52:03'),(4,22,0,1,'2025-04-13 11:35:46'),(4,23,0,1,'2025-04-13 12:16:58'),(4,24,0,1,'2025-04-13 12:24:42'),(4,25,0,1,'2025-04-13 12:27:43'),(4,26,0,1,'2025-04-13 12:29:54'),(4,27,0,1,'2025-04-13 12:39:47'),(4,28,0,1,'2025-04-13 12:43:08'),(4,29,0,1,'2025-04-13 12:45:31'),(4,30,0,1,'2025-04-13 12:49:24'),(4,31,0,1,'2025-04-13 12:58:28'),(4,32,0,1,'2025-04-13 13:00:58'),(4,33,0,1,'2025-04-13 13:05:15'),(4,34,0,1,'2025-04-13 14:10:26'),(4,35,0,1,'2025-04-13 14:44:50'),(4,36,0,1,'2025-04-13 14:46:44'),(4,37,0,1,'2025-04-13 14:49:51'),(4,38,0,1,'2025-04-13 14:53:03'),(4,39,0,1,'2025-04-13 14:55:14'),(4,40,0,1,'2025-04-13 14:57:42'),(5,26,0,1,'2025-04-14 23:03:32'),(5,41,0,1,'2025-04-14 22:54:27'),(5,42,0,1,'2025-04-14 22:59:04'),(5,43,0,1,'2025-04-14 23:01:12'),(5,44,0,1,'2025-04-14 23:02:47'),(6,44,0,1,'2025-04-14 23:14:14'),(6,45,0,1,'2025-04-14 23:10:09'),(6,46,0,1,'2025-04-14 23:12:02'),(7,47,0,1,'2025-04-14 23:20:33'),(7,48,0,1,'2025-04-14 23:22:43'),(7,49,0,1,'2025-04-14 23:26:04'),(8,50,0,1,'2025-04-14 23:33:24'),(8,51,0,1,'2025-04-14 23:34:23'),(8,52,0,1,'2025-04-14 23:38:08'),(9,53,0,1,'2025-04-14 23:51:28'),(10,33,0,1,'2025-04-14 23:53:32');
/*!40000 ALTER TABLE `UserProductStatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `UserRoasterStatus`
--

LOCK TABLES `UserRoasterStatus` WRITE;
/*!40000 ALTER TABLE `UserRoasterStatus` DISABLE KEYS */;
INSERT INTO `UserRoasterStatus` VALUES (2,1,0,1,'2025-04-14 11:44:35'),(2,5,0,1,'2025-04-10 19:26:01'),(2,6,0,1,'2025-04-14 11:45:12'),(2,7,0,1,'2025-04-14 03:52:20'),(2,8,0,1,'2025-04-10 20:29:58'),(3,7,0,1,'2025-04-14 03:15:54'),(3,9,0,1,'2025-04-13 23:27:47'),(4,1,0,1,'2025-04-14 01:52:03'),(4,6,0,1,'2025-04-13 12:29:54'),(4,7,0,1,'2025-04-13 12:49:24'),(4,8,0,1,'2025-04-13 14:46:44'),(4,9,0,1,'2025-04-13 14:10:26'),(4,10,0,1,'2025-04-13 14:57:42'),(5,5,0,1,'2025-04-14 23:02:47'),(5,6,0,1,'2025-04-14 23:03:32'),(5,11,0,1,'2025-04-14 22:54:27'),(5,12,0,1,'2025-04-14 22:59:04'),(5,13,0,1,'2025-04-14 23:01:12'),(6,5,0,1,'2025-04-14 23:14:14'),(6,14,0,1,'2025-04-14 23:10:09'),(6,15,0,1,'2025-04-14 23:11:19'),(7,16,0,1,'2025-04-14 23:20:33'),(7,17,0,1,'2025-04-14 23:22:43'),(7,18,0,1,'2025-04-14 23:26:04'),(8,19,0,1,'2025-04-14 23:33:24'),(8,20,0,1,'2025-04-14 23:33:56'),(8,21,0,1,'2025-04-14 23:38:08'),(9,22,0,1,'2025-04-14 23:51:28'),(10,8,0,1,'2025-04-14 23:53:32');
/*!40000 ALTER TABLE `UserRoasterStatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'erdun_test_123@example.com','$2a$10$GBNf1XxpI8qCd1Bh3PRGGeCewhQjDy2xoKbDfPFkG6tskTB5M8Ywe','Erdun Tester','1234567890','2000-01-01','User1',NULL,'2025-04-06 22:36:15',0,0,'en'),(2,'eed950128@gmail.com','$2a$10$DQkW4SoRpc44V2DRKfMZWui57ky2Btr9NT8.FZEvDPT02Fvut2aLm','Erdun E','9789544270','1995-01-28','Erdun','http://10.0.2.2:8080/uploads/user_2_1744321963099.jpg','2025-04-07 00:19:11',0,0,'en'),(3,'erdunwork@gmail.com','$2a$10$XibU1iU0avIX6CMi0CLW3eQy2wwW4tzexH2uU0R5IAzJ0thYAqAEK','ErdunTest','9789544270','1995-01-28','User3',NULL,'2025-04-10 19:28:10',0,0,'en'),(4,'n.derbinsky@northeastern.edu','$2a$10$.XP5SkGTKDhchGhcPWiV7OSr1brSCIlzl314jeyE.b2p3T1u5aa8K','Nate Derbinsky','0000000000','2000-01-01','Professor Nate',NULL,'2025-04-13 15:22:03',0,0,'en'),(5,'liu.fengka@northeastern.edu','$2a$10$8yLKmHY6Sq83uage8Bj6sef1Y2K19L7ypcbYzQ1rcZBpRaDk.qU.2','Fengkai Liu','2222222222','2000-01-01','Kai',NULL,'2025-04-14 15:21:05',0,0,'en'),(6,'wan.qing@northeastern.edu','$2a$10$BgPe/VCfvcO18hpLkFnNIOXBUUzWWDvWt6TWH8a0o2gmDdSlkOnm2','Qingyuan Wan','3333333333','2000-01-01','User6',NULL,'2025-04-15 03:06:52',0,0,'en'),(7,'g.gongorasvartzman@northeastern.edu','$2a$10$jvUE6U2TyF73WLy0MuRbOuhH1kJdk.PSnM0GW8QhPEM7aK2GpLt.K','Gongora Svartzman, Gabriela','4444444444','2000-01-01','User7',NULL,'2025-04-15 03:17:42',0,0,'en'),(8,'kumanan.n@northeastern.edu','$2a$10$AqX8Q1DBtJZVMXKB3hzHiuRv0Bgp9oD11SqjGT43RGiXRHhCCt.x.','Naveen Kumanan','5555555555','2000-01-01','User8',NULL,'2025-04-15 03:30:08',0,0,'en'),(9,'leyanhua96@gmail.com','$2a$10$wDhLIYpB2F9LhoWcYLaSqOT./p/5UjtAOxbIe2o60GITV47CoGsDq','Leyan Hua','6666666666','2000-01-01','User9',NULL,'2025-04-15 03:49:16',0,0,'en'),(10,'hiahiahia2333@gmail.com','$2a$10$queunHidg2PW2s79WpQPqeKszwZLW6JcqTmMwOVSM0R9Ozn97wWm6','Leyan Hua','7777777777','2000-01-01','User10',NULL,'2025-04-15 03:52:46',0,0,'en');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Varietal`
--

LOCK TABLES `Varietal` WRITE;
/*!40000 ALTER TABLE `Varietal` DISABLE KEYS */;
INSERT INTO `Varietal` VALUES (1,'Abyssinia'),(2,'Acaia'),(3,'Agaro'),(4,'Batian'),(5,'Blue Mountain'),(6,'Bourbon'),(7,'Castillo'),(8,'Catimor'),(9,'Catuai'),(10,'Caturra'),(11,'Colombia'),(12,'Geisha'),(13,'Heirloom'),(14,'Icatu'),(15,'Java'),(16,'Landrace'),(17,'Mundo Novo'),(18,'Obatá'),(19,'Pacamara'),(20,'Pacas'),(21,'Parainema'),(22,'Ruiru 11'),(23,'Sarchimor'),(24,'Sidra'),(25,'SL14'),(26,'SL28'),(27,'SL34'),(28,'Típica'),(29,'Típica Mejorado'),(30,'Villa Sarchi');
/*!40000 ALTER TABLE `Varietal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-15  0:11:11
