CREATE DATABASE  IF NOT EXISTS `cancionesActualizadas` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `canciones`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: canciones
-- ------------------------------------------------------
-- Server version	5.7.7-rc-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `canciones`
--

DROP TABLE IF EXISTS `canciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `canciones` (
  `idCancion` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCancion` varchar(45) DEFAULT NULL,
  `duracion` varchar(8) DEFAULT NULL,
  `idArtista` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCancion`),
  KEY `fk1_idx` (`idArtista`),
  CONSTRAINT `fk1` FOREIGN KEY (`idArtista`) REFERENCES `artistas` (`idArtista`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `canciones`
--

LOCK TABLES `canciones` WRITE;
/*!40000 ALTER TABLE `canciones` DISABLE KEYS */;
INSERT INTO `canciones` VALUES (1,'Cancion1','03:25',1),(2,'Cancion2','04:50',2);
/*!40000 ALTER TABLE `canciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estilos`
--

DROP TABLE IF EXISTS `estilos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estilos` (
  `idEstilo` int(11) NOT NULL AUTO_INCREMENT,
  `estilo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idEstilo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estilos`
--

LOCK TABLES `estilos` WRITE;
/*!40000 ALTER TABLE `estilos` DISABLE KEYS */;
INSERT INTO `estilos` VALUES (1,'Rock'),(2,'Jazz'),(3,'Samba'),(4,'Bossa Nova'),(5,'Romantica');
/*!40000 ALTER TABLE `estilos` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `paises`
--

DROP TABLE IF EXISTS `paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paises` (
  `idPais` int(11) NOT NULL AUTO_INCREMENT,
  `pais` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPais`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paises`
--

LOCK TABLES `paises` WRITE;
/*!40000 ALTER TABLE `paises` DISABLE KEYS */;
INSERT INTO `paises` VALUES (1,'Brasil'),(2,'España'),(3,'Inglaterra'),(4,'USA'),(5,'France');
/*!40000 ALTER TABLE `paises` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `artistas`
--

DROP TABLE IF EXISTS `artistas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artistas` (
  `idArtista` int(11) NOT NULL AUTO_INCREMENT,
  `nombreArtista` varchar(45) DEFAULT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `idEstilo` int(11) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `idPais` int(11) DEFAULT NULL,
  PRIMARY KEY (`idArtista`),
  KEY `fk2_idx` (`idEstilo`),
  KEY `fk3_idx` (`idPais`),
  CONSTRAINT `fk2` FOREIGN KEY (`idEstilo`) REFERENCES `estilos` (`idEstilo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk3` FOREIGN KEY (`idPais`) REFERENCES `paises` (`idPais`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artistas`
--

LOCK TABLES `artistas` WRITE;
/*!40000 ALTER TABLE `artistas` DISABLE KEYS */;
INSERT INTO `artistas` VALUES (1,'Eminem','Martin',1,36,1),(2,'DMX','Julius',2,34,2);
/*!40000 ALTER TABLE `artistas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'canciones'
--

--
-- Dumping routines for database 'canciones'
--
/*!50003 DROP PROCEDURE IF EXISTS `mostrar_usuarios_acceso` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostrar_artistas`(IN TIPO INT)
BEGIN
	SELECT * FROM ARTISTAS WHERE idArtista = TIPO;
END ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostrar_artistas_estilo`(IN ESTILO INT)
BEGIN
	SELECT * FROM ARTISTAS WHERE idEstilo = ESTILO;
END ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostrar_artistasYcanciones`(IN ARTISTAS int, IN CANCIONES INT)
BEGIN
select * from artistas a join canciones c on (a.idArtista = c.idArtista) group by a.idArtista, c.idCancion order by a.nombreArtista, c.idCancion;
END;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostrar_canciones_artista`(IN ARTISTA INT)
BEGIN
	SELECT * FROM CANCIONES WHERE idArtista = ARTISTA;
END;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostrar_canciones_Pais`(IN PAIS INT)
BEGIN
select * from canciones
left join artistas on (canciones.idArtista = artistas.idArtista) where artistas.idPais = PAIS;
END ;;

DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-29  10:27:06
