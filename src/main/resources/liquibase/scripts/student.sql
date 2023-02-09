-- liquibase formatted sql

-- changeset sdomanov: 1
CREATE INDEX student_by_name_index ON student (name);

-- changeset sdomanov: 2
CREATE INDEX faculty_by_name_and_color_index ON faculty (name, color);
