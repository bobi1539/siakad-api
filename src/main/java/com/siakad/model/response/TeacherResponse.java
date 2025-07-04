package com.siakad.model.response;

import com.siakad.entity.MTeacher;
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
public class TeacherResponse extends AbstractMasterEntityResponse {
    private Long id;
    private String name;
    private String status;
    private String photo;

    public static TeacherResponse build(MTeacher teacher) {
        if (teacher == null) return null;

        TeacherResponse response = TeacherResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .status(teacher.getStatus().label)
                .photo(teacher.getPhoto())
                .build();
        setMasterEntity(response, teacher);
        return response;
    }
}
