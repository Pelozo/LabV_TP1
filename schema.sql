create database tournament;
use tournament;

CREATE TABLE winners(
	id INTEGER NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	consumed INTEGER NOT NULL,
	CONSTRAINT pk_winner PRIMARY KEY (id)
);