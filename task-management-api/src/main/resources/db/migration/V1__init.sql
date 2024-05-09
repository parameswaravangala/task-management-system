CREATE DATABASE  IF NOT EXISTS `task_mgmt_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `task_mgmt_db`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: task_mgmt_db
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `notification_channels`
--

DROP TABLE IF EXISTS `notification_channels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification_channels` (
                                         `id` int NOT NULL AUTO_INCREMENT,
                                         `created_date` datetime(6) NOT NULL,
                                         `last_modified_date` datetime(6) DEFAULT NULL,
                                         `created_by` varchar(255) NOT NULL,
                                         `last_modified_by` varchar(255) DEFAULT NULL,
                                         `name` varchar(255) NOT NULL,
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification_template`
--

DROP TABLE IF EXISTS `notification_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification_template` (
                                         `channel_id` int NOT NULL,
                                         `id` int NOT NULL AUTO_INCREMENT,
                                         `notification_type_id` int NOT NULL DEFAULT '0',
                                         `created_at` datetime(6) NOT NULL,
                                         `created_date` datetime(6) NOT NULL,
                                         `last_modified_date` datetime(6) DEFAULT NULL,
                                         `updated_at` datetime(6) DEFAULT NULL,
                                         `title` varchar(100) NOT NULL,
                                         `description` varchar(2048) DEFAULT NULL,
                                         `created_by` varchar(255) NOT NULL,
                                         `last_modified_by` varchar(255) DEFAULT NULL,
                                         `content` tinytext,
                                         PRIMARY KEY (`id`),
                                         KEY `FKscclwl2dbydd68e3bd7frriho` (`channel_id`),
                                         KEY `FKp61jnra6ickbr1yqucecg3yl4` (`notification_type_id`),
                                         CONSTRAINT `FKp61jnra6ickbr1yqucecg3yl4` FOREIGN KEY (`notification_type_id`) REFERENCES `notification_types` (`id`),
                                         CONSTRAINT `FKscclwl2dbydd68e3bd7frriho` FOREIGN KEY (`channel_id`) REFERENCES `notification_channels` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification_types`
--

DROP TABLE IF EXISTS `notification_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification_types` (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `created_date` datetime(6) NOT NULL,
                                      `last_modified_date` datetime(6) DEFAULT NULL,
                                      `created_by` varchar(255) NOT NULL,
                                      `last_modified_by` varchar(255) DEFAULT NULL,
                                      `name` varchar(255) NOT NULL,
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
                        `due_date` date DEFAULT NULL,
                        `priority` int NOT NULL,
                        `created_date` datetime(6) NOT NULL,
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `last_modified_date` datetime(6) DEFAULT NULL,
                        `created_by` varchar(255) NOT NULL,
                        `description` varchar(255) DEFAULT NULL,
                        `last_modified_by` varchar(255) DEFAULT NULL,
                        `status`enum('TO_DO','IN_PROGRESS','DONE') NOT NULL,
                        `title` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `title_UNIQUE` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `task_history`
--

DROP TABLE IF EXISTS `task_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_history` (
                                `due_date` date DEFAULT NULL,
                                `id` int NOT NULL AUTO_INCREMENT,
                                `priority` int NOT NULL,
                                `created_date` datetime(6) NOT NULL,
                                `last_modified_date` datetime(6) DEFAULT NULL,
                                `task_id` bigint NOT NULL,
                                `created_by` varchar(255) NOT NULL,
                                `description` varchar(255) DEFAULT NULL,
                                `last_modified_by` varchar(255) DEFAULT NULL,
                                `status` enum('TO_DO','IN_PROGRESS','DONE') NOT NULL,
                                `title` varchar(255) DEFAULT NULL,
                                `action` enum('INSERTED','UPDATED','DELETED') DEFAULT NULL,
                                `version` int DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `FKer57q2libi1e9njpj6faoxd2i` (`task_id`),
                                CONSTRAINT `FKer57q2libi1e9njpj6faoxd2i` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_notification_preferences`
--

DROP TABLE IF EXISTS `user_notification_preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_notification_preferences` (
                                                 `channel_id` int DEFAULT NULL,
                                                 `id` int NOT NULL AUTO_INCREMENT,
                                                 `is_enabled` bit(1) DEFAULT NULL,
                                                 `notification_type_id` int DEFAULT NULL,
                                                 `user_id` int DEFAULT NULL,
                                                 `created_date` datetime(6) NOT NULL,
                                                 `last_modified_date` datetime(6) DEFAULT NULL,
                                                 `created_by` varchar(255) NOT NULL,
                                                 `last_modified_by` varchar(255) DEFAULT NULL,
                                                 PRIMARY KEY (`id`),
                                                 KEY `FKs6nexwmrnp8cf4kmretp0pxwf` (`channel_id`),
                                                 KEY `FKnd6oc46498eqfckqlsynhy1n4` (`notification_type_id`),
                                                 KEY `FKjqii7bt0v7fyg56obr7nio4ax` (`user_id`),
                                                 CONSTRAINT `FKjqii7bt0v7fyg56obr7nio4ax` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                                                 CONSTRAINT `FKnd6oc46498eqfckqlsynhy1n4` FOREIGN KEY (`notification_type_id`) REFERENCES `notification_types` (`id`),
                                                 CONSTRAINT `FKs6nexwmrnp8cf4kmretp0pxwf` FOREIGN KEY (`channel_id`) REFERENCES `notification_channels` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `created_date` datetime(6) NOT NULL,
                         `last_modified_date` datetime(6) DEFAULT NULL,
                         `created_by` varchar(255) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `first_name` varchar(255) NOT NULL,
                         `last_modified_by` varchar(255) DEFAULT NULL,
                         `last_name` varchar(255) NOT NULL,
                         `mobile` varchar(255) NOT NULL,
                         `user_name` varchar(255) NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users_user_notification_preferences`
--

DROP TABLE IF EXISTS `users_user_notification_preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_user_notification_preferences` (
                                                       `user_id` int NOT NULL,
                                                       `user_notification_preferences_id` int NOT NULL,
                                                       PRIMARY KEY (`user_id`,`user_notification_preferences_id`),
                                                       UNIQUE KEY `UK_dcjk1t67322kcldqvrv1iym87` (`user_notification_preferences_id`),
                                                       CONSTRAINT `FKae2dhnh1rtlj8lj9i96xblamm` FOREIGN KEY (`user_notification_preferences_id`) REFERENCES `user_notification_preferences` (`id`),
                                                       CONSTRAINT `FKg9d44dqu77wewxuwfwmdc170p` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-08 21:57:36
