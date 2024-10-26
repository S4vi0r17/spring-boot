create table users (
    id bigint primary key auto_increment,
    username varchar(100) not null unique,
    password varchar(300) not null
);
