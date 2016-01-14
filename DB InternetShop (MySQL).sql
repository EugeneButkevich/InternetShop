SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `InternetShop` DEFAULT CHARACTER SET utf8 ;
USE `InternetShop` ;

-- -----------------------------------------------------
-- Table `InternetShop`.`client`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `InternetShop`.`client` (
  `id_client` INT NOT NULL AUTO_INCREMENT ,
  `login` VARCHAR(20) NOT NULL ,
  `password` VARCHAR(20) NOT NULL ,
  `surname` VARCHAR(20) NOT NULL ,
  `name` VARCHAR(20) NOT NULL ,
  `registrationDate` DATE NOT NULL ,
  `phone` VARCHAR(15) NOT NULL ,
  `address` VARCHAR(50) NULL ,
  `email` VARCHAR(30) NULL ,
  PRIMARY KEY (`id_client`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InternetShop`.`blackList`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `InternetShop`.`blackList` (
  `id_blackList` INT NOT NULL AUTO_INCREMENT ,
  `id_client` INT NOT NULL ,
  `date_of_addition` DATE NOT NULL ,
  PRIMARY KEY (`id_blackList`) ,
  INDEX `fk_blackList_client1_idx` (`id_client` ASC) ,
  CONSTRAINT `fk_blackList_client1`
    FOREIGN KEY (`id_client` )
    REFERENCES `InternetShop`.`client` (`id_client` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InternetShop`.`categoryProduct`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `InternetShop`.`categoryProduct` (
  `id_category` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(40) NOT NULL ,
  PRIMARY KEY (`id_category`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InternetShop`.`product`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `InternetShop`.`product` (
  `id_product` INT NOT NULL AUTO_INCREMENT ,
  `id_category` INT NOT NULL ,
  `name` VARCHAR(20) NOT NULL ,
  `price` INT NOT NULL ,
  `quantityInStock` INT NOT NULL ,
  PRIMARY KEY (`id_product`) ,
  INDEX `fk_product_categoryProduct1_idx` (`id_category` ASC) ,
  CONSTRAINT `fk_product_categoryProduct1`
    FOREIGN KEY (`id_category` )
    REFERENCES `InternetShop`.`categoryProduct` (`id_category` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InternetShop`.`order`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `InternetShop`.`order` (
  `id_order` INT NOT NULL AUTO_INCREMENT ,
  `id_client` INT NOT NULL ,
  `address` VARCHAR(30) NOT NULL ,
  `date` DATE NOT NULL ,
  `status` VARCHAR(20) NOT NULL ,
  `amount` INT NULL ,
  PRIMARY KEY (`id_order`, `id_client`) ,
  INDEX `fk_order_client1_idx` (`id_client` ASC) ,
  CONSTRAINT `fk_order_client1`
    FOREIGN KEY (`id_client` )
    REFERENCES `InternetShop`.`client` (`id_client` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InternetShop`.`order_has_product`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `InternetShop`.`order_has_product` (
  `id_order` INT NOT NULL ,
  `id_product` INT NOT NULL ,
  `count` INT NOT NULL ,
  INDEX `fk_order_has_product_product1_idx` (`id_product` ASC) ,
  INDEX `fk_order_has_product_order1_idx` (`id_order` ASC) ,
  PRIMARY KEY (`id_order`, `id_product`) ,
  CONSTRAINT `fk_order_has_product_order1`
    FOREIGN KEY (`id_order` )
    REFERENCES `InternetShop`.`order` (`id_order` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_product_product1`
    FOREIGN KEY (`id_product` )
    REFERENCES `InternetShop`.`product` (`id_product` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InternetShop`.`administrator`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `InternetShop`.`administrator` (
  `id_administrator` INT NOT NULL AUTO_INCREMENT ,
  `login` VARCHAR(20) NOT NULL ,
  `password` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`id_administrator`) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
