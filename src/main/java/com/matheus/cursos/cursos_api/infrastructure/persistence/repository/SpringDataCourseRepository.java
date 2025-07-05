package com.matheus.cursos.cursos_api.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheus.cursos.cursos_api.infrastructure.persistence.entity.CourseEntity;

public interface SpringDataCourseRepository extends JpaRepository<CourseEntity, Long> {
    List<CourseEntity> findAllByOrderByUpdatedAtDesc();
    Optional<CourseEntity> findByName(String name);
}
