/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 10.4.25-MariaDB : Database - seminarski_baza
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`seminarski_baza` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `seminarski_baza`;

/*Table structure for table `inli` */

DROP TABLE IF EXISTS `inli`;

CREATE TABLE `inli` (
  `idInstruktor` bigint(20) NOT NULL,
  `idLicenca` bigint(20) NOT NULL,
  `datumIzdavanja` date NOT NULL,
  `datumIsteka` date NOT NULL,
  PRIMARY KEY (`idInstruktor`,`idLicenca`),
  KEY `idLicenca` (`idLicenca`),
  CONSTRAINT `inli_ibfk_1` FOREIGN KEY (`idInstruktor`) REFERENCES `instruktor` (`idInstruktor`),
  CONSTRAINT `inli_ibfk_2` FOREIGN KEY (`idLicenca`) REFERENCES `licenca` (`idLicenca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `inli` */

insert  into `inli`(`idInstruktor`,`idLicenca`,`datumIzdavanja`,`datumIsteka`) values 
(1,1,'2026-03-10','2031-03-10');

/*Table structure for table `instruktor` */

DROP TABLE IF EXISTS `instruktor`;

CREATE TABLE `instruktor` (
  `idInstruktor` bigint(20) NOT NULL AUTO_INCREMENT,
  `imePrezimeInstruktora` varchar(50) NOT NULL,
  `korisnickoIme` varchar(50) NOT NULL,
  `sifra` varchar(50) NOT NULL,
  `brojTelefona` varchar(50) NOT NULL,
  `datumZaposlenja` date NOT NULL,
  PRIMARY KEY (`idInstruktor`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `instruktor` */

insert  into `instruktor`(`idInstruktor`,`imePrezimeInstruktora`,`korisnickoIme`,`sifra`,`brojTelefona`,`datumZaposlenja`) values 
(1,'Rade Kosmajac','Radenko','Radenko1!','0654579630','2026-02-28');

/*Table structure for table `kategorija` */

DROP TABLE IF EXISTS `kategorija`;

CREATE TABLE `kategorija` (
  `idKategorija` bigint(20) NOT NULL AUTO_INCREMENT,
  `nazivKategorije` varchar(50) NOT NULL,
  `opisKategorije` varchar(50) NOT NULL,
  PRIMARY KEY (`idKategorija`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `kategorija` */

insert  into `kategorija`(`idKategorija`,`nazivKategorije`,`opisKategorije`) values 
(1,'Vatrogasac-pripravnik','Lice na osnovnoj obuci za rad u jedinici.'),
(2,'Vatrogasac I klase','Iskusni operativac sa položenim ispitom.'),
(3,'Vatrogasac-spasilac','Specijalista za rad u ekstremnim uslovima.');

/*Table structure for table `licenca` */

DROP TABLE IF EXISTS `licenca`;

CREATE TABLE `licenca` (
  `idLicenca` bigint(20) NOT NULL AUTO_INCREMENT,
  `nazivLicence` varchar(50) NOT NULL,
  PRIMARY KEY (`idLicenca`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `licenca` */

insert  into `licenca`(`idLicenca`,`nazivLicence`) values 
(1,'Hazmat'),
(2,'Defib'),
(3,'SSWF');

/*Table structure for table `polaznik` */

DROP TABLE IF EXISTS `polaznik`;

CREATE TABLE `polaznik` (
  `idPolaznik` bigint(20) NOT NULL AUTO_INCREMENT,
  `imePrezimePolaznika` varchar(50) NOT NULL,
  `jmbgPolaznika` varchar(50) NOT NULL,
  `datumRodjenjaPolaznika` date NOT NULL,
  `idKategorija` bigint(20) NOT NULL,
  PRIMARY KEY (`idPolaznik`),
  KEY `idKategorija` (`idKategorija`),
  CONSTRAINT `polaznik_ibfk_1` FOREIGN KEY (`idKategorija`) REFERENCES `kategorija` (`idKategorija`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

/*Data for the table `polaznik` */

insert  into `polaznik`(`idPolaznik`,`imePrezimePolaznika`,`jmbgPolaznika`,`datumRodjenjaPolaznika`,`idKategorija`) values 
(1,'Mile Kitic','0101004710005','2004-01-01',3),
(5,'Uros Zivkovic','1505004710271','2004-05-15',2),
(6,'Marko Sokolovic','2010003710156','2003-01-20',3),
(10,'Barbara Bobak','0707004715014','2004-07-07',1),
(19,'Barbara Bobak','0707004715014','2004-07-07',1),
(21,'Luka Spanovic','0202005710040','2005-02-02',1),
(22,'Luak','0202005710040','2005-02-07',2),
(23,'kjhgfd','0202005710040','2005-02-02',1);

/*Table structure for table `stavkazapisnika` */

DROP TABLE IF EXISTS `stavkazapisnika`;

CREATE TABLE `stavkazapisnika` (
  `idZapisnik` bigint(20) NOT NULL,
  `rb` bigint(20) NOT NULL AUTO_INCREMENT,
  `tekst` varchar(200) NOT NULL,
  `trajanjeStavke` int(11) NOT NULL,
  `idUsluga` bigint(20) NOT NULL,
  PRIMARY KEY (`idZapisnik`,`rb`),
  KEY `rb` (`rb`),
  KEY `idUsluga` (`idUsluga`),
  CONSTRAINT `stavkazapisnika_ibfk_1` FOREIGN KEY (`idZapisnik`) REFERENCES `zapisnik` (`idZapisnik`),
  CONSTRAINT `stavkazapisnika_ibfk_2` FOREIGN KEY (`idUsluga`) REFERENCES `usluga` (`idUsluga`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `stavkazapisnika` */

insert  into `stavkazapisnika`(`idZapisnik`,`rb`,`tekst`,`trajanjeStavke`,`idUsluga`) values 
(1,1,'lala',20,1),
(2,2,'gggggg',35,1);

/*Table structure for table `usluga` */

DROP TABLE IF EXISTS `usluga`;

CREATE TABLE `usluga` (
  `idUsluga` bigint(20) NOT NULL AUTO_INCREMENT,
  `opisUsluge` varchar(50) NOT NULL,
  `trajanjeUsluge` int(11) NOT NULL,
  `cenaUsluge` double NOT NULL,
  PRIMARY KEY (`idUsluga`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `usluga` */

insert  into `usluga`(`idUsluga`,`opisUsluge`,`trajanjeUsluge`,`cenaUsluge`) values 
(1,'Hazmat',300,12000);

/*Table structure for table `zapisnik` */

DROP TABLE IF EXISTS `zapisnik`;

CREATE TABLE `zapisnik` (
  `idZapisnik` bigint(20) NOT NULL AUTO_INCREMENT,
  `datumEvidentiranja` date NOT NULL,
  `tekst` varchar(200) NOT NULL,
  `ukupnoTrajanje` int(11) NOT NULL,
  `idInstruktor` bigint(20) NOT NULL,
  `idPolaznik` bigint(20) NOT NULL,
  PRIMARY KEY (`idZapisnik`),
  KEY `idInstruktor` (`idInstruktor`),
  KEY `idPolaznik` (`idPolaznik`),
  CONSTRAINT `zapisnik_ibfk_1` FOREIGN KEY (`idInstruktor`) REFERENCES `instruktor` (`idInstruktor`),
  CONSTRAINT `zapisnik_ibfk_2` FOREIGN KEY (`idPolaznik`) REFERENCES `polaznik` (`idPolaznik`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `zapisnik` */

insert  into `zapisnik`(`idZapisnik`,`datumEvidentiranja`,`tekst`,`ukupnoTrajanje`,`idInstruktor`,`idPolaznik`) values 
(1,'2026-03-03','lalalala',50,1,5),
(2,'2023-02-02','hhhhhhh',40,1,21);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
