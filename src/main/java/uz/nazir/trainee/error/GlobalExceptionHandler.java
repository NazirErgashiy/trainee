package uz.nazir.trainee.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.nazir.trainee.error.dto.ExceptionResponse;
import uz.nazir.trainee.error.exceptions.NotFoundException;

import java.time.LocalDateTime;

/**
 * Global Exception Handler
 * Can catch these errors: 400, 404, 409 ,500
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 400
     * Override MethodArgumentNotValidException
     * Do not annotate with @ExceptionHandler(MethodArgumentNotValidException.class)
     * It causes “Ambiguous @ExceptionHandler method mapped“ error
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request
    ) {
        return ResponseEntity
                .status(status)
                .body(new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.name(), LocalDateTime.now()));
    }

    /**
     * 404
     * Throw exception when resource not found
     */
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(NotFoundException ex) {
        return buildResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    /**
     * 409
     */
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<ExceptionResponse> handleIllegalExceptions(RuntimeException ex) {
        return buildResponseEntity(ex, HttpStatus.CONFLICT);
    }

    /**
     * 500
     * Throw exception for any other unpredicted reason.
     * Avoid modification of this method to make problem visible in logs.
     * Don't delete this method to avoid descendants declare it and catch 'any error'.
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleOtherExceptions(Exception ex) throws Exception {
        throw ex;
    }

    /**
     * Build response entity (easier to understand error status from server)
     */
    private ResponseEntity<ExceptionResponse> buildResponseEntity(Exception ex, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(new ExceptionResponse(ex.getMessage(), httpStatus.name(), LocalDateTime.now()));
    }
}
