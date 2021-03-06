SET @ADMIN_USER_ID = (SELECT `USER_ID` from `user` WHERE `user_email` LIKE 'sharpinu.tester@gmail.com');

DROP TABLE IF EXISTS setting_group_temp;
CREATE TABLE  setting_group_temp (
	`SETTING_GROUP_ID` int(11) NOT NULL AUTO_INCREMENT,
	`SETTING_GROUP_NAME` varchar(256),
	`CREATED_USER_ID` int(11) DEFAULT NULL,
	`UPDATED_USER_ID` int(11) DEFAULT NULL,
	 PRIMARY KEY (`SETTING_GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO setting_group_temp(`SETTING_GROUP_NAME`, `CREATED_USER_ID`, `UPDATED_USER_ID`) VALUE ('Footer Menu', @ADMIN_USER_ID, @ADMIN_USER_ID);

INSERT INTO setting_group (`SETTING_GROUP_NAME`, `CREATED_USER_ID`, `UPDATED_USER_ID`)
	SELECT `SETTING_GROUP_NAME`, `CREATED_USER_ID`, `UPDATED_USER_ID` FROM setting_group_temp
	WHERE `SETTING_GROUP_NAME` NOT IN (SELECT `SETTING_GROUP_NAME` FROM setting_group);

DROP TABLE IF EXISTS setting_temp;
CREATE TABLE  setting_temp (
	`SETTING_ID` int(11) NOT NULL AUTO_INCREMENT,
	`SETTING_NAME` varchar(256),
	`SETTING_VALUE` VARCHAR(1000),
	`SETTING_TYPE` TINYINT(1) DEFAULT 0,
	`CREATED_USER_ID` int(11) DEFAULT NULL,
	`UPDATED_USER_ID` int(11) DEFAULT NULL,
	`SETTING_GROUP_ID` int(11) DEFAULT NULL,
	 PRIMARY KEY (`SETTING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET @FOOTER_MENU_GROUP_ID = (SELECT `SETTING_GROUP_ID` from `setting_group` WHERE `setting_group_name` LIKE 'Footer Menu');

INSERT INTO setting_temp(`SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID`) VALUE ('weekly.highlight.topic.url','/SharpInU/news/1/list.in', 0, @ADMIN_USER_ID, @ADMIN_USER_ID, @FOOTER_MENU_GROUP_ID);
INSERT INTO setting_temp(`SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID`) VALUE ('weekly.highlight.topic.title','Sharp-in-U provides list of valuable Big Data Testing', 0, @ADMIN_USER_ID, @ADMIN_USER_ID, @FOOTER_MENU_GROUP_ID);
INSERT INTO setting_temp(`SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID`) VALUE ('weekly.highlight.topic.image.url','/SharpInU/assets/images/news.jpeg', 0, @ADMIN_USER_ID, @ADMIN_USER_ID, @FOOTER_MENU_GROUP_ID);

INSERT INTO setting_temp(`SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID`) VALUE ('testing.trend.topic.url','/SharpInU/trend.in', 0, @ADMIN_USER_ID, @ADMIN_USER_ID, @FOOTER_MENU_GROUP_ID);
INSERT INTO setting_temp(`SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID`) VALUE ('testing.trend.topic.title','Find out the latest software testing trends that will drive QA and testing in feature.', 0, @ADMIN_USER_ID, @ADMIN_USER_ID, @FOOTER_MENU_GROUP_ID);
INSERT INTO setting_temp(`SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID`) VALUE ('testing.trend.topic.image.url','/SharpInU/assets/images/thinking.png', 0, @ADMIN_USER_ID, @ADMIN_USER_ID, @FOOTER_MENU_GROUP_ID);

INSERT INTO setting (`SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID`)
	SELECT `SETTING_NAME`,`SETTING_VALUE`, `SETTING_TYPE`, `CREATED_USER_ID`, `UPDATED_USER_ID`, `SETTING_GROUP_ID` FROM setting_temp
	WHERE `SETTING_NAME` NOT IN (SELECT `SETTING_NAME` FROM setting);

DROP TABLE IF EXISTS setting_temp;
DROP TABLE IF EXISTS setting_group_temp;
