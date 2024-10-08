package uz.nazir.trainee.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.nazir.trainee.validation.OnCreate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO request
 * All requests should be validated before passing into service
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRequest {

    @NotNull(groups = OnCreate.class, message = "firstName should be populated")
    @Size(min = 3, max = 50, message = "firstName should be in range of 3-50")
    private String firstName;

    @NotNull(groups = OnCreate.class, message = "lastName should be populated")
    @Size(min = 3, max = 50, message = "lastName should be in range of 3-50")
    private String lastName;

    @NotNull(groups = OnCreate.class, message = "middleName should be populated")
    @Size(min = 3, max = 50, message = "middleName should be in range of 3-50")
    private String middleName;

    @NotNull(groups = OnCreate.class, message = "age should be populated")
    @Min(value = 1, message = "age should be greater than 0")
    @Max(value = 100, message = "age should be less than 100")
    private Integer age;

    private Long subjectId;
}
