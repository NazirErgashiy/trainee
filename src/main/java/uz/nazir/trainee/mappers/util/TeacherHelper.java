package uz.nazir.trainee.mappers.util;

import org.springframework.stereotype.Component;
import uz.nazir.trainee.entities.Subject;

/**
 * Provides implementation of needed methods to mapstruct
 */
@Component
public class TeacherHelper {

    /**
     * Parse ids to Subject with only mapped id, other values are NULL
     *
     * @param value id
     * @return subject entity
     */
    public Subject idToEntity(Long value) {
        if (value == null) {
            return null;
        }

        Subject subject = new Subject();
        subject.setId(value);
        return subject;
    }
}
