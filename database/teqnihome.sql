# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.17)
# Database: teqnihome
# Generation Time: 2017-01-04 11:28:21 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table USER
# ------------------------------------------------------------

DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER` (
  `USERNAME` varchar(255) NOT NULL,
  `DOB` date NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `FIRST_NAME` varchar(255) NOT NULL,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `ROLE` bigint(20) NOT NULL,
  `IS_ACCOUNT_VERIFIED` bit(1) NOT NULL,
  PRIMARY KEY (`USERNAME`),
  UNIQUE KEY `UK_ejfk3g58oxsgbb4ju3u4fhivk` (`EMAIL`),
  KEY `FK_9cnarn4mvo0wdgqc2xnek4ais` (`ROLE`),
  CONSTRAINT `FK_9cnarn4mvo0wdgqc2xnek4ais` FOREIGN KEY (`ROLE`) REFERENCES `USER_ROLE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table USER_ROLE
# ------------------------------------------------------------

DROP TABLE IF EXISTS `USER_ROLE`;

CREATE TABLE `USER_ROLE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_iqf75fygxt0upw06w483dpwnu` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `USER_ROLE` WRITE;
/*!40000 ALTER TABLE `USER_ROLE` DISABLE KEYS */;

INSERT INTO `USER_ROLE` (`ID`, `NAME`)
VALUES
	(1,'ADMIN'),
	(3,'OBSERVER'),
	(2,'USER');

/*!40000 ALTER TABLE `USER_ROLE` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;