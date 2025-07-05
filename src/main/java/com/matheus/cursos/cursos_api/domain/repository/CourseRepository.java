package com.matheus.cursos.cursos_api.domain.repository;

import java.util.List;
import java.util.Optional;

import com.matheus.cursos.cursos_api.domain.entity.Course;

public interface CourseRepository {
    Course save(Course course);
    Optional<Course> findById(Long id);
    Optional<Course> findByName(String name);
    List<Course> findAllByOrderByUpdatedAtDesc();
    void deleteById(Long id);
}
