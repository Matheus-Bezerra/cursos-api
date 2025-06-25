package com.matheus.cursos.cursos_api.model.dto;

import com.matheus.cursos.cursos_api.model.enums.CourseCategoryEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequestDTO {
    @NotBlank(message = "O nome do curso é obrigatório")
    private String name;

    @NotNull(message = "A categoria do curso é obrigatória")
    private CourseCategoryEnum category;

    @NotNull(message = "O status do curso é obrigatório")
    private Boolean active;
}
