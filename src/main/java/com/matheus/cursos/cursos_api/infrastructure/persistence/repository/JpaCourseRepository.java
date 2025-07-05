package com.matheus.cursos.cursos_api.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.matheus.cursos.cursos_api.application.services.CourseMapper;
import com.matheus.cursos.cursos_api.domain.entity.Course;
import com.matheus.cursos.cursos_api.domain.repository.CourseRepository;
import com.matheus.cursos.cursos_api.infrastructure.persistence.entity.CourseEntity;

@Repository
public class JpaCourseRepository implements CourseRepository {

    @Autowired
    SpringDataCourseRepository springDataCourseRepository;

    @Autowired
    CourseMapper courseMapper;

    @Override
    public Course save(Course course) {
        CourseEntity entity = courseMapper.toEntity(course);
        entity = springDataCourseRepository.save(entity);
        return courseMapper.toDomain(entity);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return springDataCourseRepository.findById(id)
                .map(courseMapper::toDomain);
    }

    @Override
    public List<Course> findAllByOrderByUpdatedAtDesc() {
        return courseMapper.toListDomain(springDataCourseRepository.findAllByOrderByUpdatedAtDesc());
    }

    @Override
    public void deleteById(Long id) {
        springDataCourseRepository.deleteById(id);
    }
    
}
