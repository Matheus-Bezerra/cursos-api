package com.matheus.cursos.cursos_api.controllers;

import com.matheus.cursos.cursos_api.exceptions.CourseNotFoundException;
import com.matheus.cursos.cursos_api.model.dto.CourseRequestDTO;
import com.matheus.cursos.cursos_api.model.dto.CourseResponseDTO;
import com.matheus.cursos.cursos_api.model.dto.CourseUpdateDTO;
import com.matheus.cursos.cursos_api.services.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{id}")
    @Operation(summary = "Buscar curso por ID", description = "Retorna os detalhes de um curso específico pelo ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curso encontrado", content = {
            @Content(schema = @Schema(implementation = CourseResponseDTO.class))
        }),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public ResponseEntity<Object> getCourse(@PathVariable Long id) {
        try {
            var course = this.courseService.get(id);
            return ResponseEntity.ok().body(course);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
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

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar curso", description = "Atualiza as informações de um curso existente")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Curso atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro ao atualizar curso"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody CourseUpdateDTO body) {
        try {
            this.courseService.update(id, body);
            return ResponseEntity.noContent().build();
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir curso", description = "Exclui um curso existente")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Curso excluído com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro ao excluir curso"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            this.courseService.delete(id);
            return ResponseEntity.status(204).build();
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
