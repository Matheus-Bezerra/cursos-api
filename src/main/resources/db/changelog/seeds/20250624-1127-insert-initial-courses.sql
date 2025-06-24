--liquibase formatted sql
--changeset Matheus:20250624-1127

-- Inserts initial courses into the "courses" table
INSERT INTO courses (name, category) VALUES
('Backend Development', 'BACKEND'),
('Frontend Development', 'FRONTEND'),
('Fullstack Development', 'FULLSTACK'),
('Mobile Development', 'MOBILE'),
('Data Science', 'DATA_SCIENCE'),
('Artificial Intelligence', 'IA'),
('DevOps Practices', 'DEVOPS'),
('Others', 'OTHERS');