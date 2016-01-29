-- --------------------------------------------------------

--
-- Table structure for table `setting_group`
--

CREATE TABLE IF NOT EXISTS `setting_group` (
  `SETTING_GROUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SETTING_GROUP_NAME` varchar(255) NOT NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_USER_ID` int(11) DEFAULT NULL,
  `UPDATED_USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SETTING_GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `setting`
--

CREATE TABLE IF NOT EXISTS `setting` (
  `SETTING_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SETTING_NAME` varchar(255) NOT NULL,
  `SETTING_VALUE` varchar(10000) NOT NULL,
  `SETTING_TYPE` TINYINT(1) DEFAULT 0 COMMENT 'GLOBAL = 0, USER = 1',
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_USER_ID` int(11) DEFAULT NULL,
  `UPDATED_USER_ID` int(11) DEFAULT NULL,
  `SETTING_GROUP_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SETTING_ID`),
  KEY `FK_S_CREATED_USER_ID` (`CREATED_USER_ID`),
  KEY `FK_S_UPDATED_USER_ID` (`UPDATED_USER_ID`),
  KEY `FK_S_SETTING_GROUP_ID` (`SETTING_GROUP_ID`),
  CONSTRAINT `FK_S_CREATED_USER_ID` FOREIGN KEY (`CREATED_USER_ID`) REFERENCES `user` (`USER_ID`),
  CONSTRAINT `FK_S_UPDATED_USER_ID` FOREIGN KEY (`UPDATED_USER_ID`) REFERENCES `user` (`USER_ID`),
  CONSTRAINT `FK_S_SETTING_GROUP_ID` FOREIGN KEY (`SETTING_GROUP_ID`) REFERENCES `setting_group` (`SETTING_GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;