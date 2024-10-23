-- V2__Agregar_columna_calle_a_pacientes.sql

ALTER TABLE pacientes
ADD COLUMN calle VARCHAR(100) NOT NULL;
