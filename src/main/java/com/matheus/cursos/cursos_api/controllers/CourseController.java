package com.matheus.cursos.cursos_api.controllers;

import com.matheus.cursos.cursos_api.model.dto.CourseRequestDTO;
import com.matheus.cursos.cursos_api.model.dto.CourseResponseDTO;
import com.matheus.cursos.cursos_api.services.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos", description = "Operações relacionadas a cursos")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping()
    @Operation(summary = "Listar cursos", description = "Retorna a lista de todos os cursos cadastrados")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de cursos retornada com sucesso", content = {
            @Content(array = @ArraySchema (schema = @Schema(implementation = CourseResponseDTO.class)))
        }),
    })
    public ResponseEntity<Object> fetchCourse() {
        try {
            var courses = this.courseService.fetch();
            return ResponseEntity.ok().body(courses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping()
    @Operation(summary = "Criar curso", description = "Cria um novo curso com as informações fornecidas")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curso criado com sucesso", content = {
            @Content(schema = @Schema(implementation = CourseResponseDTO.class))
        }),
        @ApiResponse(responseCode = "400", description = "Erro ao criar curso")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody CourseRequestDTO body) {
        try {
            var course = this.courseService.create(body);
            return ResponseEntity.status(201).body(course);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    

}
