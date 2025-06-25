--liquibase formatted sql
--changeset Matheus:20250624-1102

-- Creates the ENUM type for the "category" field in the "courses" table
CREATE TYPE course_category AS ENUM ('BACKEND', 'FRONTEND', 'FULLSTACK', 'MOBILE', 'DATA_SCIENCE', 'IA', 'DEVOPS', 'OTHERS');

-- Create ta ble courses
CREATE TABLE courses (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    category course_category NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--rollback DROP TABLE courses;
--rollback DROP TYPE course_category;