CREATE TABLE IF NOT EXISTS `email_system` (
  `EMAIL_SYSTEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROTOCOL` varchar(20) DEFAULT NULL,
  `HOST` varchar(64) NOT NULL,
  `PORT` int(11) NOT NULL DEFAULT '0',
  `USER_NAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  `ACTIVE` char(1) DEFAULT 'Y',
  `IS_DEFAULT` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0: NOT-DEFAULT, 1: DEFAULT',
  `DATE_CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `SENDER_EMAIL` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`EMAIL_SYSTEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
