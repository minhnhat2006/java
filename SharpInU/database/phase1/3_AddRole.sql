CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */
CREATE TABLE `role_temp` (
	`role_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT  INTO `role_temp`(`role_name`) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO `role` (`role_name`)
	SELECT `role_name` FROM `role_temp` 
	WHERE `role_name` NOT IN (SELECT `role_name` FROM `role`);
DROP TABLE IF EXISTS role_temp;

CREATE TABLE IF NOT EXISTS `user_role` (
  `user_role_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_role_id`),
  CONSTRAINT `FK_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FK_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`USER_ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;