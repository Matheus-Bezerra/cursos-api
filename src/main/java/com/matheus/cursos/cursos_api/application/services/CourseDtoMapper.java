package com.matheus.cursos.cursos_api.application.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matheus.cursos.cursos_api.application.dto.CourseRequestDTO;
import com.matheus.cursos.cursos_api.application.dto.CourseResponseDTO;
import com.matheus.cursos.cursos_api.domain.entity.Course;

@Component
public class CourseDtoMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public Course toDomain(CourseRequestDTO dto) {
        return modelMapper.map(dto, Course.class);
    }

    public CourseResponseDTO toResponseDTO(Course course) {
        return modelMapper.map(course, CourseResponseDTO.class);
    }
}
