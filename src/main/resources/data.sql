CREATE DATABASE IF NOT EXISTS desafio_03;

USE desafio_03;

INSERT INTO user (username, password, email, role, creation_date)
VALUES ('fernando.escamilla', '$2a$10$dDaa4Cr/KeZEOLXA1wgl3e5dYESlFUGNmMONAfDoZoqEb/QvAAW/O',
        'fernando@gmail.com', 'ADMIN', NOW());

# La contraseña es 'adminFernando'



