package uz.nazir.trainee.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO response from server
 */
@Data
@Builder
public class SubjectResponse {

    private Long id;

    private String name;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
