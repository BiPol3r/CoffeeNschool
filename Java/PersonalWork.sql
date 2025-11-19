-- 1) Create database
CREATE DATABASE IF NOT EXISTS zuko_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

-- 2) Create a dedicated user
CREATE USER IF NOT EXISTS 'zuko_user'@'localhost' IDENTIFIED BY 'metro';

-- 3) Grant privileges on zuko_db to that user
GRANT ALL PRIVILEGES ON zuko_db.* TO 'zuko_user'@'localhost';
FLUSH PRIVILEGES;

-- 4) Use the database
USE zuko_db;

-- 5) Create users table
CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  phone VARCHAR(64),
  password_hash TEXT NOT NULL,
  salt TEXT NOT NULL,
  country VARCHAR(100),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
