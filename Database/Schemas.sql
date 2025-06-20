CREATE TABLE `dealerships` (
  `dealership_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`dealership_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `inventory` (
  `dealership_id` int NOT NULL,
  `VIN` varchar(17) NOT NULL,
  PRIMARY KEY (`dealership_id`,`VIN`),
  KEY `VIN` (`VIN`),
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`dealership_id`) REFERENCES `dealerships` (`dealership_id`),
  CONSTRAINT `inventory_ibfk_2` FOREIGN KEY (`VIN`) REFERENCES `vehicles` (`VIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `lease_contracts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `VIN` varchar(17) DEFAULT NULL,
  `dealership_id` int DEFAULT NULL,
  `customer_name` varchar(50) DEFAULT NULL,
  `lease_start` date DEFAULT NULL,
  `lease_end` date DEFAULT NULL,
  `monthly_payment` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `VIN` (`VIN`),
  KEY `dealership_id` (`dealership_id`),
  CONSTRAINT `lease_contracts_ibfk_1` FOREIGN KEY (`VIN`) REFERENCES `vehicles` (`VIN`),
  CONSTRAINT `lease_contracts_ibfk_2` FOREIGN KEY (`dealership_id`) REFERENCES `dealerships` (`dealership_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `leasecontracts` (
  `lease_id` int NOT NULL AUTO_INCREMENT,
  `VIN` varchar(17) DEFAULT NULL,
  `lease_date` date DEFAULT NULL,
  `customer_name` varchar(50) DEFAULT NULL,
  `customer_email` varchar(50) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `make` varchar(30) DEFAULT NULL,
  `model` varchar(30) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `expected_value` decimal(10,2) DEFAULT NULL,
  `lease_fee` decimal(10,2) DEFAULT NULL,
  `total_lease_price` decimal(10,2) DEFAULT NULL,
  `monthly_payment` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`lease_id`),
  KEY `VIN` (`VIN`),
  CONSTRAINT `leasecontracts_ibfk_1` FOREIGN KEY (`VIN`) REFERENCES `vehicles` (`VIN`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sales_contracts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `VIN` varchar(17) DEFAULT NULL,
  `dealership_id` int DEFAULT NULL,
  `customer_name` varchar(50) DEFAULT NULL,
  `sale_date` date DEFAULT NULL,
  `sale_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `VIN` (`VIN`),
  KEY `dealership_id` (`dealership_id`),
  CONSTRAINT `sales_contracts_ibfk_1` FOREIGN KEY (`VIN`) REFERENCES `vehicles` (`VIN`),
  CONSTRAINT `sales_contracts_ibfk_2` FOREIGN KEY (`dealership_id`) REFERENCES `dealerships` (`dealership_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `salescontracts` (
  `contract_id` int NOT NULL AUTO_INCREMENT,
  `VIN` varchar(17) DEFAULT NULL,
  `sale_date` date DEFAULT NULL,
  `customer_name` varchar(50) DEFAULT NULL,
  `customer_email` varchar(50) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `make` varchar(30) DEFAULT NULL,
  `model` varchar(30) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `mileage` int DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `sales_tax` decimal(10,2) DEFAULT NULL,
  `fees` decimal(10,2) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `is_financed` tinyint(1) DEFAULT NULL,
  `monthly_payment` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`contract_id`),
  KEY `VIN` (`VIN`),
  CONSTRAINT `salescontracts_ibfk_1` FOREIGN KEY (`VIN`) REFERENCES `vehicles` (`VIN`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `vehicles` (
  `VIN` varchar(17) NOT NULL,
  `make` varchar(30) DEFAULT NULL,
  `model` varchar(30) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `sold` tinyint(1) DEFAULT NULL,
  `mileage` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`VIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
SELECT * FROM cardealershipdatabase.vehicles;