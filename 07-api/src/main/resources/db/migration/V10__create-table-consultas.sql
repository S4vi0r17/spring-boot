create table consultas(
    id bigint primary key auto_increment,
    id_paciente bigint not null,
    id_medico bigint not null,
    fecha datetime not null,

    foreign key (id_paciente) references pacientes(id),
    foreign key (id_medico) references medicos(id)
);