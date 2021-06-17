DROP TABLE IF EXISTS `customer_cart`;

CREATE TABLE `customer_cart`
(
    `customer_id` int(11) NOT NULL,
    `product_id`  int(11) NOT NULL,
    `quantity`    int(11) DEFAULT NULL,
    PRIMARY KEY (`customer_id`, `product_id`),
    CONSTRAINT `customer_cart_customer_id_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
    CONSTRAINT `customer_cart_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;