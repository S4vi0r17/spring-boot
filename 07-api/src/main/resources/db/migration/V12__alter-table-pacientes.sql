ALTER TABLE pacientes ADD COLUMN activo BOOLEAN DEFAULT true;
UPDATE pacientes SET activo = true;
