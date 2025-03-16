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
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `Beans`
--

LOCK TABLES `Beans` WRITE;
/*!40000 ALTER TABLE `Beans` DISABLE KEYS */;
INSERT INTO `Beans` VALUES (1,1,'Geisha','Experimental'),(2,2,'Bourbon','Washed'),(3,2,'Pacamara','Washed'),(4,3,'Heirloom','Natural'),(5,4,'Caturra','Washed'),(6,5,'Heirloom','Natural'),(7,6,'Pacas','Washed'),(8,7,'Heirloom','Washed'),(9,8,'Catuai','Washed'),(10,9,'Heirloom','Natural'),(11,10,'Pacamara','Washed'),(12,11,'Gesha','Natural'),(13,12,'Caturra','Washed'),(14,13,'Yellow Bourbon','Pulped Natural'),(15,14,'Geisha','Natural'),(16,15,'Geisha','Natural'),(17,16,'Bourbon','Washed'),(18,21,'Maragogipe','Natural'),(19,17,'Catuai','Natural'),(20,23,'Caturra','Washed'),(21,18,'Heirloom','Washed'),(22,22,'Heirloom','Natural'),(23,19,'Geisha','Natural'),(24,25,'Heirloom','Washed'),(25,20,'Bourbon','Washed'),(26,24,'Yellow Bourbon','Pulped Natural');
/*!40000 ALTER TABLE `Beans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Farms`
--

LOCK TABLES `Farms` WRITE;
/*!40000 ALTER TABLE `Farms` DISABLE KEYS */;
INSERT INTO `Farms` VALUES (1,'Ninety Plus Estate',22,1500.00),(2,'Finca El Injerto',23,1800.00),(3,'Ethiopia Yirgacheffe Farms',24,2000.00),(4,'Los Delirios',29,1300.00),(5,'Buku Sayisa',30,2000.00),(6,'La Tortuga',31,1550.00),(7,'Suke Quto',30,1800.00),(8,'Finca El Puente',32,1600.00),(9,'Halo Hartume',33,1900.00),(10,'Los Pirineos',34,1400.00),(11,'La Palma y El Tucán',35,1650.00),(12,'Finca Tamana',36,1700.00),(13,'Daterra',37,1200.00),(14,'Finca Santa Teresa',38,1850.00),(15,'Finca Deborah',38,1900.00),(16,'Finca La Soledad',58,1600.00),(17,'Finca Hartmann',38,1400.00),(18,'Halo Beriti',24,2000.00),(19,'Elida Estate',38,1900.00),(20,'Finca Nueva Armenia',23,1800.00),(21,'La Bendicion',59,1350.00),(22,'Worka Sakaro',33,1950.00),(23,'La Palma',60,1750.00),(24,'Fazenda Santa Inês',37,1200.00),(25,'Hambela',30,1900.00);
/*!40000 ALTER TABLE `Farms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Locations`
--

LOCK TABLES `Locations` WRITE;
/*!40000 ALTER TABLE `Locations` DISABLE KEYS */;
INSERT INTO `Locations` VALUES (58,NULL,'Acatenango','Guatemala'),(22,NULL,'Boquete','Panama'),(38,NULL,'Chiriquí','Panama'),(35,NULL,'Cundinamarca','Colombia'),(33,NULL,'Gedeb','Ethiopia'),(30,NULL,'Guji','Ethiopia'),(23,NULL,'Huehuetenango','Guatemala'),(36,NULL,'Huila','Colombia'),(29,NULL,'Jinotega','Nicaragua'),(32,NULL,'La Paz','Honduras'),(37,NULL,'Minas Gerais','Brazil'),(59,NULL,'Nueva Segovia','Nicaragua'),(31,NULL,'Santa Bárbara','Honduras'),(26,NULL,'Sidamo','Ethiopia'),(60,NULL,'Tolima','Colombia'),(34,NULL,'Usulután','El Salvador'),(24,NULL,'Yirgacheffe','Ethiopia'),(12,'Aarhus',NULL,'Denmark'),(6,'Berlin',NULL,'Germany'),(18,'Brooklyn',NULL,'United States'),(3,'Chicago',NULL,'United States'),(8,'Copenhagen',NULL,'Denmark'),(4,'Durham',NULL,'United States'),(9,'Fayetteville',NULL,'United States'),(16,'Forlì-Cesena',NULL,'Italy'),(15,'Helsingborg',NULL,'Sweden'),(5,'London',NULL,'United Kingdom'),(14,'Melbourne',NULL,'Australia'),(1,'Oakland',NULL,'United States'),(7,'Oslo',NULL,'Norway'),(2,'Portland',NULL,'United States'),(13,'San Francisco',NULL,'United States'),(10,'Santa Cruz',NULL,'United States'),(17,'Stafford',NULL,'United Kingdom'),(11,'Vancouver',NULL,'Canada');
/*!40000 ALTER TABLE `Locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Products`
--

LOCK TABLES `Products` WRITE;
/*!40000 ALTER TABLE `Products` DISABLE KEYS */;
INSERT INTO `Products` VALUES (1,1,'Giant Steps','2024-03-01','Dark',9.50),(2,1,'Bella Donovan','2024-03-02','Medium',10.00),(3,1,'Three Africas','2024-03-03','Medium-Dark',11.50),(4,1,'Hayes Valley Espresso','2024-03-04','Dark',12.00),(5,1,'Kenya Nyeri Peaberry','2024-03-05','Medium',13.00),(6,2,'Hair Bender','2024-03-01','Medium-Dark',9.00),(7,2,'Holler Mountain','2024-03-02','Medium',10.50),(8,2,'French Roast','2024-03-03','Dark',11.00),(9,3,'Black Cat Classic Espresso','2024-03-01','Medium',11.50),(10,3,'House Blend','2024-03-02','Medium',10.00),(11,3,'Frequency Blend','2024-03-03','Light',12.00),(12,4,'Hologram','2024-03-01','Medium',10.50),(13,4,'Big Trouble','2024-03-02','Medium-Dark',11.00),(14,4,'Apollo','2024-03-03','Light',12.50),(15,5,'Red Brick Espresso','2024-03-01','Medium',12.00),(16,5,'Sweetshop','2024-03-02','Light',13.00),(17,5,'Filter Blend','2024-03-03','Medium-Dark',10.00),(18,6,'Ethiopia Nano Challa','2024-03-01','Light',14.00),(19,6,'Kenya Gakuyuini','2024-03-02','Medium',15.00),(20,6,'Colombia El Paraiso','2024-03-03','Medium-Dark',13.50),(21,7,'Finca Tamana','2024-03-01','Medium',15.50),(22,7,'Caballero','2024-03-02','Light',16.00),(23,7,'Kii AA','2024-03-03','Medium-Dark',14.50),(24,8,'Kieni','2024-03-01','Medium',14.50),(25,8,'Daterra Sweet Collection','2024-03-02','Light',15.50),(26,8,'Frinsa Estate','2024-03-03','Medium-Dark',13.00),(27,9,'Southern Weather','2024-03-01','Medium',13.00),(28,9,'Monarch','2024-03-02','Dark',12.50),(29,9,'Geometry','2024-03-03','Light',14.00),(30,10,'The 1950 Blend','2024-03-01','Medium',14.00),(31,10,'Streetlevel Espresso','2024-03-02','Medium-Dark',13.50),(32,10,'Seabright House Blend','2024-03-03','Light',15.00),(33,11,'Epic Espresso','2024-03-01','Medium',13.00),(34,11,'Old School Espresso','2024-03-02','Dark',14.00),(35,11,'Blue Sky Blend','2024-03-03','Medium-Dark',12.50),(36,12,'Ethiopia Kochere','2024-03-01','Light',16.50),(37,12,'Kenya Gakundu','2024-03-02','Medium',15.00),(38,12,'Colombia Los Monjes','2024-03-03','Medium-Dark',14.00),(39,13,'Guatemala San Juan','2024-03-01','Medium',15.50),(40,13,'Ethiopia Aricha','2024-03-02','Light',17.00),(41,13,'Kenya Thiriku AA','2024-03-03','Medium-Dark',14.50),(42,14,'Stereo','2024-03-01','Medium',13.50),(43,14,'Colombia Tamana','2024-03-02','Light',15.00),(44,14,'Ethiopia Reko','2024-03-03','Medium-Dark',14.00),(45,15,'Sweet Tooth','2024-03-01','Medium',14.00),(46,15,'Los Gigantes','2024-03-02','Light',15.50),(47,15,'Elida Estate','2024-03-03','Medium-Dark',14.00),(48,16,'Kenya Kiambu','2024-03-01','Medium',14.50),(49,16,'Colombia Inza','2024-03-02','Light',16.00),(50,16,'Ethiopia Banko Gotiti','2024-03-03','Medium-Dark',14.50),(51,17,'Hunkute','2024-03-01','Medium',13.50),(52,17,'Kenya Kagumoini','2024-03-02','Light',15.50),(53,17,'Colombia El Jordan','2024-03-03','Medium-Dark',14.00),(54,18,'Ethiopia Nansebo','2024-03-01','Medium',15.00),(55,18,'Kenya Ichuga','2024-03-02','Light',16.50),(56,18,'Colombia La Palma','2024-03-03','Medium-Dark',14.50),(57,19,'Sweetshop','2024-03-01','Medium',13.50),(58,19,'Kenya Tegu','2024-03-02','Light',15.00),(59,19,'Ethiopia Biftu Gudina','2024-03-03','Medium-Dark',14.50),(60,20,'Colombia Los Naranjos','2024-03-01','Medium',14.00),(61,20,'Ethiopia Suke Quto','2024-03-02','Light',16.50),(62,20,'Kenya Kirinyaga','2024-03-03','Medium-Dark',15.00);
/*!40000 ALTER TABLE `Products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Products_Beans`
--

LOCK TABLES `Products_Beans` WRITE;
/*!40000 ALTER TABLE `Products_Beans` DISABLE KEYS */;
INSERT INTO `Products_Beans` VALUES (1,10,31.50,'Blend'),(1,15,67.72,'Primary'),(1,19,0.79,'Blend'),(2,1,50.00,'Primary'),(2,22,50.00,'Blend'),(3,4,50.00,'Primary'),(3,7,50.00,'Blend'),(4,6,100.00,'Primary'),(5,8,100.00,'Primary'),(6,14,100.00,'Primary'),(7,5,60.00,'Primary'),(7,9,40.00,'Blend'),(8,2,70.00,'Primary'),(8,3,30.00,'Blend'),(9,4,100.00,'Primary'),(10,12,100.00,'Primary'),(11,3,60.00,'Primary'),(11,6,40.00,'Blend'),(12,13,100.00,'Primary'),(13,16,70.00,'Primary'),(13,17,30.00,'Blend'),(14,11,40.00,'Blend'),(14,18,60.00,'Primary'),(15,5,100.00,'Primary'),(16,2,50.00,'Blend'),(16,7,50.00,'Primary'),(17,19,100.00,'Primary'),(18,10,100.00,'Primary'),(19,1,70.00,'Primary'),(19,8,30.00,'Blend'),(20,4,100.00,'Primary'),(21,22,100.00,'Primary'),(22,12,50.00,'Primary'),(22,20,50.00,'Blend'),(23,3,30.00,'Blend'),(23,25,70.00,'Primary'),(24,24,100.00,'Primary'),(25,14,60.00,'Primary'),(25,16,40.00,'Blend'),(26,21,100.00,'Primary'),(27,2,20.00,'Blend'),(27,6,80.00,'Primary'),(28,9,100.00,'Primary'),(29,15,60.00,'Blend'),(29,17,40.00,'Primary'),(30,13,100.00,'Primary'),(31,10,50.00,'Primary'),(31,19,50.00,'Blend'),(32,5,100.00,'Primary'),(33,12,40.00,'Blend'),(33,18,60.00,'Primary'),(34,7,70.00,'Primary'),(34,8,30.00,'Blend'),(35,15,100.00,'Primary'),(36,11,40.00,'Blend'),(36,16,60.00,'Primary'),(37,22,100.00,'Primary'),(38,13,30.00,'Blend'),(38,19,70.00,'Primary'),(39,4,60.00,'Primary'),(39,14,40.00,'Blend'),(40,18,100.00,'Primary'),(41,8,70.00,'Primary'),(41,24,30.00,'Blend'),(42,10,50.00,'Primary'),(42,19,50.00,'Blend'),(43,16,100.00,'Primary'),(44,3,20.00,'Blend'),(44,12,80.00,'Primary'),(45,5,50.00,'Primary'),(45,13,50.00,'Blend'),(46,6,70.00,'Primary'),(46,21,30.00,'Blend'),(47,18,100.00,'Primary'),(48,7,50.00,'Primary'),(48,16,50.00,'Blend'),(49,9,100.00,'Primary'),(50,14,70.00,'Primary'),(50,17,30.00,'Blend'),(51,15,40.00,'Blend'),(51,20,60.00,'Primary'),(52,11,50.00,'Primary'),(52,12,50.00,'Blend'),(53,22,100.00,'Primary'),(54,3,80.00,'Primary'),(54,6,20.00,'Blend'),(55,4,60.00,'Primary'),(55,13,40.00,'Blend'),(56,16,100.00,'Primary'),(57,5,70.00,'Primary'),(57,9,30.00,'Blend'),(58,7,60.00,'Primary'),(58,10,40.00,'Blend'),(59,19,100.00,'Primary'),(60,8,50.00,'Primary'),(60,14,50.00,'Blend'),(61,18,70.00,'Primary'),(61,22,30.00,'Blend'),(62,15,100.00,'Primary');
/*!40000 ALTER TABLE `Products_Beans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Products_TastingNotes`
--

LOCK TABLES `Products_TastingNotes` WRITE;
/*!40000 ALTER TABLE `Products_TastingNotes` DISABLE KEYS */;
INSERT INTO `Products_TastingNotes` VALUES (1,9),(1,12),(1,18),(1,23),(1,45),(2,1),(2,7),(2,10),(2,18),(2,26),(3,7),(3,16),(3,25),(3,42),(4,14),(4,16),(4,36),(4,38),(4,40),(5,34),(5,39),(5,40),(5,46),(6,1),(6,7),(6,22),(6,31),(6,38),(7,13),(7,32),(7,49),(8,22),(8,25),(8,45),(9,3),(9,14),(10,4),(10,15),(10,24),(10,39),(10,42),(11,8),(11,11),(11,25),(11,28),(11,35),(12,6),(12,21),(12,33),(12,37),(13,9),(13,17),(13,30),(13,35),(14,5),(14,19),(14,23),(14,29),(15,8),(15,16),(15,24),(15,33),(16,18),(16,22),(16,27),(16,41),(17,12),(17,20),(17,34),(17,39),(18,3),(18,5),(18,29),(18,41),(19,14),(19,24),(19,40),(19,44),(20,8),(20,16),(20,26),(20,47),(21,11),(21,15),(21,27),(21,32),(22,13),(22,19),(22,28),(22,38),(23,6),(23,10),(23,17),(23,30),(24,6),(24,19),(24,24),(24,28),(25,3),(25,4),(25,18),(25,35),(26,11),(26,22),(26,33),(26,40),(27,5),(27,24),(27,27),(27,39),(28,8),(28,9),(28,15),(28,44),(29,21),(29,23),(29,29),(29,46),(30,1),(30,6),(30,38),(30,48),(31,2),(31,25),(31,30),(31,34),(32,9),(32,14),(32,17),(32,19),(33,20),(33,22),(33,29),(33,39),(34,15),(34,18),(34,32),(34,42),(35,9),(35,21),(35,37),(35,44),(36,7),(36,11),(36,26),(36,31),(37,5),(37,10),(37,17),(37,23),(38,3),(38,21),(38,27),(38,42),(39,9),(39,16),(39,22),(39,40),(40,3),(40,12),(40,30),(40,45),(41,4),(41,25),(41,29),(41,33),(42,7),(42,19),(42,24),(42,33),(43,12),(43,17),(43,31),(43,35),(44,23),(44,28),(44,32),(44,39),(45,7),(45,9),(45,26),(45,34),(46,8),(46,15),(46,29),(46,40),(47,11),(47,14),(47,37),(47,38),(48,13),(48,27),(48,32),(48,39),(49,4),(49,7),(49,25),(49,41),(50,8),(50,10),(50,28),(50,30),(51,2),(51,11),(51,33),(51,46),(52,16),(52,19),(52,31),(52,45),(53,5),(53,8),(53,34),(53,42),(54,1),(54,5),(54,29),(54,38),(55,9),(55,12),(55,19),(55,35),(56,4),(56,16),(56,30),(56,42),(57,5),(57,15),(57,21),(57,40),(58,14),(58,16),(58,33),(58,42),(59,6),(59,7),(59,25),(59,41),(60,5),(60,20),(60,33),(60,35),(61,7),(61,13),(61,26),(61,38),(62,1),(62,8),(62,22),(62,33);
/*!40000 ALTER TABLE `Products_TastingNotes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Ratings`
--

LOCK TABLES `Ratings` WRITE;
/*!40000 ALTER TABLE `Ratings` DISABLE KEYS */;
INSERT INTO `Ratings` VALUES (1,'🥺','Gross, no; watery, flavorless (Keurig)'),(2,'🫤','Drink if you forced me; some flavor, but not good (Dunkin, diner)'),(3,'😐','It’s coffee; uninteresting flavor (Starbucks, Pavement)'),(4,'😁','Would drink anytime, delicious'),(5,'🤤','Ambrosia');
/*!40000 ALTER TABLE `Ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Roasters`
--

LOCK TABLES `Roasters` WRITE;
/*!40000 ALTER TABLE `Roasters` DISABLE KEYS */;
INSERT INTO `Roasters` VALUES (1,1,'Blue Bottle Coffee','https://bluebottlecoffee.com/us/eng',0),(2,2,'Stumptown Coffee Roasters','https://www.stumptowncoffee.com/',0),(3,3,'Intelligentsia Coffee','https://www.intelligentsiacoffee.com/',0),(4,4,'Counter Culture Coffee','https://www.counterculturecoffee.com/',0),(5,5,'Square Mile Coffee Roasters','https://www.squaremilecoffee.com/',0),(6,6,'The Barn','https://www.thebarn.de/',0),(7,7,'Tim Wendelboe','https://www.timwendelboe.no/',0),(8,8,'Coffee Collective','https://www.coffeecollective.dk/',0),(9,9,'Onyx Coffee Lab','https://www.onyxcoffeelab.com/',0),(10,10,'Verve Coffee Roasters','https://www.vervecoffee.com/',0),(11,11,'49th Parallel Coffee Roasters','https://www.49thcoffee.com/',0),(12,6,'Five Elephant','https://www.fiveelephant.com/',0),(13,12,'La Cabra','https://www.lacabra.dk/',0),(14,2,'Heart Coffee Roasters','https://www.heartroasters.com/',0),(15,13,'Ritual Coffee Roasters','https://www.ritualcoffee.com/',0),(16,14,'Proud Mary','https://www.proudmarycoffee.com.au/',0),(17,15,'Koppi','https://www.koppi.se/',0),(18,16,'Gardelli Coffee','https://www.gardellicoffee.com/',0),(19,17,'Has Bean Coffee','https://www.hasbean.co.uk/',0),(20,18,'Sey Coffee','https://www.seycoffee.com/',0);
/*!40000 ALTER TABLE `Roasters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TastingLogs`
--

LOCK TABLES `TastingLogs` WRITE;
/*!40000 ALTER TABLE `TastingLogs` DISABLE KEYS */;
INSERT INTO `TastingLogs` VALUES (1,1,1,'2024-03-02',1,'Pour Over','Medium',93,2,'Strong and bitter but has a rich flavor.'),(2,2,2,'2024-03-03',4,'Espresso','Fine',90,1,'Smooth with a sweet aftertaste, perfect espresso.'),(3,3,3,'2024-03-04',2,'French Press','Coarse',92,3,'A bit too watery but has a nice fruity flavor.'),(4,4,4,'2024-03-05',5,'Aeropress','Medium-Fine',95,2,'Delicious, I could drink this all day long.'),(5,5,5,'2024-03-06',3,'Cold Brew','Coarse',80,1,'Smooth, but lacks complexity, very drinkable.'),(6,1,6,'2024-03-07',2,'Pour Over','Medium',92,2,'A bit acidic, but smooth with fruity notes.'),(7,1,7,'2024-03-08',4,'Espresso','Fine',94,1,'Rich and smooth, with hints of chocolate and caramel.'),(8,1,8,'2024-03-09',3,'French Press','Coarse',91,3,'Nice body, but a bit flat on the finish.'),(9,1,9,'2024-03-10',5,'Aeropress','Medium-Fine',95,2,'Bold flavor, slightly bitter but enjoyable.'),(10,1,10,'2024-03-11',1,'Cold Brew','Coarse',85,1,'Smooth, with chocolate and nutty flavors.');
/*!40000 ALTER TABLE `TastingLogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TastingLogs_TastingNotes`
--

LOCK TABLES `TastingLogs_TastingNotes` WRITE;
/*!40000 ALTER TABLE `TastingLogs_TastingNotes` DISABLE KEYS */;
INSERT INTO `TastingLogs_TastingNotes` VALUES (1,9),(1,16),(1,23),(2,4),(2,5),(2,19),(3,7),(3,11),(3,22),(4,2),(4,6),(4,14),(5,12),(5,18),(5,25),(6,7),(6,12),(6,25),(7,14),(7,16),(7,19),(8,2),(8,6),(8,11),(9,5),(9,8),(9,24),(10,3),(10,9),(10,18);
/*!40000 ALTER TABLE `TastingLogs_TastingNotes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TastingNotes`
--

LOCK TABLES `TastingNotes` WRITE;
/*!40000 ALTER TABLE `TastingNotes` DISABLE KEYS */;
INSERT INTO `TastingNotes` VALUES (24,'Almond'),(8,'Apple'),(38,'Apricot'),(7,'Berry'),(41,'Berry Jam'),(20,'Bitter'),(3,'Black Tea'),(29,'Brown Sugar'),(42,'Butter Biscuit'),(36,'Buttery'),(5,'Caramel'),(27,'Cinnamon'),(6,'Citrus'),(13,'Cocoa'),(50,'Coconut'),(39,'Cranberry'),(34,'Creamy'),(19,'Dark Chocolate'),(14,'Earthy'),(2,'Floral'),(49,'Gingerbread'),(25,'Grapefruit'),(37,'Green Apple'),(28,'Herbal'),(10,'Honey'),(45,'Jasmine'),(47,'Lavender'),(30,'Lemon'),(40,'Lime'),(23,'Malt'),(33,'Maple Syrup'),(1,'Milk Chocolate'),(44,'Molasses'),(9,'Nutty'),(17,'Peach'),(35,'Pineapple'),(32,'Plum'),(18,'Red Fruit'),(46,'Rose'),(16,'Smoky'),(12,'Spicy'),(11,'Sweet'),(48,'Syrupy'),(22,'Tart'),(15,'Toasty'),(26,'Tobacco'),(31,'Tropical Fruit'),(4,'Vanilla'),(43,'Vanilla Bean'),(21,'Winey');
/*!40000 ALTER TABLE `TastingNotes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'erdun.e@example.com','ErdunE','password123','2025-03-13 03:53:16'),(2,'kai@example.com','Kai','password123','2025-03-13 03:53:16'),(3,'will@example.com','Will','password123','2025-03-13 03:53:16'),(4,'nav@example.com','Nav','password123','2025-03-13 03:53:16'),(5,'professor.nate@example.com','ProfessorNate','password123','2025-03-13 03:53:16'),(6,'professor.gs@example.com','ProfessorGS','password123','2025-03-13 03:53:16'),(7,'professor.alan@example.com','ProfessorAlan','password123','2025-03-13 03:53:16'),(8,'john.doe@example.com','JohnDoe','password123','2025-03-13 03:53:16'),(9,'jane.smith@example.com','JaneSmith','password123','2025-03-13 03:53:16'),(10,'alex.jones@example.com','AlexJones','password123','2025-03-13 03:53:16');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-13 18:39:33
