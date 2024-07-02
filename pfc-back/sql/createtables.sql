CREATE DATABASE IF NOT EXISTS excursionDB;

DROP TABLE IF EXISTS reserva;
DROP TABLE IF EXISTS excursion;

-- --------------------------------- Excursion ------------------------------------
CREATE TABLE excursion ( excursionId BIGINT NOT NULL AUTO_INCREMENT,
                     ciudad VARCHAR(255) COLLATE latin1_bin NOT NULL,
                     descripcion VARCHAR(1024) COLLATE latin1_bin NOT NULL,
                     fechaInicio DATETIME NOT NULL,
                     precio FLOAT NOT NULL,
                     maxNumPlazas SMALLINT NOT NULL,
                     fechaAlta DATETIME NOT NULL,
                     numPlazas SMALLINT NOT NULL,
                     CONSTRAINT ExcursionPK PRIMARY KEY(excursionId),
                     CONSTRAINT validNumPlazas CHECK ( numPlazas >= 0 AND numPlazas <= maxNumPlazas ),
                     CONSTRAINT validPrecio CHECK ( precio >= 0 AND precio <= 999999999) ) ENGINE = InnoDB;

-- --------------------------------- Reserva ------------------------------------

CREATE TABLE reserva ( reservaId BIGINT NOT NULL AUTO_INCREMENT,
                    excursionId BIGINT NOT NULL,
                    email VARCHAR(40) COLLATE latin1_bin NOT NULL,
                    fechaCancelacion DATETIME,
                    numPlazas SMALLINT NOT NULL,
                    precio FLOAT NOT NULL,
                    fechaReserva DATETIME NOT NULL,
                    CONSTRAINT ReservaPK PRIMARY KEY(reservaId),
                    CONSTRAINT ReservaExcursionIdFK FOREIGN KEY(excursionId)
                        REFERENCES excursion(excursionId) ON DELETE CASCADE ) ENGINE = InnoDB;

-- Inserción de datos en la tabla Excursion
INSERT INTO excursion (ciudad, descripcion, fechaInicio, precio, maxNumPlazas, fechaAlta, numPlazas) VALUES 
('Santiago de Compostela', 'Visita guiada por la Catedral de Santiago', '2025-06-15 10:00:00', 10.50, 20, '2024-05-01 08:00:00', 19),
('Vigo', 'Ruta de senderismo por las Islas Cíes', '2025-08-10 09:30:00', 18.75, 30, '2024-05-10 10:00:00', 30),
('Beade', 'Excursión al sótano de Galpos', '2024-10-01 23:30:00', 19.99, 30, '2024-05-10 10:00:00', 30),
('A Coruña', 'Paseo en barco por la costa coruñesa', '2025-07-20 14:00:00', 25.00, 15, '2024-05-05 09:00:00', 15),
('Ourense', 'Visita a las termas de Outariz', '2025-09-05 11:00:00', 15.00, 25, '2024-05-15 11:00:00', 25),
('Excursion a Marte', 'Nos lleva Elon Musk', '2050-01-01 10:00:00', 250000.00, 200, '2050-05-01 08:00:00', 200),
('Kabukicho', 'Visita la Millenium Tower', '2005-12-25 23:00:00', 20.00, 5, '2001-05-15 11:00:00', 5),
('Excursion en el Pasado', 'Esta excursion ya ha acabado', '2018-01-01 10:00:00', 10.50, 20, '2024-05-01 08:00:00', 20),
('Excursion sin Plazas', 'Esta excursion ya no tiene plazas', '2050-01-01 10:00:00', 25.00, 20, '2024-05-01 08:00:00', 00);

-- Inserción de datos en la tabla Reserva
INSERT INTO reserva (excursionId, email, fechaCancelacion, numPlazas, precio, fechaReserva) VALUES
(1, 'usuario1@example.com', NULL, 2, 21.00, '2024-05-02 11:30:00'),
(2, 'usuario2@example.com', NULL, 3, 75.00, '2024-05-03 13:45:00'),
(3, 'usuario3@example.com', NULL, 1, 10.50, '2024-05-04 16:20:00');
