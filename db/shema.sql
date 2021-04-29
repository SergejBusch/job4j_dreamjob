create table post (
    id serial primary key,
    name text
);

create table candidate (
    id serial primary key,
    name text
);

create table users (
    id serial primary key,
    email text unique not null,
    password text not null,
    name text not null
);