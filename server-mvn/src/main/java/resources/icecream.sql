/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.27-MariaDB : Database - icecream
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`icecream` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `icecream`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `categoryID` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `category` */

insert  into `category`(`categoryID`,`name`) values 
(1,'aroma'),
(2,'vegetable oil'),
(3,'glucose'),
(4,'nuts'),
(5,'fruits'),
(6,'powder white'),
(7,'water');

/*Table structure for table `component` */

DROP TABLE IF EXISTS `component`;

CREATE TABLE `component` (
  `componentID` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `shortCode` varchar(10) NOT NULL,
  `producer` varchar(30) NOT NULL,
  `categoryID` bigint(10) NOT NULL,
  PRIMARY KEY (`componentID`),
  KEY `categoryID` (`categoryID`),
  CONSTRAINT `component_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `component` */

insert  into `component`(`componentID`,`name`,`shortCode`,`producer`,`categoryID`) values 
(1,'aroma vanila 352','VNL352','monini',1),
(2,'aroma jagoda 1556','jag1556','monini',1),
(3,'secer u prahu','sug','sunoko',6),
(5,'mleko u prahu','mleko','Imlek',6),
(6,'zamrznuta jagoda','jag','Frikom',5),
(7,'aroma banana','ABNA','Monini',1),
(8,'banana voce','FRTBN','Frikom',5),
(9,'lesnik','HZLN','/',4),
(11,'Borovnica','BLBR','Frikom',5);

/*Table structure for table `packaging` */

DROP TABLE IF EXISTS `packaging`;

CREATE TABLE `packaging` (
  `packagingID` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`packagingID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `packaging` */

insert  into `packaging`(`packagingID`,`name`) values 
(1,'family 1700ml'),
(2,'family 900ml'),
(3,'kornet 155ml'),
(4,'kornet 140ml'),
(5,'casica 155ml'),
(6,'stapic 60ml'),
(7,'stapic 100ml'),
(8,'biskvit 120ml');

/*Table structure for table `recipe` */

DROP TABLE IF EXISTS `recipe`;

CREATE TABLE `recipe` (
  `recipeID` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `shortCode` varchar(10) NOT NULL,
  `quantity` double NOT NULL,
  `comment` varchar(100) NOT NULL,
  `tehnologID` bigint(10) NOT NULL,
  `packagingID` bigint(10) NOT NULL,
  PRIMARY KEY (`recipeID`),
  KEY `tehnologID` (`tehnologID`),
  KEY `packagingID` (`packagingID`),
  CONSTRAINT `recipe_ibfk_1` FOREIGN KEY (`tehnologID`) REFERENCES `tehnolog` (`tehnologID`),
  CONSTRAINT `recipe_ibfk_2` FOREIGN KEY (`packagingID`) REFERENCES `packaging` (`packagingID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `recipe` */

insert  into `recipe`(`recipeID`,`name`,`shortCode`,`quantity`,`comment`,`tehnologID`,`packagingID`) values 
(1,'sladoled jagoda','SJFP',33,'sladoled sa ukusom jagode u velikom porodicnom pakovanju',1,1),
(6,'sladoled vanila','SLVNLMP',88,'sladoled od vanile u malom porodicnom pakovanju',1,2),
(7,'coko banana','CHCBN',100,'sladoled sa ukusom cokoladne bananice za mali stapic',1,6),
(8,'sladoled jagoda','SLJGMS',100,'sladoled sa ukusom jagode za mali stapic',1,6),
(9,'sladoled borovnica','SLBRVV',100,'sladoled sa ukusom borovnice veliki kornet',1,3),
(10,'recept1bb','aaabb',100,'a bbbb',1,7);

/*Table structure for table `recipeitem` */

DROP TABLE IF EXISTS `recipeitem`;

CREATE TABLE `recipeitem` (
  `itemID` bigint(10) NOT NULL AUTO_INCREMENT,
  `recipeID` bigint(10) NOT NULL,
  `quantity` double NOT NULL,
  `componentID` bigint(20) NOT NULL,
  PRIMARY KEY (`itemID`),
  KEY `recipeID` (`recipeID`),
  KEY `componentID` (`componentID`),
  CONSTRAINT `recipeitem_ibfk_1` FOREIGN KEY (`recipeID`) REFERENCES `recipe` (`recipeID`),
  CONSTRAINT `recipeitem_ibfk_2` FOREIGN KEY (`componentID`) REFERENCES `component` (`componentID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `recipeitem` */

insert  into `recipeitem`(`itemID`,`recipeID`,`quantity`,`componentID`) values 
(1,1,12,2),
(2,1,3,3),
(3,1,4,5),
(4,1,14,6),
(17,6,11,1),
(18,6,33,3),
(19,6,44,5),
(20,7,50,3),
(21,7,10,5),
(22,7,10,7),
(23,7,10,8),
(24,7,20,9),
(25,8,50,2),
(26,8,10,5),
(27,8,20,3),
(28,8,10,6),
(29,9,10,1),
(30,9,50,11),
(31,9,30,5),
(32,9,10,3),
(33,10,25,1),
(34,10,25,6),
(35,10,20,9),
(36,10,20,3);

/*Table structure for table `tehnolog` */

DROP TABLE IF EXISTS `tehnolog`;

CREATE TABLE `tehnolog` (
  `tehnologID` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`tehnologID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tehnolog` */

insert  into `tehnolog`(`tehnologID`,`name`,`surname`,`username`,`password`) values 
(1,'Vanja','Jankovic','vanjavanja','vanjaj'),
(2,'Marija','Maric','maramara','marmar');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
