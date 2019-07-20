CREATE TABLE `parkings` (
  `idParking` int(11) NOT NULL AUTO_INCREMENT,
  `AccountId` varchar(99) NOT NULL,
  `Type` varchar(99) NOT NULL,
  `State` varchar(99) NOT NULL,
  `Start` datetime DEFAULT NULL,
  `End` datetime DEFAULT NULL,
  PRIMARY KEY (`idParking`),
  UNIQUE KEY `idParking_UNIQUE` (`idParking`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
