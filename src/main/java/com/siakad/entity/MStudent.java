package com.siakad.entity;

import com.siakad.constant.Gender;
import com.siakad.constant.StudentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "m_student")
public class MStudent extends AbstractMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "nisn")
    private String nisn;

    @Column(name = "nis")
    private String nis;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StudentStatus status;

    @ManyToOne
    @JoinColumn(name = "current_clazz_id")
    private MClazz currentClazz;

    public static final String F_FULL_NAME = "fullName";
    public static final String F_GENDER = "gender";
    public static final String F_STATUS = "status";
}
