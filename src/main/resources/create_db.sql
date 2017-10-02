-- MySQL Script generated by MySQL Workbench
-- Thu Sep 28 03:19:31 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema startup_management_system
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema startup_management_system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `startup_management_system` DEFAULT CHARACTER SET utf8 ;
USE `startup_management_system` ;

-- -----------------------------------------------------
-- Table `startup_management_system`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `startup_management_system`.`country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,heroku_05351903c0e1390@us-cdbr-iron-east-05.cleardb.net
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startup_management_system`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `startup_management_system`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(320) NULL,
  `registration_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `firstname` VARCHAR(100) NULL,
  `lastname` VARCHAR(100) NULL,
  `description` VARCHAR(255) NULL,
  `country_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_user_country1_idx` (`country_id` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  CONSTRAINT `fk_user_country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `startup_management_system`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startup_management_system`.`region`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `startup_management_system`.`region` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_region_country1_idx` (`country_id` ASC),
  CONSTRAINT `fk_region_country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `startup_management_system`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startup_management_system`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `startup_management_system`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(320) NULL,
  `registration_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startup_management_system`.`industry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `startup_management_system`.`industry` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startup_management_system`.`startup`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `startup_management_system`.`startup` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL,
  `registration_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT NOT NULL,
  `country_id` INT NULL,
  `industry_id` INT NOT NULL,
  `budget` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idstartup_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `fk_startup_user1_idx` (`user_id` ASC),
  INDEX `fk_startup_country1_idx` (`country_id` ASC),
  INDEX `fk_startup_industry1_idx` (`industry_id` ASC),
  CONSTRAINT `fk_startup_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `startup_management_system`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_startup_country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `startup_management_system`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_startup_industry1`
    FOREIGN KEY (`industry_id`)
    REFERENCES `startup_management_system`.`industry` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startup_management_system`.`offer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `startup_management_system`.`offer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL,
  `registration_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT NOT NULL,
  `country_id` INT NULL,
  `industry_id` INT NOT NULL,
  `budget` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idstartup_UNIQUE` (`id` ASC),
  INDEX `fk_startup_user1_idx` (`user_id` ASC),
  INDEX `fk_startup_country1_idx` (`country_id` ASC),
  INDEX `fk_startup_industry1_idx` (`industry_id` ASC),
  CONSTRAINT `fk_startup_user10`
    FOREIGN KEY (`user_id`)
    REFERENCES `startup_management_system`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_startup_country10`
    FOREIGN KEY (`country_id`)
    REFERENCES `startup_management_system`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_startup_industry10`
    FOREIGN KEY (`industry_id`)
    REFERENCES `startup_management_system`.`industry` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startup_management_system`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `startup_management_system`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startup_management_system`.`admin_has_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `startup_management_system`.`admin_has_roles` (
  `admin_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`admin_id`, `role_id`),
  INDEX `fk_admin_has_role_role1_idx` (`role_id` ASC),
  INDEX `fk_admin_has_role_admin1_idx` (`admin_id` ASC),
  CONSTRAINT `fk_admin_has_role_admin1`
    FOREIGN KEY (`admin_id`)
    REFERENCES `startup_management_system`.`admin` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_admin_has_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `startup_management_system`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startup_management_system`.`user_has_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `startup_management_system`.`user_has_roles` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_user_has_role_role1_idx` (`role_id` ASC),
  INDEX `fk_user_has_role_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_role_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `startup_management_system`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `startup_management_system`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
