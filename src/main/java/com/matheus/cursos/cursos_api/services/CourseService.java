package com.matheus.cursos.cursos_api.services;

import com.matheus.cursos.cursos_api.exceptions.CourseNotFoundException;
import com.matheus.cursos.cursos_api.model.dto.CourseRequestDTO;
import com.matheus.cursos.cursos_api.model.dto.CourseResponseDTO;
import com.matheus.cursos.cursos_api.model.dto.CourseUpdateDTO;
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
        return convertListEntityToListDTO(this.courseRepository.findAllByOrderByUpdatedAtDesc());
    }

    public CourseResponseDTO get(long id) {
        CourseEntity courseEntity = this.courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));
        
        return convertEntityToDTO(courseEntity);
    }

    public CourseResponseDTO create(CourseRequestDTO body) {
        CourseEntity courseEntity = convertDTOToEntity(body);
        courseEntity = this.courseRepository.save(courseEntity);
        return convertEntityToDTO(courseEntity);
    }

    public CourseResponseDTO update(Long id, CourseUpdateDTO body) {
        CourseEntity courseEntity = this.courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));

        if (body.getName() != null) {
            courseEntity.setName(body.getName());
        }
        if (body.getCategory() != null) {
            courseEntity.setCategory(body.getCategory());
        }
        if (body.getActive() != null) {
            courseEntity.setActive(body.getActive());
        }

        courseEntity = this.courseRepository.save(courseEntity);
        return convertEntityToDTO(courseEntity);
    }
    
    public void delete(Long id) {
        CourseEntity courseEntity = this.courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado"));
        
        this.courseRepository.delete(courseEntity);
    }
}
