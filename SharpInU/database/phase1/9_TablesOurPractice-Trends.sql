--
-- Table structure for table `ourPracticeCategory`
--

DROP TABLE IF EXISTS `ourPracticeCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ourPracticeCategory` (
  `OURPRACTICECATEGORY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`OURPRACTICECATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ourPractice`
--

DROP TABLE IF EXISTS `ourPractice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ourPractice` (
  `OURPRACTICE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OURPRACTICECATEGORY_ID` int(11) NOT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `CONTENT` text NOT NULL,
  `SUMMARY` text,
  `CREATED_DATE` datetime NOT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`OURPRACTICE_ID`),
  KEY `OurPractice_OurPracticeCategory` (`OURPRACTICECATEGORY_ID`),
  KEY `OurPractice_User` (`USER_ID`),
  CONSTRAINT `OurPractice_OurPracticeCategory` FOREIGN KEY (`OURPRACTICECATEGORY_ID`) REFERENCES `ourPracticeCategory` (`OURPRACTICECATEGORY_ID`) ON UPDATE NO ACTION,
  CONSTRAINT `OurPractice_User` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `trend`
--

DROP TABLE IF EXISTS `trend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trend` (
  `TREND_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `CONTENT` text NOT NULL,
  `SUMMARY` text,
  `CREATED_DATE` datetime NOT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TREND_ID`),
  KEY `Trend_User` (`USER_ID`),
  CONSTRAINT `Trend_User` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;