alter table medicos add activo boolean default true;
update medicos set activo = true;
