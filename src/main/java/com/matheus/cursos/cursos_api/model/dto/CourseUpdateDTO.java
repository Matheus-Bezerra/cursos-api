package com.matheus.cursos.cursos_api.model.dto;

import com.matheus.cursos.cursos_api.model.enums.CourseCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseUpdateDTO {
    private String name;
    private CourseCategoryEnum category;
    private Boolean active;
}
