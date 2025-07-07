package com.matheus.cursos.cursos_api.adapters.dto;

import com.matheus.cursos.cursos_api.domain.enums.CourseCategoryEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 2, max = 100, message = "O nome do curso deve ter entre 2 e 100 caracteres")
    private String name;

    @NotNull(message = "A categoria do curso é obrigatória")
    private CourseCategoryEnum category;

    @NotNull(message = "O status do curso é obrigatório")
    private Boolean active;
}
