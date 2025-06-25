package com.matheus.cursos.cursos_api.services;

import com.matheus.cursos.cursos_api.model.dto.CourseRequestDTO;
import com.matheus.cursos.cursos_api.model.dto.CourseResponseDTO;
import com.matheus.cursos.cursos_api.model.entity.CourseEntity;
import com.matheus.cursos.cursos_api.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CourseEntity convertDTOToEntity(CourseRequestDTO dto) {
        return modelMapper.map(dto, CourseEntity.class);
    }

    public CourseResponseDTO convertEntityToDTO(CourseEntity entity) {
        return modelMapper.map(entity, CourseResponseDTO.class);
    }

    public List<CourseResponseDTO> convertListEntityToListDTO(List<CourseEntity> entities) {
        return entities.stream().map(this::convertEntityToDTO).toList();
    }

    public List<CourseResponseDTO> fetch() {
        return convertListEntityToListDTO(this.courseRepository.findAll());
    }

    public CourseResponseDTO create(CourseRequestDTO body) {
        CourseEntity courseEntity = convertDTOToEntity(body);
        System.out.println("Creating course: " + courseEntity.toString());
        courseEntity = this.courseRepository.save(courseEntity);
        return convertEntityToDTO(courseEntity);
    }
}
