/*
MySQL Backup
Database: snap
Backup Time: 2018-08-05 00:27:59
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `snap`.`drivers`;
DROP TABLE IF EXISTS `snap`.`passengers`;
DROP TABLE IF EXISTS `snap`.`travels`;
CREATE TABLE `drivers` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'شناسه مخصوص راننده',
  `username` varchar(255) COLLATE utf8_persian_ci NOT NULL COMMENT 'نام کاربری',
  `password` varchar(255) COLLATE utf8_persian_ci NOT NULL COMMENT 'رمز عبور',
  `salary` int(10) NOT NULL DEFAULT '0' COMMENT 'درآمد',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) COMMENT 'نام کاربری یکتا برای هر راننده',
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
CREATE TABLE `passengers` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'شناسه مخضوض مسافر',
  `username` varchar(255) COLLATE utf8_persian_ci NOT NULL COMMENT 'نام کاربری',
  `password` varchar(255) COLLATE utf8_persian_ci NOT NULL COMMENT 'رمز عبور',
  `money` int(11) unsigned DEFAULT '0' COMMENT 'موجودی کاربر',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE COMMENT 'نام کاربری یکتا برای هر مسافر',
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
CREATE TABLE `travels` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'شناسه مخصوص سفر',
  `passenger_id` int(11) unsigned NOT NULL COMMENT 'شناسه مخصوص مسافر',
  `driver_id` int(11) unsigned DEFAULT NULL COMMENT 'شناسه مخصوص راننده',
  `status` varchar(30) COLLATE utf8_persian_ci NOT NULL DEFAULT 'wait' COMMENT 'وضعیت سفر',
  `point` int(255) unsigned NOT NULL DEFAULT '0' COMMENT 'امتیاز داده شده توسط کاربر',
  `cost` int(10) unsigned NOT NULL COMMENT 'هزینه(تومان)',
  `from_place_lltitude` decimal(20,18) unsigned NOT NULL COMMENT 'مختضات مبدا',
  `from_place_longitude` decimal(20,18) unsigned NOT NULL COMMENT 'مختصات مبدا',
  `to_place_lltitude` decimal(20,18) unsigned NOT NULL COMMENT 'مختصات مقصد',
  `to_place_longitude` decimal(20,18) unsigned NOT NULL COMMENT 'مختصات مقصد',
  PRIMARY KEY (`id`),
  KEY `passenger_id` (`passenger_id`),
  KEY `driver_id` (`driver_id`),
  CONSTRAINT `travels_ibfk_1` FOREIGN KEY (`passenger_id`) REFERENCES `passengers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `travels_ibfk_2` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6787 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
BEGIN;
LOCK TABLES `snap`.`drivers` WRITE;
DELETE FROM `snap`.`drivers`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `snap`.`passengers` WRITE;
DELETE FROM `snap`.`passengers`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `snap`.`travels` WRITE;
DELETE FROM `snap`.`travels`;
UNLOCK TABLES;
COMMIT;
