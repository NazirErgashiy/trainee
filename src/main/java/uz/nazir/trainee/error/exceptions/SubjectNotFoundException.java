package uz.nazir.trainee.error.exceptions;

/**
 * Exception 404 for Subject
 */
public class SubjectNotFoundException extends NotFoundException {
    public SubjectNotFoundException(Long id) {
        super("subject", id);
    }
}
