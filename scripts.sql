DROP DATABASE Dbmusic;

CREATE DATABASE Dbmusic;

CREATE TABLE `dbmusic`.`title` (
  `idtitle` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL UNIQUE,
  `date` DATE NULL,
  PRIMARY KEY (`idtitle`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE `dbmusic`.`filter` (
  `idfilter` INT NOT NULL AUTO_INCREMENT,
  `filter` VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY (`idfilter`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE `dbmusic`.`label` (
	`idlabel` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `father` INT NULL,
    `id_filter` INT NOT NULL,
    PRIMARY KEY(`idlabel`),
    CONSTRAINT FOREIGN KEY(`father`) REFERENCES label(idlabel),
    CONSTRAINT FOREIGN KEY(`id_filter`) REFERENCES filter(idfilter));

CREATE TABLE `dbmusic`.`title_has_label` (
  `id_label` INT NOT NULL,
  `id_title` INT NOT NULL,
  INDEX `title_idx` (`id_title` ASC),
  CONSTRAINT `label`
    FOREIGN KEY (`id_label`)
    REFERENCES `dbmusic`.`label` (`idlabel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `title`
    FOREIGN KEY (`id_title`)
    REFERENCES `dbmusic`.`title` (`idtitle`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

