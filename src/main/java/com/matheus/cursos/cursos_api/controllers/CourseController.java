package com.matheus.cursos.cursos_api.controllers;

import com.matheus.cursos.cursos_api.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping()
    public ResponseEntity<Object> fetchCourse() {
        try {
            var courses = this.courseService.fetch();
            return ResponseEntity.ok().body(courses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
