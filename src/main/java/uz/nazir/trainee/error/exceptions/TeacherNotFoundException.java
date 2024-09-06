package uz.nazir.trainee.error.exceptions;

/**
 * Exception 404 for Teacher
 */
public class TeacherNotFoundException extends NotFoundException {
    public TeacherNotFoundException(Long id) {
        super("teacher", id);
    }
}
