package uz.nazir.trainee.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * DTO request
 * All requests should be validated before passing into service
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequest {

    @Size(min = 3, max = 50, message = "name should be in range of 3-50")
    private String name;
}
