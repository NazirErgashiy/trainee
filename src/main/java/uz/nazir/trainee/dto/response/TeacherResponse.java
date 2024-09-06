package uz.nazir.trainee.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO response from server
 */
@Data
@Builder
public class TeacherResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private Integer age;

    private SubjectResponse subject;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
