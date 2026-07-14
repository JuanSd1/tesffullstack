-- ============================================================
-- Script de creación de base de datos
-- Autor: Juan Sebastian Quintero Diaz
-- Rama: quintero_bd
-- ============================================================

CREATE DATABASE IF NOT EXISTS bd_quintero
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE bd_quintero;

CREATE TABLE IF NOT EXISTS person (
    id          BIGINT          NOT NULL AUTO_INCREMENT,
    nombre      VARCHAR(100)    NOT NULL,
    apellido    VARCHAR(100)    NOT NULL,
    fechaNacimiento DATE        NOT NULL,
    puesto      VARCHAR(100)    NOT NULL,
    sueldo      DECIMAL(10,2)   NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- Usuario de conexión
-- ============================================================
CREATE USER IF NOT EXISTS 'conexion'@'localhost' IDENTIFIED BY 'conexion123';
GRANT ALL PRIVILEGES ON bd_quintero.* TO 'conexion'@'localhost';
FLUSH PRIVILEGES;
