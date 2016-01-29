CREATE TABLE `statistic` (
  `field` VARCHAR(100) NOT NULL,
  `count` BIGINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`field`),
  UNIQUE INDEX `field_UNIQUE` (`field` ASC));

INSERT INTO `statistic` (`field`, `count`) VALUES ('site.view.total', '0');
INSERT INTO `statistic` (`field`, `count`) VALUES ('site.user.register.total', '0');
