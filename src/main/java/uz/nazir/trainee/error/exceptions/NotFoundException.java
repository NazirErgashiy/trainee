package uz.nazir.trainee.error.exceptions;

/**
 * Generic 404 RuntimeException Handled by GlobalExceptionHandler...
 */
public abstract class NotFoundException extends RuntimeException {
    public NotFoundException(String name, Long id) {
        super(String.format("Could not find %s by id=%d", name, id));
    }
}
