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

/*Table structure for table `katedra` */

DROP TABLE IF EXISTS `katedra`;

CREATE TABLE `katedra` (
  `katedraId` int(11) NOT NULL,
  `naziv` varchar(100) NOT NULL,
  `sef` int(11) DEFAULT NULL,
  `zamenikSefa` int(11) DEFAULT NULL,
  `sekretar` int(11) DEFAULT NULL,
  PRIMARY KEY (`katedraId`),
  KEY `sef` (`sef`),
  KEY `zamenikSefa` (`zamenikSefa`),
  KEY `sekretar` (`sekretar`),
  CONSTRAINT `katedra_ibfk_1` FOREIGN KEY (`sef`) REFERENCES `nastavnik` (`nastavnikId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `katedra_ibfk_2` FOREIGN KEY (`zamenikSefa`) REFERENCES `nastavnik` (`nastavnikId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `katedra_ibfk_3` FOREIGN KEY (`sekretar`) REFERENCES `nastavnik` (`nastavnikId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `katedra` */

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `korisnikId` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(100) NOT NULL,
  `prezime` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `ulogaId` int(11) DEFAULT NULL,
  PRIMARY KEY (`korisnikId`),
  KEY `ulogaId` (`ulogaId`),
  CONSTRAINT `korisnik_ibfk_2` FOREIGN KEY (`ulogaId`) REFERENCES `uloga` (`ulogaId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `korisnik` */

insert  into `korisnik`(`korisnikId`,`ime`,`prezime`,`username`,`password`,`ulogaId`) values 
(1,'petar','crncevic','petar','petar',NULL);

/*Table structure for table `nastavnik` */

DROP TABLE IF EXISTS `nastavnik`;

CREATE TABLE `nastavnik` (
  `nastavnikId` int(11) NOT NULL,
  `katedraId` int(11) DEFAULT NULL,
  `ime` varchar(100) NOT NULL,
  `prezime` varchar(100) NOT NULL,
  `zvanje` varchar(100) NOT NULL,
  `telefon` varchar(100) DEFAULT NULL,
  `kabinet` varchar(50) DEFAULT NULL,
  `ePosta` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`nastavnikId`),
  KEY `katedraId` (`katedraId`),
  CONSTRAINT `nastavnik_ibfk_1` FOREIGN KEY (`katedraId`) REFERENCES `katedra` (`katedraId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `nastavnik` */

insert  into `nastavnik`(`nastavnikId`,`katedraId`,`ime`,`prezime`,`zvanje`,`telefon`,`kabinet`,`ePosta`) values 
(1,NULL,'Marko','Petrovic','docent','5553333','210a','petrovicmarko@fon.bg.ac.rs');

/*Table structure for table `nastavnik_na_predmetu` */

DROP TABLE IF EXISTS `nastavnik_na_predmetu`;

CREATE TABLE `nastavnik_na_predmetu` (
  `nastavnikId` int(11) NOT NULL,
  `predmetId` int(11) NOT NULL,
  `tipNastaveId` int(11) NOT NULL,
  PRIMARY KEY (`nastavnikId`,`predmetId`,`tipNastaveId`),
  KEY `predmetId` (`predmetId`),
  KEY `tipNastaveId` (`tipNastaveId`),
  CONSTRAINT `nastavnik_na_predmetu_ibfk_1` FOREIGN KEY (`nastavnikId`) REFERENCES `nastavnik` (`nastavnikId`),
  CONSTRAINT `nastavnik_na_predmetu_ibfk_2` FOREIGN KEY (`predmetId`) REFERENCES `predmet` (`predmetId`),
  CONSTRAINT `nastavnik_na_predmetu_ibfk_3` FOREIGN KEY (`tipNastaveId`) REFERENCES `tip_nastave` (`tip_nastaveId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `nastavnik_na_predmetu` */

insert  into `nastavnik_na_predmetu`(`nastavnikId`,`predmetId`,`tipNastaveId`) values 
(1,444,1);

/*Table structure for table `obaveza` */

DROP TABLE IF EXISTS `obaveza`;

CREATE TABLE `obaveza` (
  `obavezaId` int(11) NOT NULL,
  `predmetId` int(11) DEFAULT NULL,
  `tip` varchar(100) DEFAULT NULL,
  `naziv` varchar(100) NOT NULL,
  `broj_poena` int(11) NOT NULL,
  PRIMARY KEY (`obavezaId`),
  KEY `predmetId` (`predmetId`),
  CONSTRAINT `obaveza_ibfk_1` FOREIGN KEY (`predmetId`) REFERENCES `predmet` (`predmetId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `obaveza` */

/*Table structure for table `osoba_u_vezi_sa_udzbenikom` */

DROP TABLE IF EXISTS `osoba_u_vezi_sa_udzbenikom`;

CREATE TABLE `osoba_u_vezi_sa_udzbenikom` (
  `osobaId` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(100) NOT NULL,
  `prezime` varchar(100) NOT NULL,
  `titula` varchar(100) DEFAULT NULL,
  `ulogaId` int(11) DEFAULT NULL,
  `udzbenikId` int(11) DEFAULT NULL,
  PRIMARY KEY (`osobaId`),
  KEY `ulogaId` (`ulogaId`),
  KEY `udzbenikId` (`udzbenikId`),
  CONSTRAINT `osoba_u_vezi_sa_udzbenikom_ibfk_1` FOREIGN KEY (`ulogaId`) REFERENCES `uloga_udzbenik` (`ulogaId`),
  CONSTRAINT `osoba_u_vezi_sa_udzbenikom_ibfk_2` FOREIGN KEY (`udzbenikId`) REFERENCES `udzbenik` (`udzbenikId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `osoba_u_vezi_sa_udzbenikom` */

insert  into `osoba_u_vezi_sa_udzbenikom`(`osobaId`,`ime`,`prezime`,`titula`,`ulogaId`,`udzbenikId`) values 
(1,'Gordana','Jakic','docent',NULL,NULL),
(2,'Jelena','Adjelkovic','Adjelkovic',NULL,NULL),
(6,'Petar','Crncevic','saradnik u nastavi',1,23),
(7,'Petar','Crncevic','saradnik u nastavi',1,23);

/*Table structure for table `predmet` */

DROP TABLE IF EXISTS `predmet`;

CREATE TABLE `predmet` (
  `predmetId` int(11) NOT NULL,
  `naziv` varchar(200) NOT NULL,
  `br_casova_predavanja_nedeljno` int(11) DEFAULT NULL,
  `br_casova_vezbi_nedeljno` int(11) DEFAULT NULL,
  `ostali_casovi` int(11) DEFAULT NULL,
  `drugi_oblici_nastave` varchar(200) DEFAULT NULL,
  `studijski_istrazivacki_rad` varchar(300) DEFAULT NULL,
  `cilj` varchar(500) DEFAULT NULL,
  `ishod` varchar(500) DEFAULT NULL,
  `uslov` varchar(250) DEFAULT NULL,
  `vrsta_i_nivo_studija` int(11) DEFAULT NULL,
  `sadrzaj_tekst` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`predmetId`),
  KEY `predmet_ibfk_1` (`vrsta_i_nivo_studija`),
  CONSTRAINT `predmet_ibfk_1` FOREIGN KEY (`vrsta_i_nivo_studija`) REFERENCES `vrsta_i_nivo_studija` (`vrstaId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `predmet` */

insert  into `predmet`(`predmetId`,`naziv`,`br_casova_predavanja_nedeljno`,`br_casova_vezbi_nedeljno`,`ostali_casovi`,`drugi_oblici_nastave`,`studijski_istrazivacki_rad`,`cilj`,`ishod`,`uslov`,`vrsta_i_nivo_studija`,`sadrzaj_tekst`) values 
(444,'strukture podataka',2,2,0,'','','da','da','da',2,'');

/*Table structure for table `predmet_na_studijskom_programu` */

DROP TABLE IF EXISTS `predmet_na_studijskom_programu`;

CREATE TABLE `predmet_na_studijskom_programu` (
  `predmetId` int(11) NOT NULL,
  `studijski_programId` int(11) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `espb` int(2) DEFAULT NULL,
  PRIMARY KEY (`predmetId`),
  KEY `studijski_programId` (`studijski_programId`),
  CONSTRAINT `predmet_na_studijskom_programu_ibfk_1` FOREIGN KEY (`predmetId`) REFERENCES `predmet` (`predmetId`),
  CONSTRAINT `predmet_na_studijskom_programu_ibfk_2` FOREIGN KEY (`studijski_programId`) REFERENCES `studijski_program` (`studijskiProgramId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `predmet_na_studijskom_programu` */

/*Table structure for table `studijski_program` */

DROP TABLE IF EXISTS `studijski_program`;

CREATE TABLE `studijski_program` (
  `studijskiProgramId` int(11) NOT NULL,
  `naziv` varchar(150) NOT NULL,
  PRIMARY KEY (`studijskiProgramId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `studijski_program` */

/*Table structure for table `tematska_celina` */

DROP TABLE IF EXISTS `tematska_celina`;

CREATE TABLE `tematska_celina` (
  `tematska_celinaId` int(11) NOT NULL AUTO_INCREMENT,
  `predmetId` int(11) DEFAULT NULL,
  `tip_nastaveId` int(11) DEFAULT NULL,
  `nadredjena_tematska_celinaId` int(11) DEFAULT NULL,
  `naziv` varchar(100) NOT NULL,
  `opis` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`tematska_celinaId`),
  KEY `predmetId` (`predmetId`),
  KEY `tip_nastaveId` (`tip_nastaveId`),
  KEY `nadredjena_tematska_celinaId` (`nadredjena_tematska_celinaId`),
  CONSTRAINT `tematska_celina_ibfk_1` FOREIGN KEY (`predmetId`) REFERENCES `predmet` (`predmetId`),
  CONSTRAINT `tematska_celina_ibfk_2` FOREIGN KEY (`tip_nastaveId`) REFERENCES `tip_nastave` (`tip_nastaveId`),
  CONSTRAINT `tematska_celina_ibfk_3` FOREIGN KEY (`nadredjena_tematska_celinaId`) REFERENCES `tematska_celina` (`tematska_celinaId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tematska_celina` */

/*Table structure for table `tip_nastave` */

DROP TABLE IF EXISTS `tip_nastave`;

CREATE TABLE `tip_nastave` (
  `tip_nastaveId` int(11) NOT NULL,
  `naziv` varchar(200) NOT NULL,
  PRIMARY KEY (`tip_nastaveId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tip_nastave` */

insert  into `tip_nastave`(`tip_nastaveId`,`naziv`) values 
(1,'predavanja'),
(2,'vezbe'),
(3,'laboratorijske vezbe');

/*Table structure for table `udzbenik` */

DROP TABLE IF EXISTS `udzbenik`;

CREATE TABLE `udzbenik` (
  `udzbenikId` int(11) NOT NULL AUTO_INCREMENT,
  `predmetId` int(11) DEFAULT NULL,
  `naziv` varchar(100) NOT NULL,
  `godina_izdanja` int(11) DEFAULT NULL,
  `izdavac` varchar(100) DEFAULT NULL,
  `stampa` varchar(200) DEFAULT NULL,
  `rbr_izdanja` int(11) DEFAULT NULL,
  `tiraz` int(11) DEFAULT NULL,
  `isbn` int(11) DEFAULT NULL,
  PRIMARY KEY (`udzbenikId`),
  KEY `predmetId` (`predmetId`),
  CONSTRAINT `udzbenik_ibfk_1` FOREIGN KEY (`predmetId`) REFERENCES `predmet` (`predmetId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

/*Data for the table `udzbenik` */

insert  into `udzbenik`(`udzbenikId`,`predmetId`,`naziv`,`godina_izdanja`,`izdavac`,`stampa`,`rbr_izdanja`,`tiraz`,`isbn`) values 
(1,NULL,'Programski jezici',2018,'Milija [FON]','',0,0,0),
(2,NULL,'FON',2018,'BAze',NULL,NULL,NULL,NULL),
(3,NULL,'Matika 3',2018,'Milija[FON]',NULL,NULL,NULL,NULL),
(5,NULL,'Mata3',2018,'Nebojsa Nikolic',NULL,NULL,NULL,NULL),
(6,NULL,'Mata 4',2018,'FON',NULL,NULL,NULL,NULL),
(7,NULL,'af',2018,'asd',NULL,NULL,NULL,NULL),
(8,NULL,'Strukture podataka i algoritmi',2018,'FON',NULL,NULL,NULL,NULL),
(11,NULL,'M3',2018,'','',0,0,0),
(12,NULL,'12',2018,'','',0,0,0),
(16,NULL,'Engleski 1',2018,'FON','Newspress',2,500,3232546),
(18,NULL,'kreiraj',2018,'','',0,0,0),
(19,NULL,'kreiraj2',2018,'','',0,0,0),
(23,NULL,'analiza podataka',2018,'fon','newspress Smederevo',1,12000,245242323);

/*Table structure for table `udzbenik_na_predmetu` */

DROP TABLE IF EXISTS `udzbenik_na_predmetu`;

CREATE TABLE `udzbenik_na_predmetu` (
  `udzbenikId` int(11) NOT NULL,
  `predmetId` int(11) NOT NULL,
  PRIMARY KEY (`udzbenikId`,`predmetId`),
  KEY `predmetId` (`predmetId`),
  CONSTRAINT `udzbenik_na_predmetu_ibfk_1` FOREIGN KEY (`udzbenikId`) REFERENCES `udzbenik` (`udzbenikId`),
  CONSTRAINT `udzbenik_na_predmetu_ibfk_2` FOREIGN KEY (`predmetId`) REFERENCES `predmet` (`predmetId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `udzbenik_na_predmetu` */

insert  into `udzbenik_na_predmetu`(`udzbenikId`,`predmetId`) values 
(1,444);

/*Table structure for table `uloga` */

DROP TABLE IF EXISTS `uloga`;

CREATE TABLE `uloga` (
  `ulogaId` int(11) NOT NULL,
  `naziv` varchar(100) NOT NULL,
  PRIMARY KEY (`ulogaId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `uloga` */

/*Table structure for table `uloga_udzbenik` */

DROP TABLE IF EXISTS `uloga_udzbenik`;

CREATE TABLE `uloga_udzbenik` (
  `ulogaId` int(11) NOT NULL,
  `naziv` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ulogaId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `uloga_udzbenik` */

insert  into `uloga_udzbenik`(`ulogaId`,`naziv`) values 
(1,'autor'),
(2,'recenzent');

/*Table structure for table `vrsta_i_nivo_studija` */

DROP TABLE IF EXISTS `vrsta_i_nivo_studija`;

CREATE TABLE `vrsta_i_nivo_studija` (
  `vrstaId` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`vrstaId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `vrsta_i_nivo_studija` */

insert  into `vrsta_i_nivo_studija`(`vrstaId`,`naziv`) values 
(1,'osnovne studija'),
(2,'master studije'),
(3,'doktorske studije');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
