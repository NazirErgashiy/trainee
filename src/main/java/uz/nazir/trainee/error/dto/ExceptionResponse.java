package uz.nazir.trainee.error.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Exception Response DTO goes to user when exception thrown
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private String message;

    private String code;

    private LocalDateTime timestamp;
}
