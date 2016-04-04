CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	5.6.28-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'mesc'),(2,'dairy'),(3,'meats'),(4,'bakery'),(5,'fruit & veg');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cc_info`
--

DROP TABLE IF EXISTS `cc_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cc_info` (
  `cc_number` varchar(19) NOT NULL,
  `cc_email` varchar(25) NOT NULL,
  `expiry_date` date NOT NULL,
  `ccv` int(4) NOT NULL,
  PRIMARY KEY (`cc_number`),
  UNIQUE KEY `unique` (`cc_number`),
  KEY `fk_ccinfo_customer_idx` (`cc_number`),
  KEY `fk_ccinfo_customer_idx1` (`cc_email`),
  CONSTRAINT `fk_ccinfo_customer` FOREIGN KEY (`cc_number`) REFERENCES `customer` (`cc_number`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc_info`
--

LOCK TABLES `cc_info` WRITE;
/*!40000 ALTER TABLE `cc_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `cc_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `email` varchar(25) NOT NULL,
  `fname` varchar(10) NOT NULL,
  `lname` varchar(10) DEFAULT NULL,
  `phone` varchar(15) NOT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `address1` varchar(40) NOT NULL,
  `address2` varchar(40) DEFAULT NULL,
  `city` varchar(10) NOT NULL,
  `postal` varchar(10) NOT NULL,
  `country` varchar(10) NOT NULL,
  `region` varchar(10) DEFAULT NULL,
  `cc_number` varchar(19) NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `unique` (`email`,`cc_number`),
  KEY `index` (`email`,`fname`,`lname`,`cc_number`),
  KEY `ccnum` (`cc_number`),
  CONSTRAINT `fk_customer_login` FOREIGN KEY (`email`) REFERENCES `login` (`idlogin`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `idlogin` varchar(25) NOT NULL,
  `idpass` varchar(45) NOT NULL,
  PRIMARY KEY (`idlogin`),
  UNIQUE KEY `unique` (`idlogin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_history`
--

DROP TABLE IF EXISTS `order_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_history` (
  `id` varchar(40) NOT NULL,
  `amount` decimal(8,2) NOT NULL,
  `date_created` datetime NOT NULL,
  `customer_id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`id`),
  KEY `index` (`id`,`customer_id`),
  KEY `fk_order_history_customer_idx` (`customer_id`),
  CONSTRAINT `fk_order_history_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_history`
--

LOCK TABLES `order_history` WRITE;
/*!40000 ALTER TABLE `order_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordered_product`
--

DROP TABLE IF EXISTS `ordered_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordered_product` (
  `order_id` varchar(40) NOT NULL,
  `product_id` varchar(40) NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  `date_created` datetime NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `fk_ordered_product_order_id_idx` (`order_id`),
  KEY `fk_ordered_product_product_idx` (`product_id`),
  KEY `index` (`order_id`,`product_id`,`date_created`),
  CONSTRAINT `fk_ordered_product_order` FOREIGN KEY (`order_id`) REFERENCES `order_history` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordered_product_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordered_product`
--

LOCK TABLES `ordered_product` WRITE;
/*!40000 ALTER TABLE `ordered_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordered_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` varchar(40) NOT NULL,
  `name` varchar(45) NOT NULL,
  `model_num` varchar(20) NOT NULL,
  `description` mediumtext,
  `available` bit(1) DEFAULT b'0',
  `price` decimal(6,2) NOT NULL,
  `category_id` int(10) unsigned NOT NULL,
  `add_info` varchar(50) DEFAULT NULL,
  `image` blob,
  `last_update` datetime NOT NULL,
  `new` bit(1) NOT NULL DEFAULT b'1',
  `approved` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_product_category` (`category_id`),
  KEY `index` (`id`,`category_id`,`last_update`,`approved`,`new`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('1','milk','','semi skimmed (1L)',NULL,1.70,1,NULL,NULL,'2016-12-31 00:00:00','',''),('10','sesame seed bagel','','4 bagels',NULL,1.19,3,NULL,NULL,'2016-12-31 00:00:00','',''),('11','pumpkin seed bun','','4 buns',NULL,1.15,3,NULL,NULL,'2016-12-31 00:00:00','',''),('12','chocolate cookies','','contain peanuts<br>(3 cookies)',NULL,2.39,3,NULL,NULL,'2016-12-31 00:00:00','',''),('13','corn on the cob','','2 pieces',NULL,1.59,4,NULL,NULL,'2016-12-31 00:00:00','',''),('14','red currants','','150g',NULL,2.49,4,NULL,NULL,'2016-12-31 00:00:00','',''),('15','broccoli','','500g',NULL,1.29,4,NULL,NULL,'2016-12-31 00:00:00','',''),('16','seedless watermelon','','250g',NULL,1.49,4,NULL,NULL,'2016-12-31 00:00:00','',''),('2','cheese','','mild cheddar (330g)',NULL,2.39,1,NULL,NULL,'2016-12-31 00:00:00','',''),('3','butter','','unsalted (250g)',NULL,1.09,1,NULL,NULL,'2016-12-31 00:00:00','',''),('4','free range eggs','','medium-sized (6 eggs)',NULL,1.76,1,NULL,NULL,'2016-12-31 00:00:00','',''),('5','organic meat patties','','rolled in fresh herbs<br>2 patties (250g)',NULL,2.29,2,NULL,NULL,'2016-12-31 00:00:00','',''),('6','parma ham','','matured, organic (70g)',NULL,3.49,2,NULL,NULL,'2016-12-31 00:00:00','',''),('7','chicken leg','','free range (250g)',NULL,2.59,2,NULL,NULL,'2016-12-31 00:00:00','',''),('8','sausages','','reduced fat, pork<br>3 sausages (350g)',NULL,3.55,2,NULL,NULL,'2016-12-31 00:00:00','',''),('9','sunflower seed loaf','','600g',NULL,1.89,3,NULL,NULL,'2016-12-31 00:00:00','','');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `customer_id` varchar(25) NOT NULL,
  `product_id` varchar(40) NOT NULL,
  `comments` varchar(60) NOT NULL,
  `star` int(1) NOT NULL,
  PRIMARY KEY (`customer_id`,`product_id`),
  KEY `fk_review_product_idx` (`product_id`),
  KEY `fk_review_customer_idx` (`customer_id`),
  KEY `index` (`customer_id`,`product_id`,`comments`),
  CONSTRAINT `fk_review_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-04 20:50:03
