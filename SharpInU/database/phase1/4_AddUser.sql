/*Data for the table `role` */
CREATE TABLE `user_temp` (
	`USER_EMAIL` varchar(50) NOT NULL,
	`USER_PASSWORD` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user_temp`(`USER_EMAIL`, `USER_PASSWORD`) VALUES ('sharpinu.tester@gmail.com', 'ROLE_USER');
INSERT INTO `user_temp`(`USER_EMAIL`, `USER_PASSWORD`) VALUES ('user_test01@gmail.com', 'ROLE_USER');

INSERT INTO `user` (`USER_EMAIL`, `USER_PASSWORD`)
	SELECT `USER_EMAIL`, `USER_PASSWORD` FROM `user_temp` 
	WHERE `USER_EMAIL` NOT IN (SELECT `USER_EMAIL` FROM `user`);

INSERT INTO `user_role` (`USER_ID`, `ROLE_ID`)
	SELECT u.`user_id`, r.`role_id` FROM `user` u, `role` r 
	WHERE u.`USER_EMAIL` LIKE 'sharpinu.tester@gmail.com';

INSERT INTO `user_role` (`USER_ID`, `ROLE_ID`)
	SELECT u.`user_id`, r.`role_id` from `user` u, `role` r 
	WHERE u.`USER_EMAIL` LIKE 'user_test01@gmail.com' AND r.`ROLE_NAME` LIKE 'ROLE_USER';

DROP TABLE IF EXISTS user_temp;