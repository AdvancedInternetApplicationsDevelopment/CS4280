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
INSERT INTO `order_history` VALUES ('33dff36a-9eef-4e11-aeb4-b7717e6f4f8a','email2@gmail.com',5000.00,'2016-07-04 00:00:00',0.00,NULL),('48e61148-10fd-4306-91a6-64521866f22b','email3@gmail.com',2000.00,'2016-07-04 00:00:00',0.00,NULL),('6736d56d-1704-40ed-a091-623c55ca6b21','email1@gmail.com',2000.00,'2016-07-04 00:00:00',0.00,NULL);
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
INSERT INTO `ordered_product` VALUES ('33dff36a-9eef-4e11-aeb4-b7717e6f4f8a','455dacc7-354e-44d3-8048-355b3523cb1f',15),('33dff36a-9eef-4e11-aeb4-b7717e6f4f8a','a5488b48-356d-4d04-8ea5-5d9bfb9c75c2',23),('33dff36a-9eef-4e11-aeb4-b7717e6f4f8a','e64961f6-25fd-4571-83d1-9886b95a6927',20),('48e61148-10fd-4306-91a6-64521866f22b','455dacc7-354e-44d3-8048-355b3523cb1f',5),('48e61148-10fd-4306-91a6-64521866f22b','a5488b48-356d-4d04-8ea5-5d9bfb9c75c2',15),('48e61148-10fd-4306-91a6-64521866f22b','b133105e-3a6f-410c-adf8-14ff21678696',10),('48e61148-10fd-4306-91a6-64521866f22b','b98870cc-6789-4649-aa50-9182a9913c04',100),('6736d56d-1704-40ed-a091-623c55ca6b21','455dacc7-354e-44d3-8048-355b3523cb1f',7),('6736d56d-1704-40ed-a091-623c55ca6b21','a5488b48-356d-4d04-8ea5-5d9bfb9c75c2',100),('6736d56d-1704-40ed-a091-623c55ca6b21','b133105e-3a6f-410c-adf8-14ff21678696',25),('6736d56d-1704-40ed-a091-623c55ca6b21','b98870cc-6789-4649-aa50-9182a9913c04',50),('6736d56d-1704-40ed-a091-623c55ca6b21','d7803c62-0110-4917-ba5c-1f8ec32a4c20',20),('6736d56d-1704-40ed-a091-623c55ca6b21','e64961f6-25fd-4571-83d1-9886b95a6927',100);
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
INSERT INTO `product` VALUES ('04d4a7af-0b2d-494d-aefb-98550fe83036','Sw7 Kylo Ren Deluxe Costume(M)','model 2',4,90,90.00,'brand 2','desc',NULL,'2016-04-24 19:02:29','\0','\0','email1@gmail.com'),('06175e53-cc5a-466d-9de7-584fe50dff74','Disney Hatch N Heroes-Cars Francesco','model 1',1,90,90.00,'brand 3','desc',NULL,'2016-04-24 19:38:59','','','shop'),('24700d6f-7506-415c-ad64-57a888751674','Oregon Scientific Smart Anatomy','model 1',6,90,90.00,'brand 3','desc',NULL,'2016-04-24 19:38:59','','','shop'),('2e3c7281-7f56-4010-bd7b-9079297c3335','Parrot Jumping Race Drone- Jett','model 2',2,90,90.00,'brand 2','desc',NULL,'2016-04-24 19:38:59','','','shop'),('3c43ad5a-34aa-4f35-a6cf-e63a9fd44576','Fast Lane Fl 1:24 Die Cast Vehicles, 12 Asst','model 2',1,90,90.00,'brand 2','desc',NULL,'2016-04-24 19:38:59','','','shop'),('455dacc7-354e-44d3-8048-355b3523cb1f','Motor Max Color Twisters - Single Pack','model 3',1,90,90.00,'brand 1','desc',NULL,'2016-04-24 19:02:29','\0','\0','email1@gmail.com'),('47ff0544-c353-45ca-8c30-38d200402445','Nerf Ner Rival Zeus Mxv 1200 Ast','model 3',3,90,90.00,'brand 1','desc',NULL,'2016-04-24 19:38:59','','','shop'),('4ae20080-06e3-446d-8694-3771f246863a','Yokai Medal Vol. 2','model 3',1,90,90.00,'brand 1','desc',NULL,'2016-04-24 19:02:29','\0','\0','email1@gmail.com'),('4b919832-7c20-4d5e-9983-98e5d04909a','Minions-Deluxe Plush Buddies Asst','model 3',2,90,90.00,'brand 1','desc',NULL,'2016-04-24 19:38:59','','','shop'),('580ca960-b9ee-4c42-ab1a-20ed6511d7c6','Slimy Squeeze It -assorted','model 2',5,90,90.00,'brand 2','desc',NULL,'2016-04-24 19:38:59','','','shop'),('63792a50-97a6-47ef-9e3c-91d8ffea8dda','Silverlit - Digibirds Special Edition- Gold','model 1',6,90,90.00,'brand 3','desc',NULL,'2016-04-24 19:02:29','\0','','email2@gmail.com'),('6dab5aec-6b70-4f39-a8da-78954e377ada','Intek - Avenger -Age Of Ultron 10 Images Lcd ','model 1',3,90,90.00,'brand 3','desc',NULL,'2016-04-24 19:02:29','\0','\0','email2@gmail.com'),('704a46b2-9deb-4d0c-884c-f37d33baf830','24\" Foam Hammer','model 2',3,90,90.00,'brand 2','desc',NULL,'2016-04-24 19:38:59','','','shop'),('71cc13e2-47af-4376-b9e4-6350127a738a','Googly Soccer Ball -assorted','model 1',6,90,90.00,'brand 3','desc',NULL,'2016-04-24 19:38:59','','','shop'),('7ca63913-d89c-4f3b-a8f6-78cf7b3c37e4','Hexbug - Aquabot 2.0 Single Pack','model 2',5,90,90.00,'brand 2','desc',NULL,'2016-04-24 19:02:29','\0','','email2@gmail.com'),('85cfd38a-cf9d-4507-8070-1a7f5335dbdc','Silverlit - Digi Bird With Cage (Wave 2)','model 3',4,90,90.00,'brand 1','desc',NULL,'2016-04-24 19:02:29','\0','','email3@gmail.com'),('96c1a3bf-b74d-44d5-a9cd-2d77316ac21b','Shopkins - Shoppin Cart','model 1',3,90,90.00,'brand 3','desc',NULL,'2016-04-24 19:38:59','','','shop'),('9ab06de4-6076-4aec-9277-2d05ebf20e36','Mattel Games Bounce-Off','model 2',5,90,90.00,'brand 2','desc',NULL,'2016-04-24 19:02:29','\0','','email3@gmail.com'),('9d80e0e9-8ad3-4670-85c8-8d621eb3be95','Vtech Stencil & Learn Studio','model 3',6,90,90.00,'brand 1','desc',NULL,'2016-04-24 19:02:29','\0','','email3@gmail.com'),('a5488b48-356d-4d04-8ea5-5d9bfb9c75c2','Mattel Games Uno','model 1',5,90,90.00,'brand 3','desc',NULL,'2016-04-24 19:02:29','\0','','email4@gmail.com'),('a67609cd-e6d9-4143-b867-8b0fc3b8b90d','Yo-Kai Gashakoro Sp Nowadays Yo-Kai','model 2',2,90,90.00,'brand 2','desc',NULL,'2016-04-24 19:02:29','\0','\0','email4@gmail.com'),('a67ee8c6-18b7-423c-b69d-96ce75418391','Mini Horror Slimy -assorted','model 3',4,90,90.00,'brand 1','desc',NULL,'2016-04-24 19:38:59','','','shop'),('b133105e-3a6f-410c-adf8-14ff21678696','Sw7 Jedi Master Lightsaber','model 3',4,90,90.00,'brand 1','desc',NULL,'2016-04-24 19:02:29','\0','\0','email4@gmail.com'),('b7a97a61-58b3-4dab-b466-3c7a492e9f58','Rubie S Avengers AOU H/S Iron Man Mk43 (L)','model 1',4,90,90.00,'brand 3','desc',NULL,'2016-04-24 19:02:29','\0','\0','email5@gmail.com'),('b98870cc-6789-4649-aa50-9182a9913c04','Bandai Yo-Kai Medal Zero Z Vol. 1 (Bonus Set)','model 1',2,90,90.00,'brand 3','desc',NULL,'2016-04-24 19:38:59','','','shop'),('c0849a25-d57e-4d1d-b9e7-a8a4828acb93','Hexbug - Aquabot 3.0 Ir Fish','model 3',1,90,90.00,'brand 1','desc',NULL,'2016-04-24 19:38:59','','','shop'),('d7803c62-0110-4917-ba5c-1f8ec32a4c20','Disney Hatch N Heroes-Cars King','model 2',2,90,90.00,'brand 2','desc',NULL,'2016-04-24 19:38:59','','','shop'),('e64961f6-25fd-4571-83d1-9886b95a6927','Nerf Ner Sonic Ice Mega Magnus','model 1',3,90,90.00,'brand 3','desc',NULL,'2016-04-24 19:38:59','','','shop'),('e84d5b38-9e05-40ae-ba7d-4ddc574f1814','Vtech Winnie The Pooh: Pooh & Friends Phone','model 2',6,90,90.00,'brand 2','desc',NULL,'2016-04-24 19:02:29','\0','','email5@gmail.com'),('e9f7e5cc-8dab-44d1-ac06-4313f1b160b4','Hasbro Monopoly','model 3',5,90,90.00,'brand 1','desc',NULL,'2016-04-24 19:02:29','\0','','email5@gmail.com');
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
INSERT INTO `review` VALUES ('email1@gmail.com','455dacc7-354e-44d3-8048-355b3523cb1f','Comment One',NULL,4),('email2@gmail.com','d7803c62-0110-4917-ba5c-1f8ec32a4c20','Comment Two',NULL,3),('email3@gmail.com','455dacc7-354e-44d3-8048-355b3523cb1f','Comment Three',NULL,5),('email3@gmail.com','704a46b2-9deb-4d0c-884c-f37d33baf830','Comment Four','Reply Four',5),('email4@gmail.com','d7803c62-0110-4917-ba5c-1f8ec32a4c20','Comment Five','Reply Five',4),('email5@gmail.com','b98870cc-6789-4649-aa50-9182a9913c04','Comment Six','Reply Six',3),('email6@gmail.com','704a46b2-9deb-4d0c-884c-f37d33baf830','Comment Seven',NULL,3);
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

-- Dump completed on 2016-04-24 20:01:19
