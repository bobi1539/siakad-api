package com.siakad.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "m_clazz")
public class MClazz extends AbstractMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "grade_level")
    private Integer gradeLevel;

    @ManyToOne
    @JoinColumn(name = "homeroom_teacher_id")
    private MTeacher homeroomTeacher;

    public static final String F_NAME = "name";
    public static final String F_GRADE_LEVEL = "gradeLevel";
}
