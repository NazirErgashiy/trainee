package uz.nazir.trainee.error.exceptions;

/**
 * Exception 404 for Student
 */
public class StudentNotFoundException extends NotFoundException {
    public StudentNotFoundException(Long id) {
        super("student", id);
    }
}
