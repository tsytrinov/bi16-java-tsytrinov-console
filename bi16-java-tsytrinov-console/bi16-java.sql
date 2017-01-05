-- MySQL Script generated by MySQL Workbench
-- 10/30/16 21:23:56
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bi16-java
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bi16-java` ;

-- -----------------------------------------------------
-- Schema bi16-java
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bi16-java` DEFAULT CHARACTER SET utf8 ;
USE `bi16-java` ;

-- -----------------------------------------------------
-- Table `bi16-java`.`Account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bi16-java`.`Account` ;

CREATE TABLE IF NOT EXISTS `bi16-java`.`Account` (
  `Account_ID` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `Country` VARCHAR(45) NULL,
  PRIMARY KEY (`Account_ID`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bi16-java`.`CreditCard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bi16-java`.`CreditCard` ;

CREATE TABLE IF NOT EXISTS `bi16-java`.`CreditCard` (
  `CreditCard_ID` INT NOT NULL AUTO_INCREMENT,
  `Cash_amount` DECIMAL(10,2) NULL,
  `Acc_Account_ID` INT NOT NULL,
  PRIMARY KEY (`CreditCard_ID`),
  INDEX `fk_CreditCard_Account1_idx` (`Acc_Account_ID` ASC),
  CONSTRAINT `fk_CreditCard_Account1`
  FOREIGN KEY (`Acc_Account_ID`)
  REFERENCES `bi16-java`.`Account` (`Account_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bi16-java`.`Transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bi16-java`.`Transaction` ;

CREATE TABLE IF NOT EXISTS `bi16-java`.`Transaction` (
  `Transaction_ID` INT NOT NULL AUTO_INCREMENT,
  `Amount` DECIMAL(10,2) NULL,
  `FROM_CreditCard_ID` INT NOT NULL,
  `TO_CreditCard_ID` INT NOT NULL,
  PRIMARY KEY (`Transaction_ID`),
  INDEX `fk_Transaction_CreditCard_idx` (`FROM_CreditCard_ID` ASC),
  INDEX `fk_Transaction_CreditCard1_idx` (`TO_CreditCard_ID` ASC),
  CONSTRAINT `fk_Transaction_CreditCard`
  FOREIGN KEY (`FROM_CreditCard_ID`)
  REFERENCES `bi16-java`.`CreditCard` (`CreditCard_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_CreditCard1`
  FOREIGN KEY (`TO_CreditCard_ID`)
  REFERENCES `bi16-java`.`CreditCard` (`CreditCard_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;

SET SQL_MODE = '';
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;