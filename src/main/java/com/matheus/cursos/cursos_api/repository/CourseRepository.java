package com.matheus.cursos.cursos_api.repository;

import com.matheus.cursos.cursos_api.model.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {}
