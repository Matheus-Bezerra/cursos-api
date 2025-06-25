--liquibase formatted sql
--changeset Matheus:20250624-1102

-- This migration alters the "category" column in the "courses" table to change its type from ENUM to VARCHAR(50).
ALTER TABLE courses ALTER COLUMN category TYPE VARCHAR(50);

--rollback ALTER TABLE courses ALTER COLUMN category TYPE course_category;