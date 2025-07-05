package com.matheus.cursos.cursos_api.domain.entity;

import com.matheus.cursos.cursos_api.domain.enums.CourseCategoryEnum;
import java.time.LocalDateTime;


public class Course {
    
    private Long id;
    private String name;
    private CourseCategoryEnum category;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Course() {}

    public Course(Long id, String name, CourseCategoryEnum category, Boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CourseCategoryEnum category) {
        this.category = category;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
