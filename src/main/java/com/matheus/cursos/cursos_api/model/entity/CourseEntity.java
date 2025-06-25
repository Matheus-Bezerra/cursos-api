package com.matheus.cursos.cursos_api.model.entity;

import com.matheus.cursos.cursos_api.model.constants.TableCorporateConstant;
import com.matheus.cursos.cursos_api.model.enums.CouseCategoryEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = TableCorporateConstant.TABLE_COURSE)
@Data
public class CouseEntity implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false, precision = 8)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 500)
    private CouseCategoryEnum category;

    private Boolean active;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
