CREATE DATABASE  IF NOT EXISTS `auth_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `auth_db`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: auth_db
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
-- Table structure for table `authorization`
--

DROP TABLE IF EXISTS `authorization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorization` (
                                 `id` varchar(255) NOT NULL,
                                 `registered_client_id` varchar(255) NOT NULL,
                                 `principal_name` varchar(255) NOT NULL,
                                 `authorization_grant_type` varchar(255) NOT NULL,
                                 `authorized_scopes` text,
                                 `attributes` text,
                                 `state` varchar(500) DEFAULT NULL,
                                 `authorization_code_value` text,
                                 `authorization_code_issued_at` timestamp NULL DEFAULT NULL,
                                 `authorization_code_expires_at` timestamp NULL DEFAULT NULL,
                                 `authorization_code_metadata` text,
                                 `access_token_value` text,
                                 `access_token_issued_at` timestamp NULL DEFAULT NULL,
                                 `access_token_expires_at` timestamp NULL DEFAULT NULL,
                                 `access_token_metadata` text,
                                 `access_token_type` varchar(255) DEFAULT NULL,
                                 `access_token_scopes` text,
                                 `refresh_token_value` text,
                                 `refresh_token_issued_at` timestamp NULL DEFAULT NULL,
                                 `refresh_token_expires_at` timestamp NULL DEFAULT NULL,
                                 `refresh_token_metadata` text,
                                 `oidc_id_token_value` text,
                                 `oidc_id_token_issued_at` timestamp NULL DEFAULT NULL,
                                 `oidc_id_token_expires_at` timestamp NULL DEFAULT NULL,
                                 `oidc_id_token_metadata` text,
                                 `oidc_id_token_claims` text,
                                 `user_code_value` text,
                                 `user_code_issued_at` timestamp NULL DEFAULT NULL,
                                 `user_code_expires_at` timestamp NULL DEFAULT NULL,
                                 `user_code_metadata` text,
                                 `device_code_value` text,
                                 `device_code_issued_at` timestamp NULL DEFAULT NULL,
                                 `device_code_expires_at` timestamp NULL DEFAULT NULL,
                                 `device_code_metadata` text,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `authorizationconsent`
--

DROP TABLE IF EXISTS `authorizationconsent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorizationconsent` (
                                        `registered_client_id` varchar(255) NOT NULL,
                                        `principal_name` varchar(255) NOT NULL,
                                        `authorities` varchar(1000) NOT NULL,
                                        PRIMARY KEY (`registered_client_id`,`principal_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
                          `id` varchar(255) NOT NULL,
                          `client_id` varchar(255) NOT NULL,
                          `client_id_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          `client_secret` varchar(255) DEFAULT NULL,
                          `client_secret_expires_at` timestamp NULL DEFAULT NULL,
                          `client_name` varchar(255) NOT NULL,
                          `client_authentication_methods` varchar(1000) NOT NULL,
                          `authorization_grant_types` varchar(1000) NOT NULL,
                          `redirect_uris` varchar(1000) DEFAULT NULL,
                          `post_logout_redirect_uris` varchar(1000) DEFAULT NULL,
                          `scopes` varchar(1000) NOT NULL,
                          `client_settings` varchar(2000) NOT NULL,
                          `token_settings` varchar(2000) NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
                              `id` bigint unsigned NOT NULL AUTO_INCREMENT,
                              `name` varchar(60) NOT NULL,
                              `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `version` bigint unsigned NOT NULL DEFAULT '0',
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `permission_role`
--

DROP TABLE IF EXISTS `permission_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission_role` (
                                   `permission_id` bigint unsigned NOT NULL,
                                   `role_id` bigint unsigned NOT NULL,
                                   `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   `version` bigint unsigned NOT NULL DEFAULT '0',
                                   PRIMARY KEY (`permission_id`,`role_id`),
                                   KEY `permission_role_fk2` (`role_id`),
                                   CONSTRAINT `permission_role_fk1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                   CONSTRAINT `permission_role_fk2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `id` bigint unsigned NOT NULL AUTO_INCREMENT,
                        `name` varchar(60) NOT NULL,
                        `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `version` bigint unsigned NOT NULL DEFAULT '0',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_user`
--

DROP TABLE IF EXISTS `role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_user` (
                             `role_id` bigint unsigned NOT NULL,
                             `user_id` bigint unsigned NOT NULL,
                             `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `version` bigint unsigned NOT NULL DEFAULT '0',
                             PRIMARY KEY (`role_id`,`user_id`),
                             KEY `role_user_fk2` (`user_id`),
                             CONSTRAINT `role_user_fk1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                             CONSTRAINT `role_user_fk2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` bigint unsigned NOT NULL AUTO_INCREMENT,
                        `username` varchar(24) NOT NULL,
                        `password` varchar(200) NOT NULL,
                        `email` varchar(255) NOT NULL,
                        `enabled` bit(1) NOT NULL,
                        `account_expired` bit(1) NOT NULL,
                        `credentials_expired` bit(1) NOT NULL,
                        `account_locked` bit(1) NOT NULL,
                        `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `version` bigint unsigned NOT NULL DEFAULT '0',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `user_ix1` (`username`),
                        UNIQUE KEY `user_ix2` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-08 21:59:35
