package com.matheus.cursos.cursos_api.application.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matheus.cursos.cursos_api.domain.entity.Course;
import com.matheus.cursos.cursos_api.infrastructure.persistence.entity.CourseEntity;

@Component
public class CourseMapper {
    
   @Autowired
    private ModelMapper modelMapper;

    public CourseEntity toEntity(Course course) {
        return modelMapper.map(course, CourseEntity.class);
    }

    public Course toDomain(CourseEntity entity) {
        return modelMapper.map(entity, Course.class);
    }

     public List<Course> toListDomain(List<CourseEntity> entities) {
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
