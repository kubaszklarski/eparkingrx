CREATE TABLE `payments` (
  `idPayments` int(11) NOT NULL AUTO_INCREMENT,
  `PaymentRate` decimal(2,0) NOT NULL,
  `IsActive` int(11) NOT NULL DEFAULT '0',
  `Timestamp` datetime NOT NULL,
  PRIMARY KEY (`idPayments`),
  UNIQUE KEY `idPayments_UNIQUE` (`idPayments`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
