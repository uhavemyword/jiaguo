create database mytest;

use mytest;

CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `creation_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `contact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `creation_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT NOT NULL,
  `phone` VARCHAR(50) NULL,
  `address` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `favorite` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `creation_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` VARCHAR(255) NOT NULL,
  `url` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `creation_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `external_id` int NOT NULL,
  `info_json` json NOT NULL,
  PRIMARY KEY (`id`));