--liquibase formatted sql

--changeset 1725604814_subjects.sql:1
CREATE TABLE IF NOT EXISTS _subjects
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name varchar(255) NOT NULL,
    create_date timestamp without time zone,
    update_date timestamp without time zone
);