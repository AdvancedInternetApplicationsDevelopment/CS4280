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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Action'),(2,'Action Figures'),(3,'Role Play'),(4,'Halloween'),(5,'Board Games'),(6,'Education');
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
  `cc_holder` varchar(25) NOT NULL,
  `cc_email` varchar(25) NOT NULL,
  `expiry_date` varchar(12) NOT NULL,
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
INSERT INTO `cc_info` VALUES ('340040799743575','Holder One','email3@gmail.com','2020-05-04',1),('376986756768871','Holder Two','email1@gmail.com','2020-05-04',1),('4485685601098514','Holder Three','email5@gmail.com','2020-05-04',1),('4557457844259797','Holder Four','email2@gmail.com','2020-05-04',1),('5206351081144378','Holder Five','email6@gmail.com','2020-05-04',1),('5333063160259854','Holder Six','email4@gmail.com','2020-05-04',1);
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
  `credits` decimal(8,2) DEFAULT '0.00',
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
INSERT INTO `customer` VALUES ('email1@gmail.com','customer','one','12345678',NULL,'address1','address2','hk','kowloon','hk','hk','376986756768871',0.00),('email2@gmail.com','customer','two','23456781',NULL,'address1','address2','hk','hong kong','hk','hk','4557457844259797',0.00),('email3@gmail.com','customer','three','34567812',NULL,'address1','address2','hk','new terr','hk','hk','340040799743575',0.00),('email4@gmail.com','customer','four','45678123',NULL,'address1','address2','hk','tung chung','hk','hk','5333063160259854',0.00),('email5@gmail.com','customer','five','56781234',NULL,'address1','address2','hk','discovery','hk','hk','4485685601098514',0.00),('email6@gmail.com','customer','six','67812345',NULL,'address1','address2','hk','kowloon','hk','hk','5206351081144378',0.00);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount` (
  `discount_code` varchar(40) NOT NULL,
  `amount` decimal(8,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`discount_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES ('0ea49bd5-8116-4e62-ac9e-64e25d07d6f3',123.00),('8cc13148-f211-49db-8fd0-8b4657761311',123456.00),('e8934667-af0e-4a67-9bfb-0e027fd9f54e',123.00);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
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
INSERT INTO `login` VALUES ('email1@gmail.com','abcd123'),('email2@gmail.com','asdfgh'),('email3@gmail.com','123456'),('email4@gmail.com','qwerty'),('email5@gmail.com','zxcvb'),('email6@gmail.com','qwerty');
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
  `customer_id` varchar(25) NOT NULL,
  `amount` decimal(8,2) NOT NULL,
  `date_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `discount` decimal(8,2) DEFAULT '0.00',
  `credit` decimal(8,2) DEFAULT '0.00',
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
INSERT INTO `order_history` VALUES ('1','email3@gmail.com',2000.00,'2016-07-04 00:00:00',0.00,NULL),('2','email1@gmail.com',2000.00,'2016-07-04 00:00:00',0.00,NULL),('3','email2@gmail.com',5000.00,'2016-07-04 00:00:00',0.00,NULL);
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
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `fk_ordered_product_order_id_idx` (`order_id`),
  KEY `fk_ordered_product_product_idx` (`product_id`),
  KEY `index` (`order_id`,`product_id`),
  CONSTRAINT `fk_ordered_product_order` FOREIGN KEY (`order_id`) REFERENCES `order_history` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordered_product_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordered_product`
--

LOCK TABLES `ordered_product` WRITE;
/*!40000 ALTER TABLE `ordered_product` DISABLE KEYS */;
INSERT INTO `ordered_product` VALUES ('1','1',5),('1','14',100),('1','16',15),('1','2',10),('2','1',7),('2','10',100),('2','12',20),('2','16',50),('2','7',25),('2','9',100),('3','1',15),('3','15',20),('3','16',23);
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
  `category_id` int(10) unsigned NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` decimal(6,2) NOT NULL,
  `brand` varchar(10) DEFAULT NULL,
  `description` mediumtext,
  `add_info` varchar(50) DEFAULT NULL,
  `last_update` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `new` bit(1) NOT NULL DEFAULT b'1',
  `approved` bit(1) NOT NULL DEFAULT b'1',
  `owner` varchar(25) DEFAULT 'shop',
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
INSERT INTO `product` VALUES ('1','Motor Max Color Twisters - Single Pack','model 3',1,90,90.00,'brand 1','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('10','Sw7 Jedi Master Lightsaber','model 3',4,90,90.00,'brand 1','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('11','Sw7 Kylo Ren Deluxe Costume(M)','model 2',4,90,90.00,'brand 2','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('12','Rubie S Avengers AOU H/S Iron Man Mk43 (L)','model 1',4,90,90.00,'brand 3','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('13','Hasbro Monopoly','model 3',5,90,90.00,'brand 1','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('14','Mattel Games Bounce-Off','model 2',5,90,90.00,'brand 2','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('15','Mattel Games Uno','model 1',5,90,90.00,'brand 3','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('16','Vtech Stencil & Learn Studio','model 3',6,90,90.00,'brand 1','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('17','Vtech Winnie The Pooh: Pooh & Friends Phone','model 2',6,90,90.00,'brand 2','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('18','Oregon Scientific Smart Anatomy','model 1',6,90,90.00,'brand 3','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('2','Fast Lane Fl 1:24 Die Cast Vehicles, 12 Asst','model 2',1,90,90.00,'brand 2','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('3','Disney Hatch N Heroes-Cars Francesco','model 1',1,90,90.00,'brand 3','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('4','Minions-Deluxe Plush Buddies Asst','model 3',2,90,90.00,'brand 1','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('5','Disney Hatch N Heroes-Cars King','model 2',2,90,90.00,'brand 2','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('6','Bandai Yo-Kai Medal Zero Z Vol. 1 (Bonus Set)','model 1',2,90,90.00,'brand 3','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('7','Nerf Ner Rival Zeus Mxv 1200 Ast','model 3',3,90,90.00,'brand 1','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('8','24\" Foam Hammer','model 2',3,90,90.00,'brand 2','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL),('9','Nerf Ner Sonic Ice Mega Magnus','model 1',3,90,90.00,'brand 3','desc',NULL,'0000-00-00 00:00:00','\0','\0',NULL);
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
  `admin_reply` varchar(60) DEFAULT NULL,
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
INSERT INTO `review` VALUES ('email1@gmail.com','1','v',NULL,4),('email2@gmail.com','15','hjj',NULL,3),('email3@gmail.com','1','A',NULL,5),('email3@gmail.com','15','hhhj',NULL,5),('email4@gmail.com','15','b',NULL,4),('email5@gmail.com','12','c',NULL,3),('email6@gmail.com','2','bb',NULL,3);
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

-- Dump completed on 2016-04-24 13:40:55
