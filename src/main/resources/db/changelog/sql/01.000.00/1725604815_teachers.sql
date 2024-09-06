--liquibase formatted sql

--changeset 1725604815_teachers.sql:2
CREATE TABLE IF NOT EXISTS _teachers
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    age int NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    middle_name varchar(255) NOT NULL,
    subject_id BIGINT,
    create_date timestamp without time zone,
    update_date timestamp without time zone,

    CONSTRAINT _subject_fk_1 FOREIGN KEY (subject_id)
        REFERENCES PUBLIC._subjects (id) MATCH SIMPLE
);