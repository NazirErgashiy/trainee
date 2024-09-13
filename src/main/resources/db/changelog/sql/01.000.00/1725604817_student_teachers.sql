--liquibase formatted sql

--changeset 1725604817_student_teachers.sql:4
CREATE TABLE IF NOT EXISTS student_teachers
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    student_id bigint not null,
    teacher_id bigint not null,

    CONSTRAINT _student_fk_1 FOREIGN KEY (student_id)
        REFERENCES PUBLIC._students (id) MATCH SIMPLE,
    CONSTRAINT _teacher_fk_1 FOREIGN KEY (teacher_id)
        REFERENCES PUBLIC._teachers (id) MATCH SIMPLE
);