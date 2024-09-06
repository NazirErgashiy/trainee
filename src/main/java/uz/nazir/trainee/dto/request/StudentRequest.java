package uz.nazir.trainee.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * DTO request
 * All requests should be validated before passing into service
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    @Size(min = 3, max = 50, message = "firstName should be in range of 3-50")
    private String firstName;

    @Size(min = 3, max = 50, message = "lastName should be in range of 3-50")
    private String lastName;

    @Size(min = 3, max = 50, message = "middleName should be in range of 3-50")
    private String middleName;

    @Min(value = 1, message = "age should be greater than 0")
    @Max(value = 100, message = "age should be less than 100")
    private Integer age;

    private List<Long> teacherIds;
}
