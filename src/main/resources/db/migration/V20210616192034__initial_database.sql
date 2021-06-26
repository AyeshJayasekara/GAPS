-- MySQL dump 10.13  Distrib 8.0.21, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: star
-- ------------------------------------------------------
-- Server version	5.7.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Current Database: `star`
--

# CREATE
#     DATABASE /*!32312 IF NOT EXISTS */ `star` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
#
# USE
#     `star`;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category`
(
    `id`     int(11) NOT NULL AUTO_INCREMENT,
    `name`   varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `enable` tinyint(1)                              DEFAULT '1',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK
    TABLES `category` WRITE;
/*!40000 ALTER TABLE `category`
    DISABLE KEYS */;
INSERT INTO `category` (`id`, `name`, `enable`)
VALUES (1, 'Liqueur', 1),
       (2, 'Groceries', 1);
/*!40000 ALTER TABLE `category`
    ENABLE KEYS */;
UNLOCK
    TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(50) COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `last_name`  varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK
    TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer`
    DISABLE KEYS */;
INSERT INTO `customer` (`id`, `first_name`, `last_name`)
VALUES (2, 'Ayesh', 'Jayasekara'),
       (3, 'Barrak', 'Obama');
/*!40000 ALTER TABLE `customer`
    ENABLE KEYS */;
UNLOCK
    TABLES;

--
-- Table structure for table `customer_cart`
--

DROP TABLE IF EXISTS `customer_cart`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_cart`
(
    `customer_id` int(11) NOT NULL,
    `product_id`  int(11) NOT NULL,
    `quantity`    int(11) DEFAULT NULL,
    PRIMARY KEY (`customer_id`),
    KEY `customer_cart_product_id_fk` (`product_id`),
    CONSTRAINT `customer_cart_customer_id_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
    CONSTRAINT `customer_cart_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_cart`
--

LOCK
    TABLES `customer_cart` WRITE;
/*!40000 ALTER TABLE `customer_cart`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_cart`
    ENABLE KEYS */;
UNLOCK
    TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `product_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `price`        decimal(15, 2)                          DEFAULT '0.00',
    `stock_level`  int(11)                                 DEFAULT '0',
    `unit`         varchar(10) COLLATE utf8mb4_unicode_ci  DEFAULT NULL,
    `sku`          varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `weight`       decimal(10, 3)                          DEFAULT '0.000',
    `sub_category` int(11)                                 DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK
    TABLES `product` WRITE;
/*!40000 ALTER TABLE `product`
    DISABLE KEYS */;
INSERT INTO `product` (`id`, `product_name`, `price`, `stock_level`, `unit`, `sku`, `weight`, `sub_category`)
VALUES (1, 'Well Fanta', 1500.00, 1000, 'Bottle', '1-1-1', 0.350, 1),
       (2, 'Vat 69', 7800.00, 20, 'Bottle', '1-1-2', 0.500, 1),
       (3, 'Murukku (Chips) 50G', 350.00, 100, 'Pack', '1-2-3', 0.050, 2),
       (4, 'Pork Bite', 2000.00, 200, 'Portion', '1-2-4', 1.000, 2);
/*!40000 ALTER TABLE `product`
    ENABLE KEYS */;
UNLOCK
    TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setting`
(
    `code`  varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
    `value` text COLLATE utf8mb4_unicode_ci        NOT NULL,
    PRIMARY KEY (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK
    TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting`
    DISABLE KEYS */;
INSERT INTO `setting` (`code`, `value`)
VALUES ('CURRENCY', 'LKR'),
       ('VAT', '10');
/*!40000 ALTER TABLE `setting`
    ENABLE KEYS */;
UNLOCK
    TABLES;

--
-- Table structure for table `shipping_weight_index`
--

DROP TABLE IF EXISTS `shipping_weight_index`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping_weight_index`
(
    `int_id`      int(11) NOT NULL AUTO_INCREMENT,
    `block_start` decimal(10, 3) DEFAULT '0.000',
    `block_end`   decimal(10, 3) DEFAULT '0.000',
    `amount`      decimal(15, 2) DEFAULT '0.00',
    PRIMARY KEY (`int_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_weight_index`
--

LOCK
    TABLES `shipping_weight_index` WRITE;
/*!40000 ALTER TABLE `shipping_weight_index`
    DISABLE KEYS */;
INSERT INTO `shipping_weight_index` (`int_id`, `block_start`, `block_end`, `amount`)
VALUES (1, 0.000, 1.000, 100.00),
       (2, 1.001, 2.000, 500.00),
       (3, 2.001, 99999.000, 1000.00);
/*!40000 ALTER TABLE `shipping_weight_index`
    ENABLE KEYS */;
UNLOCK
    TABLES;

--
-- Table structure for table `sub_category`
--

DROP TABLE IF EXISTS `sub_category`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sub_category`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `category_id` int(11)                                 DEFAULT NULL,
    `name`        varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `enable`      tinyint(1)                              DEFAULT '1',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_category`
--

LOCK
    TABLES `sub_category` WRITE;
/*!40000 ALTER TABLE `sub_category`
    DISABLE KEYS */;
INSERT INTO `sub_category` (`id`, `category_id`, `name`, `enable`)
VALUES (1, 1, 'Arrack', 1),
       (2, 2, 'Bites', 1);
/*!40000 ALTER TABLE `sub_category`
    ENABLE KEYS */;
UNLOCK
    TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2021-06-16 20:21:12
