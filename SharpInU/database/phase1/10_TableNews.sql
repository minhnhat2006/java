-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE IF NOT EXISTS `news` (
  `NEWS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY_ID` int(11) NOT NULL DEFAULT 1,
  `USER_ID` int(11) DEFAULT NULL,  			
  `CONTENT` text NOT NULL,
  `SUMMARY` text NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`NEWS_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

ALTER TABLE `news` ADD `TITLE` TEXT NOT NULL AFTER `USER_ID`;
