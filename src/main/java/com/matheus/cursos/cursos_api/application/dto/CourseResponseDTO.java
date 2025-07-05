package com.matheus.cursos.cursos_api.application.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CourseResponseDTO {
    private Long id;
    private String name;
    private String category;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
