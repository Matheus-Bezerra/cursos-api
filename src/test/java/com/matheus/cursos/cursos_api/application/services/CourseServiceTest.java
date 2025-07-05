package com.matheus.cursos.cursos_api.application.services;

import com.matheus.cursos.cursos_api.application.dto.CourseRequestDTO;
import com.matheus.cursos.cursos_api.application.dto.CourseResponseDTO;
import com.matheus.cursos.cursos_api.application.dto.CourseUpdateDTO;
import com.matheus.cursos.cursos_api.domain.entity.Course;
import com.matheus.cursos.cursos_api.domain.enums.CourseCategoryEnum;
import com.matheus.cursos.cursos_api.domain.repository.CourseRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@SpringBootTest
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseDtoMapper courseDtoMapper;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a course successfully")
    void shouldCreateACourseSuccessfully() {
        CourseRequestDTO requestDTO = new CourseRequestDTO();
        requestDTO.setName("Java");
        requestDTO.setCategory(CourseCategoryEnum.BACKEND);
        requestDTO.setActive(true);

        Course course = new Course();
        Course savedCourse = new Course();
        CourseResponseDTO responseDTO = new CourseResponseDTO();

        when(courseDtoMapper.toDomain(requestDTO)).thenReturn(course);
        when(courseRepository.save(course)).thenReturn(savedCourse);
        when(courseDtoMapper.toResponseDTO(savedCourse)).thenReturn(responseDTO);

        CourseResponseDTO result = courseService.create(requestDTO);

        assertNotNull(result);
        verify(courseDtoMapper).toDomain(requestDTO);
        verify(courseRepository).save(course);
        verify(courseDtoMapper).toResponseDTO(savedCourse);
    }

    @Test
    @DisplayName("Should throw exception when name is null")
    void shouldThrowExceptionWhenNameIsNull() {
        CourseRequestDTO requestDTO = new CourseRequestDTO();
        requestDTO.setName(null);
        requestDTO.setCategory(CourseCategoryEnum.BACKEND);
        requestDTO.setActive(true);

        when(courseDtoMapper.toDomain(requestDTO)).thenThrow(new IllegalArgumentException("O nome do curso é obrigatório"));

        assertThrows(IllegalArgumentException.class, () -> courseService.create(requestDTO));
    }

    @Test
    @DisplayName("Should throw exception when category is null")
    void shouldThrowExceptionWhenCategoryIsNull() {
        CourseRequestDTO requestDTO = new CourseRequestDTO();
        requestDTO.setName("Java");
        requestDTO.setCategory(null);
        requestDTO.setActive(true);

        when(courseDtoMapper.toDomain(requestDTO)).thenThrow(new IllegalArgumentException("A categoria do curso é obrigatória"));

        assertThrows(IllegalArgumentException.class, () -> courseService.create(requestDTO));
    }

    @Test
    @DisplayName("Should throw exception when active is null")
    void shouldThrowExceptionWhenActiveIsNull() {
        CourseRequestDTO requestDTO = new CourseRequestDTO();
        requestDTO.setName("Java");
        requestDTO.setCategory(CourseCategoryEnum.BACKEND);
        requestDTO.setActive(null);

        when(courseDtoMapper.toDomain(requestDTO)).thenThrow(new IllegalArgumentException("O status do curso é obrigatório"));

        assertThrows(IllegalArgumentException.class, () -> courseService.create(requestDTO));
    }

    @Test
    @DisplayName("Should throw exception when course name already exists")
    void shouldThrowExceptionWhenCourseNameAlreadyExists() {
        CourseRequestDTO requestDTO = new CourseRequestDTO();
        requestDTO.setName("Java");
        requestDTO.setCategory(CourseCategoryEnum.BACKEND);
        requestDTO.setActive(true);

        Course existingCourse = new Course();
        existingCourse.setName("Java");

        when(courseRepository.findByName("Java")).thenReturn(java.util.Optional.of(existingCourse));

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> courseService.create(requestDTO)
        );

        assertEquals("Já existe um curso com esse nome.", exception.getMessage());

        verify(courseRepository).findByName("Java");
        verify(courseDtoMapper, org.mockito.Mockito.never()).toDomain(requestDTO);
        verify(courseRepository, org.mockito.Mockito.never()).save(org.mockito.Mockito.any());
    }

    @Test
    @DisplayName("Should return course when get by id")
    void shouldReturnCourseWhenGetById() {
        long id = 1L;
        Course course = new Course();
        course.setId(id);
        CourseResponseDTO responseDTO = new CourseResponseDTO();
        responseDTO.setId(id);

        when(courseRepository.findById(id)).thenReturn(java.util.Optional.of(course));
        when(courseDtoMapper.toResponseDTO(course)).thenReturn(responseDTO);

        CourseResponseDTO result = courseService.get(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(courseRepository).findById(id);
        verify(courseDtoMapper).toResponseDTO(course);
    }

    @Test
    @DisplayName("Should throw exception when course not found on get")
    void shouldThrowExceptionWhenCourseNotFoundOnGet() {
        long id = 2L;
        when(courseRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(com.matheus.cursos.cursos_api.domain.exceptions.CourseNotFoundException.class,
            () -> courseService.get(id));
        verify(courseRepository).findById(id);
    }

    @Test
    @DisplayName("Should update course successfully")
    void shouldUpdateCourseSuccessfully() {
        long id = 1L;
        CourseUpdateDTO updateDTO = new CourseUpdateDTO();
        updateDTO.setName("Novo Nome");
        updateDTO.setCategory(CourseCategoryEnum.BACKEND);
        updateDTO.setActive(true);

        Course course = new Course();
        course.setId(id);

        Course updatedCourse = new Course();
        updatedCourse.setId(id);
        updatedCourse.setName("Novo Nome");
        updatedCourse.setCategory(CourseCategoryEnum.BACKEND);
        updatedCourse.setActive(true);

        CourseResponseDTO responseDTO = new CourseResponseDTO();
        responseDTO.setId(id);
        responseDTO.setName("Novo Nome");

        when(courseRepository.findById(id)).thenReturn(java.util.Optional.of(course));
        when(courseRepository.findByName("Novo Nome")).thenReturn(java.util.Optional.empty());
        when(courseRepository.save(course)).thenReturn(updatedCourse);
        when(courseDtoMapper.toResponseDTO(updatedCourse)).thenReturn(responseDTO);

        CourseResponseDTO result = courseService.update(id, updateDTO);

        assertNotNull(result);
        assertEquals("Novo Nome", result.getName());
        verify(courseRepository).findById(id);
        verify(courseRepository).findByName("Novo Nome");
        verify(courseRepository).save(course);
        verify(courseDtoMapper).toResponseDTO(updatedCourse);
    }

    @Test
    @DisplayName("Should throw exception when updating to existing course name")
    void shouldThrowExceptionWhenUpdatingToExistingCourseName() {
        long id = 1L;
        CourseUpdateDTO updateDTO = new CourseUpdateDTO();
        updateDTO.setName("NomeExistente");

        Course course = new Course();
        course.setId(id);

        Course existingCourse = new Course();
        existingCourse.setId(2L);

        when(courseRepository.findById(id)).thenReturn(java.util.Optional.of(course));
        when(courseRepository.findByName("NomeExistente")).thenReturn(java.util.Optional.of(existingCourse));

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> courseService.update(id, updateDTO)
        );

        assertEquals("Já existe um curso com esse nome.", exception.getMessage());
        verify(courseRepository).findById(id);
        verify(courseRepository).findByName("NomeExistente");
        verify(courseRepository, org.mockito.Mockito.never()).save(course);
    }

    @Test
    @DisplayName("Should throw exception when course not found on update")
    void shouldThrowExceptionWhenCourseNotFoundOnUpdate() {
        long id = 99L;
        CourseUpdateDTO updateDTO = new CourseUpdateDTO();
        when(courseRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(com.matheus.cursos.cursos_api.domain.exceptions.CourseNotFoundException.class,
            () -> courseService.update(id, updateDTO));
        verify(courseRepository).findById(id);
    }

    @Test
    @DisplayName("Should delete course successfully")
    void shouldDeleteCourseSuccessfully() {
        long id = 1L;
        Course course = new Course();
        course.setId(id);

        when(courseRepository.findById(id)).thenReturn(java.util.Optional.of(course));

        courseService.delete(id);

        verify(courseRepository).findById(id);
        verify(courseRepository).deleteById(id);
    }

    @Test
    @DisplayName("Should throw exception when course not found on delete")
    void shouldThrowExceptionWhenCourseNotFoundOnDelete() {
        long id = 99L;
        when(courseRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(com.matheus.cursos.cursos_api.domain.exceptions.CourseNotFoundException.class,
            () -> courseService.delete(id));
        verify(courseRepository).findById(id);
    }

  
}
