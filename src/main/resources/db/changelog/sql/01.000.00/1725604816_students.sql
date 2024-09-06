--liquibase formatted sql

--changeset 1725604816_students.sql:3
CREATE TABLE IF NOT EXISTS _students
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    age int NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    middle_name varchar(255) NOT NULL,
    create_date timestamp without time zone,
    update_date timestamp without time zone
);