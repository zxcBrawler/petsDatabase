CREATE DATABASE `petsdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE petsdb;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `current_address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lmac5ci4ou7jrdlnf8okh92m0` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `country` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `continent` varchar(50) NOT NULL,
  `date_formed` int NOT NULL,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `food` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `main_ingredient` varchar(100) NOT NULL,
  `name_food` varchar(25) NOT NULL,
  `manufacturer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkunma15b50bkyukgj6swba7v8` (`manufacturer_id`),
  CONSTRAINT `FKkunma15b50bkyukgj6swba7v8` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `manufacturer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL,
  `year_formed` int NOT NULL,
  `country_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpxwbidpgdjo58391nm3xavg56` (`country_id`),
  CONSTRAINT `FKpxwbidpgdjo58391nm3xavg56` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pet` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age` int NOT NULL,
  `name` varchar(25) NOT NULL,
  `weight` double NOT NULL,
  `type_pet_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m4ltx7q0byuavhe3oeewb0r1g` (`type_pet_id`),
  CONSTRAINT `FKgg7ntqamd0j8cofsxm380ii4x` FOREIGN KEY (`type_pet_id`) REFERENCES `type_pet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pet_food` (
  `food_id` bigint NOT NULL,
  `pet_id` bigint NOT NULL,
  KEY `FK6qvrpcd0yvkkl68p5uc3tsvpw` (`pet_id`),
  KEY `FK79fy20mavx7i351tsp7hcebr3` (`food_id`),
  CONSTRAINT `FK6qvrpcd0yvkkl68p5uc3tsvpw` FOREIGN KEY (`pet_id`) REFERENCES `food` (`id`),
  CONSTRAINT `FK79fy20mavx7i351tsp7hcebr3` FOREIGN KEY (`food_id`) REFERENCES `pet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `type_pet` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age` int NOT NULL,
  `name` varchar(25) NOT NULL,
  `password` varchar(255) NOT NULL,
  `surname` varchar(35) NOT NULL,
  `username` varchar(40) DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKddefmvbrws3hvl5t0hnnsv8ox` (`address_id`),
  CONSTRAINT `FKddefmvbrws3hvl5t0hnnsv8ox` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_pets` (
  `user_id` bigint NOT NULL,
  `pet_id` bigint NOT NULL,
  KEY `FKesdtlhv1guvu484v59y1c6qyd` (`pet_id`),
  KEY `FKscwsj3j09gy9nl1ve1o4vrq59` (`user_id`),
  CONSTRAINT `FKesdtlhv1guvu484v59y1c6qyd` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`),
  CONSTRAINT `FKscwsj3j09gy9nl1ve1o4vrq59` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `roles` enum('ADMIN','USER','VET') DEFAULT NULL,
  KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
