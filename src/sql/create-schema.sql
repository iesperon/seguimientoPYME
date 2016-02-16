-- Schema Creation Script

DROP TABLE IF EXISTS UserRoles;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Issue;

CREATE TABLE Users (
	login VARCHAR(16),
	password VARCHAR(32),
	enabled BOOLEAN,
	CONSTRAINT user_pk PRIMARY KEY (login)
);

CREATE INDEX ON Users(login);

CREATE TABLE UserRoles (
	userRoleId SERIAL,
	login VARCHAR(16),
	role VARCHAR(32),
	CONSTRAINT UserRoles_pk PRIMARY KEY (userRoleId),
	CONSTRAINT UserRoles_login_kf FOREIGN KEY (login) REFERENCES Users(login)
);

CREATE TABLE Issue (
	id SERIAL,
	title VARCHAR(256),
	description TEXT,
	date TIMESTAMP,
	CONSTRAINT issue_pk PRIMARY KEY (id)
);

CREATE INDEX ON Issue(id);