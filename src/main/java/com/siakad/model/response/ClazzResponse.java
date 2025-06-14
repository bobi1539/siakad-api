package com.siakad.model.response;

import com.siakad.entity.MClazz;
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
public class ClazzResponse extends AbstractMasterEntityResponse {
    private Long id;
    private String name;
    private Integer gradeLevel;
    private TeacherResponse homeroomTeacher;

    public static ClazzResponse build(MClazz clazz) {
        if (clazz == null) return null;

        ClazzResponse response = ClazzResponse.builder()
                .id(clazz.getId())
                .name(clazz.getName())
                .gradeLevel(clazz.getGradeLevel())
                .homeroomTeacher(TeacherResponse.build(clazz.getHomeroomTeacher()))
                .build();
        setMasterEntity(response, clazz);
        return response;
    }
}
