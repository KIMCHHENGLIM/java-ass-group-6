/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.40-log : Database - airline_database_mgt
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`airline_database_mgt` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `airline_database_mgt`;

/*Table structure for table `tbairline` */

DROP TABLE IF EXISTS `tbairline`;

CREATE TABLE `tbairline` (
  `airlineCode` varchar(45) NOT NULL,
  `airlineName` varchar(45) DEFAULT NULL,
  `aircraftModel` varchar(45) DEFAULT NULL,
  `firstClass` int(11) DEFAULT NULL,
  `businessClass` int(11) DEFAULT NULL,
  `economyClass` int(11) DEFAULT NULL,
  PRIMARY KEY (`airlineCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbairline` */

insert  into `tbairline`(`airlineCode`,`airlineName`,`aircraftModel`,`firstClass`,`businessClass`,`economyClass`) values ('AG','Angkor Air','dak',10,20,30),('EM','Emarite','Big',20,50,80),('JC','JC Airline','Small',0,10,30),('QA','Qotar','Boeing',1,10,30),('SA','Sarika','Boeing 787 Dreamliner',10,30,90);

/*Table structure for table `tbflight` */

DROP TABLE IF EXISTS `tbflight`;

CREATE TABLE `tbflight` (
  `flightCode` varchar(45) NOT NULL,
  `flightNumber` int(11) DEFAULT NULL,
  `flightStatus` char(1) DEFAULT NULL,
  `flightType` char(1) DEFAULT NULL,
  `departureDayOfWeek` char(1) DEFAULT NULL,
  `departureTime` int(11) DEFAULT NULL,
  `departureAirportCode` varchar(45) DEFAULT NULL,
  `departureAirportGate` varchar(45) DEFAULT NULL,
  `arrivalDayOfWeek` char(1) DEFAULT NULL,
  `arrivalTime` int(11) DEFAULT NULL,
  `arrivalAirportCode` varchar(45) DEFAULT NULL,
  `arrivalAirportGate` varchar(45) DEFAULT NULL,
  `airlineCode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`flightCode`),
  KEY `FK1AirlineCode` (`airlineCode`),
  CONSTRAINT `FK1AirlineCode` FOREIGN KEY (`airlineCode`) REFERENCES `tbairline` (`airlineCode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbflight` */

insert  into `tbflight`(`flightCode`,`flightNumber`,`flightStatus`,`flightType`,`departureDayOfWeek`,`departureTime`,`departureAirportCode`,`departureAirportGate`,`arrivalDayOfWeek`,`arrivalTime`,`arrivalAirportCode`,`arrivalAirportGate`,`airlineCode`) values ('SA101',101,'S','D','T',2000,'SR','2','T',2120,'PNH','3','SA'),('SA555',555,'S','D','T',1800,'SR','2','T',1900,'PNH','2','SA'),('SA777',777,'D','I','T',630,'PNH','2','T',804,'SR','2','SA'),('SA888',888,'S','D','T',1900,'SR','2','T',2120,'PNH','2','SA');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
