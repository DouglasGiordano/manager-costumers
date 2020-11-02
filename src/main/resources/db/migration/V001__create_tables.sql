
-- -----------------------------------------------------
-- Table costumer
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS costumer
(
    `id`        INT         NOT NULL AUTO_INCREMENT,
    `name`      VARCHAR(45) NOT NULL,
    `email`     VARCHAR(45) NULL,
    `birthDate` DATETIME    NULL,
    `cpf`       VARCHAR(14) NULL,
    `gender`    VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);


-- -----------------------------------------------------
-- Table address
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS address
(
    `id`                    INT         NOT NULL AUTO_INCREMENT,
    `state`                 VARCHAR(45) NULL,
    `city`                  VARCHAR(45) NULL,
    `neighborhood`          VARCHAR(45) NULL,
    `zipCode`               VARCHAR(45) NULL,
    `street`                VARCHAR(45) NULL,
    `number`                VARCHAR(45) NULL,
    `additionalInformation` VARCHAR(45) NULL,
    `main`                  TINYINT     NULL,
    `costumer`              INT         NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_costumer_address_idx` (`costumer` ASC) VISIBLE,
    CONSTRAINT `fk_costumer_address`
        FOREIGN KEY (`costumer`)
            REFERENCES costumer (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);