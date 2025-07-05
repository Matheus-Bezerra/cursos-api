package com.matheus.cursos.cursos_api.infrastructure.persistence.entity;

import com.matheus.cursos.cursos_api.domain.enums.CourseCategoryEnum;
import com.matheus.cursos.cursos_api.infrastructure.persistence.constants.TableCorporateConstant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = TableCorporateConstant.TABLE_COURSE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseCategoryEnum category;

    private Boolean active;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
