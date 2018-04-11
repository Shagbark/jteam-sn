DROP DATABASE IF EXISTS jteam_social_network;
CREATE DATABASE IF NOT EXISTS jteam_social_network;

USE jteam_social_network;

CREATE TABLE IF NOT EXISTS accounts (
  account_id INT PRIMARY KEY,
  name VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS application_user (
  account_id INT PRIMARY KEY,
  first_name VARCHAR(40) NOT NULL,
  last_name VARCHAR(50),
  email VARCHAR(100) NOT NULL UNIQUE,
  FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

CREATE TABLE IF NOT EXISTS application_user_info (
  account_id INT PRIMARY KEY,
  age INT,
  telephone VARCHAR(30),
  FOREIGN KEY (account_id) REFERENCES application_user(account_id)
);

CREATE TABLE IF NOT EXISTS application_user_password (
  account_id INT PRIMARY KEY,
  password VARCHAR(30) NOT NULL UNIQUE,
  FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);
