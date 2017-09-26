-- MySQL Script generated by MySQL Workbench
-- Tue Sep 26 21:42:56 2017
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
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startup_management_system`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `startup_management_system`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(320) NULL,
  `registration_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `firstname` VARCHAR(100) NULL,
  `lastname` VARCHAR(100) NULL,
  `description` VARCHAR(255) NULL,
  `country_id` BIGINT NULL,
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
-- Table `startup_management_system`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `startup_management_system`.`city` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startup_management_system`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `startup_management_system`.`admin` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
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
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startup_management_system`.`startup`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `startup_management_system`.`startup` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL,
  `registration_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  `country_id` BIGINT NULL,
  `industry_id` BIGINT NOT NULL,
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
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL,
  `registration_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  `country_id` BIGINT NULL,
  `industry_id` BIGINT NOT NULL,
  `budget` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idstartup_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
