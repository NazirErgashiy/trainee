package uz.nazir.trainee.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO response from server
 */
@Data
@Builder
public class StudentResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private Integer age;

    private List<TeacherResponse> teachers;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
