create table post (
    id serial primary key,
    name text
);

create table cities (
    id serial primary key,
    city text unique not null
);

create table candidate (
    id serial primary key,
    name text,
    city_id integer references cities

);

create table users (
    id serial primary key,
    email text unique not null,
    password text not null,
    name text not null
);

