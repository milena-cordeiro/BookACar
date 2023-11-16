DROP SCHEMA IF EXISTS book_a_car;
CREATE SCHEMA book_a_car;
USE book_a_car;

CREATE TABLE cars (
  `carID` INT NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(45) NOT NULL,
  `brand` VARCHAR(45) NULL,
  `color` VARCHAR(45) NULL,
  `year` INT NULL,
  `plate` VARCHAR(45) NOT NULL,
  `available` TINYINT(1) NOT NULL,
  PRIMARY KEY (`carID`)
);

CREATE TABLE clients (
  `clientID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`clientID`)
);

CREATE TABLE reservations (
  `reservationID` INT NOT NULL AUTO_INCREMENT,
  `start` DATE NOT NULL,
  `end` DATE NOT NULL,
  `carId` INT NOT NULL,
  `clientId` INT NOT NULL,
  PRIMARY KEY (`reservationID`),
  INDEX `carId_idx` (`carId` ASC) VISIBLE,
  INDEX `clientId_idx` (`clientId` ASC) VISIBLE,
  CONSTRAINT `carId`
    FOREIGN KEY (`carId`)
    REFERENCES `cars` (`carID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `clientId`
    FOREIGN KEY (`clientId`)
    REFERENCES `clients` (`clientID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

INSERT INTO cars(carID, model, brand, color, year, plate, available)
    VALUES (1, 'uno', 'fiat', 'preto', 2015, 'MDA2548', 1),
           (2, 'palio', 'fiat', 'branco', 2010, 'MGE5847', 1),
           (3, 'corsa', 'chevrolet', 'cinza', 1998, 'YTE5478', 0),
           (4, 'renegade', 'jeep', 'vermelho', 2018, 'RTE8974', 0),
           (5, 'gol', 'volks', 'prata', 2000, 'NHT4815', 1),
           (6, 'celta', 'chevrolet', 'preto', 1998, 'DFT5874', 0);
           
INSERT INTO clients(clientID, name, email)
    VALUES (1, 'João Santos', 'joaum@gmail.com'),
           (2, 'Maria da Silva', 'marias@outlook.com'),
           (3, 'Marcos Viera', 'marcondes@uol.com'),
           (4, 'Juliana Cardoso', 'jujuliana@gmail.com'),
           (5, 'José da Paixão', 'zepaixao@gmail.com'),
           (6, 'Renata Silveira', 'renata@outlook.com');

