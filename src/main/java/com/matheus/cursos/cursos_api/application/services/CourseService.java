package com.matheus.cursos.cursos_api.application.services;

import com.matheus.cursos.cursos_api.application.dto.CourseRequestDTO;
import com.matheus.cursos.cursos_api.application.dto.CourseResponseDTO;
import com.matheus.cursos.cursos_api.application.dto.CourseUpdateDTO;
import com.matheus.cursos.cursos_api.domain.entity.Course;
import com.matheus.cursos.cursos_api.domain.exceptions.CourseNotFoundException;
import com.matheus.cursos.cursos_api.domain.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseDtoMapper courseDtoMapper;

    public List<CourseResponseDTO> fetch() {
        List<Course> courses = this.courseRepository.findAllByOrderByUpdatedAtDesc();
        return courses.stream()
                .map(courseDtoMapper::toResponseDTO)
                .collect(Collectors.toList());
       
    }

    public CourseResponseDTO get(long id) {
        Course course = this.courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));

        return courseDtoMapper.toResponseDTO(course);
    }

    public CourseResponseDTO create(CourseRequestDTO body) {
        if (courseRepository.findByName(body.getName()).isPresent()) {
            throw new IllegalArgumentException("Já existe um curso com esse nome.");
        }

        Course course = courseDtoMapper.toDomain(body);
        Course saved = this.courseRepository.save(course);
        return courseDtoMapper.toResponseDTO(saved);
    }

    public CourseResponseDTO update(Long id, CourseUpdateDTO body) {
       Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));

        if (body.getName() != null) {
            courseRepository.findByName(body.getName()).ifPresent(existing -> {
                if (!existing.getId().equals(id)) {
                    throw new IllegalArgumentException("Já existe um curso com esse nome.");
                }
            });
            course.setName(body.getName());
        }

        if (body.getName() != null) {
            course.setName(body.getName());
        }
        if (body.getCategory() != null) {
            course.setCategory(body.getCategory());
        }
        if (body.getActive() != null) {
            course.setActive(body.getActive());
        }

        course = this.courseRepository.save(course);
        return courseDtoMapper.toResponseDTO(course);
    }
    
    public void delete(Long id) {
        Course course = this.courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));

        this.courseRepository.deleteById(course.getId());
    }
}
