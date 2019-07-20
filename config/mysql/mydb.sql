CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
commit;

CREATE TABLE IF NOT EXISTS `mydb`.`Parkings` (
  `idParking` INT NOT NULL AUTO_INCREMENT,
  `AccountId` VARCHAR(99) NOT NULL,
  `Type` VARCHAR(99) NOT NULL,
  `State` VARCHAR(99) NOT NULL,
  `Start` DATETIME NULL,
  `End` DATETIME NULL,
  PRIMARY KEY (`idParking`))
ENGINE = InnoDB;
commit;

CREATE TABLE IF NOT EXISTS `mydb`.`Payments` (
  `idPayments` INT NOT NULL AUTO_INCREMENT,
  `PaymentRate` DECIMAL(2) NOT NULL,
  `IsActive` INT NOT NULL DEFAULT 0,
  `Timestamp` DATETIME NOT NULL,
  PRIMARY KEY (`idPayments`))
ENGINE = InnoDB;
commit;

CREATE TABLE IF NOT EXISTS `mydb`.`Accounts` (
  `idAccounts` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(99) NOT NULL,
  `LastName` VARCHAR(99) NOT NULL,
  `PESEL` VARCHAR(99) NOT NULL,
  `Registration` VARCHAR(99) NOT NULL,
  `VIN` VARCHAR(99) NOT NULL,
  `Brand` VARCHAR(99) NOT NULL,
  `Model` VARCHAR(99) NOT NULL,
  `Production` DATE NOT NULL,
  PRIMARY KEY (`idAccounts`))
ENGINE = InnoDB;
commit;

