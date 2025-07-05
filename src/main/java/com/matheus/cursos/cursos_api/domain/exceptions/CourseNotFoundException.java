package com.matheus.cursos.cursos_api.domain.exceptions;

public class CourseNotFoundException extends RuntimeException {   
    public CourseNotFoundException(String message) {
        super(message);
    }
}
