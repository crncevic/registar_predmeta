/*
SQLyog Community v12.5.1 (64 bit)
MySQL - 10.1.26-MariaDB : Database - registar_predmeta
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`registar_predmeta` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `registar_predmeta`;

/*Table structure for table `autor` */

DROP TABLE IF EXISTS `autor`;

CREATE TABLE `autor` (
  `autorId` int(11) NOT NULL AUTO_INCREMENT,
  `udzbenikId` int(11) NOT NULL,
  `ime` varchar(100) DEFAULT NULL,
  `prezime` varchar(100) DEFAULT NULL,
  `titula` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`autorId`,`udzbenikId`),
  KEY `udzbenikId` (`udzbenikId`),
  CONSTRAINT `autor_ibfk_1` FOREIGN KEY (`udzbenikId`) REFERENCES `udzbenik` (`udzbenikId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `autor` */

insert  into `autor`(`autorId`,`udzbenikId`,`ime`,`prezime`,`titula`) values 
(1,16,'Gordana','Jakic','docent'),
(2,16,'Jelena','Adjelkovic','Adjelkovic');

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `korisnikId` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(100) DEFAULT NULL,
  `prezime` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`korisnikId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `korisnik` */

insert  into `korisnik`(`korisnikId`,`ime`,`prezime`,`username`,`password`) values 
(1,'petar','crncevic','petar','petar');

/*Table structure for table `recenzent` */

DROP TABLE IF EXISTS `recenzent`;

CREATE TABLE `recenzent` (
  `recenzentId` int(11) NOT NULL AUTO_INCREMENT,
  `udzbenikId` int(11) NOT NULL,
  `ime` varchar(100) DEFAULT NULL,
  `prezime` varchar(100) DEFAULT NULL,
  `titula` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`recenzentId`,`udzbenikId`),
  KEY `udzbenikId` (`udzbenikId`),
  CONSTRAINT `recenzent_ibfk_1` FOREIGN KEY (`udzbenikId`) REFERENCES `udzbenik` (`udzbenikId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `recenzent` */

/*Table structure for table `udzbenik` */

DROP TABLE IF EXISTS `udzbenik`;

CREATE TABLE `udzbenik` (
  `udzbenikId` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  `godina_izdanja` int(11) DEFAULT NULL,
  `izdavac` varchar(100) DEFAULT NULL,
  `stampa` varchar(200) DEFAULT NULL,
  `rbr_izdanja` int(11) DEFAULT NULL,
  `tiraz` int(11) DEFAULT NULL,
  `isbn` int(11) DEFAULT NULL,
  PRIMARY KEY (`udzbenikId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `udzbenik` */

insert  into `udzbenik`(`udzbenikId`,`naziv`,`godina_izdanja`,`izdavac`,`stampa`,`rbr_izdanja`,`tiraz`,`isbn`) values 
(1,'Programski jezici',2018,'Milija [FON]',NULL,NULL,NULL,NULL),
(2,'FON',2018,'BAze',NULL,NULL,NULL,NULL),
(3,'Matika 3',2018,'Milija[FON]',NULL,NULL,NULL,NULL),
(5,'Mata3',2018,'Nebojsa Nikolic',NULL,NULL,NULL,NULL),
(6,'Mata 4',2018,'FON',NULL,NULL,NULL,NULL),
(7,'af',2018,'asd',NULL,NULL,NULL,NULL),
(8,'Strukture podataka i algoritmi',2018,'FON',NULL,NULL,NULL,NULL),
(10,'Novi',2018,'fon',NULL,NULL,NULL,NULL),
(11,'M3',2018,'','',0,0,0),
(12,'12',2018,'','',0,0,0),
(16,'Engleski 1',2018,'FON','Newspress',2,500,3232546);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
