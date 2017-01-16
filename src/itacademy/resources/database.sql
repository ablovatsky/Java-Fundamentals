CREATE DATABASE  IF NOT EXISTS `restaurants` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `restaurants`;


DROP TABLE IF EXISTS `comments`;
DROP TABLE IF EXISTS `users_roles`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `address`;
DROP TABLE IF EXISTS `restaurant_cuisine`;
DROP TABLE IF EXISTS `cuisines`;
DROP TABLE IF EXISTS `restaurants`;
DROP TABLE IF EXISTS `cities`;
DROP TABLE IF EXISTS `countries`;



-- Table `users`
CREATE TABLE `users` (
  `id`        BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `username`  VARCHAR(45) NOT NULL UNIQUE,
  `password`  VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = UTF8;

      INSERT INTO `users`(username, password) VALUES ('admin','admin');
      UNLOCK TABLES;

-- Table `roles`
CREATE TABLE `roles` (
  `id`    BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `role`  VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = UTF8;

    INSERT INTO `roles`(role) VALUES ('user');
    UNLOCK TABLES;
    INSERT INTO `roles`(role) VALUES ('admin');
    UNLOCK TABLES;

-- Table `users_roles`
CREATE TABLE `users_roles` (
  `id`    BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `user_id`    BIGINT(20)  NOT NULL,
  `role_id`    BIGINT(20)  NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = UTF8;

INSERT INTO `users_roles`(user_id, role_id) VALUES ('1', '2');
UNLOCK TABLES;
INSERT INTO `users_roles`(user_id, role_id) VALUES ('1', '1');
UNLOCK TABLES;


-- Table: restaurants
CREATE TABLE `restaurants` (
  `id`          BIGINT(20)    NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(255)  NOT NULL,
  `information` TEXT,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = UTF8;

-- Table: cuisines
CREATE TABLE `cuisines` (
  `id`     BIGINT(20)    NOT NULL AUTO_INCREMENT,
  `name`   VARCHAR(255)  NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = UTF8;

-- Table: countries
CREATE TABLE `countries` (
  `id`     BIGINT(20)    NOT NULL AUTO_INCREMENT,
  `name`   VARCHAR(255)  NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = UTF8;

-- Table: cities
CREATE TABLE `cities` (
  `id`          BIGINT(20)    NOT NULL AUTO_INCREMENT,
  `country_id`  BIGINT(20)    NOT NULL,
  `name`        VARCHAR(255)  NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = UTF8;

-- Table: address
CREATE TABLE `address` (
  `id`                BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `restaurant_id`     BIGINT(20)  NOT NULL,
  `city_id`           BIGINT(20)  NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`),
  FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = UTF8;


-- Table: restaurant_cuisine
CREATE TABLE `restaurant_cuisine` (
  `id`                BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `restaurant_id`     BIGINT(20)  NOT NULL,
  `cuisine_id`        BIGINT(20)  NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`),
  FOREIGN KEY (`cuisine_id`) REFERENCES `cuisines` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = UTF8;

-- Table: comments
CREATE TABLE `comments` (
  `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `restaurant_id` BIGINT(20)   NOT NULL,
  `user_id`       BIGINT(20)   NOT NULL,
  `comment`       TEXT         NOT NULL,
  `date`          TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = UTF8;
