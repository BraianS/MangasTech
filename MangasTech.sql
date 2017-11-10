-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mangastech
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mangastech
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mangastech` DEFAULT CHARACTER SET utf8 ;
USE `mangastech` ;

-- -----------------------------------------------------
-- Table `mangastech`.`autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mangastech`.`autor` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mangastech`.`grupos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mangastech`.`grupos` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mangastech`.`mangas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mangastech`.`mangas` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `data_lancado` DATE NULL DEFAULT NULL,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mangastech`.`capitulos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mangastech`.`capitulos` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `capitulo` INT(11) NULL DEFAULT NULL,
  `lancamento` DATETIME NULL DEFAULT NULL,
  `grupo_id` BIGINT(20) NULL DEFAULT NULL,
  `manga_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK8r857qpc55nx9rcgyxbfjbq97` (`grupo_id` ASC),
  INDEX `FKqkqx3tllokpwh8sunmtg7wnio` (`manga_id` ASC),
  CONSTRAINT `FK8r857qpc55nx9rcgyxbfjbq97`
    FOREIGN KEY (`grupo_id`)
    REFERENCES `mangastech`.`grupos` (`id`),
  CONSTRAINT `FKqkqx3tllokpwh8sunmtg7wnio`
    FOREIGN KEY (`manga_id`)
    REFERENCES `mangastech`.`mangas` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mangastech`.`generos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mangastech`.`generos` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mangastech`.`mangas_autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mangastech`.`mangas_autor` (
  `autor_id` BIGINT(20) NULL DEFAULT NULL,
  `manga_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`manga_id`),
  INDEX `FK83xuvj9bwdrly5jelyftol7dq` (`autor_id` ASC),
  CONSTRAINT `FK83xuvj9bwdrly5jelyftol7dq`
    FOREIGN KEY (`autor_id`)
    REFERENCES `mangastech`.`autor` (`id`),
  CONSTRAINT `FKo4y1nbohcdd1jmegcyy73qsy7`
    FOREIGN KEY (`manga_id`)
    REFERENCES `mangastech`.`mangas` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mangastech`.`mangas_generos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mangastech`.`mangas_generos` (
  `manga_id` BIGINT(20) NOT NULL,
  `genero_id` BIGINT(20) NOT NULL,
  INDEX `FKn0qps2njudh7fxdceqn9o31w6` (`genero_id` ASC),
  INDEX `FKa7j422d0xq1nv4cwioqjcowsk` (`manga_id` ASC),
  CONSTRAINT `FKa7j422d0xq1nv4cwioqjcowsk`
    FOREIGN KEY (`manga_id`)
    REFERENCES `mangastech`.`mangas` (`id`),
  CONSTRAINT `FKn0qps2njudh7fxdceqn9o31w6`
    FOREIGN KEY (`genero_id`)
    REFERENCES `mangastech`.`generos` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
