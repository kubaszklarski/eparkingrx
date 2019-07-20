CREATE TABLE `accounts` (
  `idAccounts` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(99) NOT NULL,
  `LastName` varchar(99) NOT NULL,
  `PESEL` varchar(99) NOT NULL,
  `Registration` varchar(99) NOT NULL,
  `VIN` varchar(99) NOT NULL,
  `Brand` varchar(99) NOT NULL,
  `Model` varchar(99) NOT NULL,
  `Production` date NOT NULL,
  PRIMARY KEY (`idAccounts`),
  UNIQUE KEY `idAccounts_UNIQUE` (`idAccounts`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8;
