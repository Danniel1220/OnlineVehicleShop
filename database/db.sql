-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: onlinevehicleshop
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) DEFAULT NULL,
  `model` varchar(45) DEFAULT NULL,
  `VIN` varchar(45) DEFAULT NULL,
  `manufacturingYear` int DEFAULT NULL,
  `carType` varchar(45) DEFAULT NULL,
  `engineId` int DEFAULT NULL,
  `transmissionId` int DEFAULT NULL,
  `tractionType` varchar(45) DEFAULT NULL,
  `numberOfDoors` int DEFAULT NULL,
  `Color` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `engineId` FOREIGN KEY (`id`) REFERENCES `engine` (`id`),
  CONSTRAINT `transmissionId` FOREIGN KEY (`id`) REFERENCES `transmission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (120,'test','test','test',2000,'SEDAN',121,120,'AWD',5,'Black');
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electricengine`
--

DROP TABLE IF EXISTS `electricengine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `electricengine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `engineType` varchar(45) DEFAULT NULL,
  `batteryCapacity` int DEFAULT NULL,
  `engineRange` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electricengine`
--

LOCK TABLES `electricengine` WRITE;
/*!40000 ALTER TABLE `electricengine` DISABLE KEYS */;
INSERT INTO `electricengine` VALUES (120,'test',120,120);
/*!40000 ALTER TABLE `electricengine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `engine`
--

DROP TABLE IF EXISTS `engine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `engine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fuelEngineId` int DEFAULT NULL,
  `electricEngineId` int DEFAULT NULL,
  `horsePower` int DEFAULT NULL,
  `torque` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `electricEngineId` FOREIGN KEY (`id`) REFERENCES `electricengine` (`id`),
  CONSTRAINT `fuelEngineId` FOREIGN KEY (`id`) REFERENCES `fuelengine` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `engine`
--

LOCK TABLES `engine` WRITE;
/*!40000 ALTER TABLE `engine` DISABLE KEYS */;
INSERT INTO `engine` VALUES (120,2,0,120,120),(121,2,NULL,120,120);
/*!40000 ALTER TABLE `engine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuelengine`
--

DROP TABLE IF EXISTS `fuelengine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fuelengine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fuelType` varchar(45) DEFAULT NULL,
  `fuelConsumption` float DEFAULT NULL,
  `numberOfCylinders` int DEFAULT NULL,
  `CO2Emissions` float DEFAULT NULL,
  `engineLayout` varchar(45) DEFAULT NULL,
  `hasTurbine` tinyint DEFAULT NULL,
  `hasSuperCharger` tinyint DEFAULT NULL,
  `strokeType` varchar(45) DEFAULT NULL,
  `displacement` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuelengine`
--

LOCK TABLES `fuelengine` WRITE;
/*!40000 ALTER TABLE `fuelengine` DISABLE KEYS */;
INSERT INTO `fuelengine` VALUES (1,'GASOLINE',10.1,8,521.2,'INLINE',1,1,'TWO_STROKE',1.4),(2,'DIESEL',10.1,10,521.2,'V',1,0,'FOUR_STROKE',1.5),(3,'LPG',10.1,12,521.2,'VR',1,1,'TWO_STROKE',1.6),(4,'METHANOL',10.1,14,521.2,'ROTARY',1,1,'TWO_STROKE',1.7),(5,'ETHANOL',10.1,16,521.2,'BOXER',1,1,'TWO_STROKE',1.8);
/*!40000 ALTER TABLE `fuelengine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `link` varchar(200) DEFAULT NULL,
  `postId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `postId_idx` (`postId`),
  CONSTRAINT `postId` FOREIGN KEY (`postId`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (17,'Post Title',1),(18,'Post Title',1),(19,'Post Title',1),(20,'Post Title',1),(21,'Post Title',1),(22,'Post Title',1),(23,'Post Title',1);
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `available` int DEFAULT NULL,
  `carId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `carId` FOREIGN KEY (`id`) REFERENCES `car` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'Post1','Post1',100,10,120),(3,'Post Title','Post Description',120,12,120);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idUser` int NOT NULL,
  `idPost` int NOT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idUser_idx` (`idUser`),
  KEY `idPost_idx` (`idPost`),
  CONSTRAINT `idPost` FOREIGN KEY (`idPost`) REFERENCES `post` (`id`),
  CONSTRAINT `idUser` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,3,1,'2022-04-07 00:00:00');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transmission`
--

DROP TABLE IF EXISTS `transmission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transmission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `transmissionType` varchar(45) DEFAULT NULL,
  `numberOfGears` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transmission`
--

LOCK TABLES `transmission` WRITE;
/*!40000 ALTER TABLE `transmission` DISABLE KEYS */;
INSERT INTO `transmission` VALUES (120,'MANUAL',5);
/*!40000 ALTER TABLE `transmission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'John Doe','123456','client','johndoe@test.com'),(2,'2','2','CLIENT','2'),(3,'mememe','Parola1','ADMIN','meme@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-07 14:20:42
