package uz.nazir.trainee.mappers.util;

import org.springframework.stereotype.Component;
import uz.nazir.trainee.entities.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides implementation of needed methods to mapstruct
 */
@Component
public class StudentHelper {

    /**
     * Parse ids to Teacher with only mapped id, other values are NULL
     *
     * @param value teacher id
     * @return teacher entity
     */
    public List<Teacher> teacherIdsToTeacherList(List<Long> value) {
        if (value == null) {
            return null;
        }

        List<Teacher> result = new ArrayList<>();
        for (Long id : value) {
            Teacher newTeacher = new Teacher();
            newTeacher.setId(id);
            result.add(newTeacher);
        }
        return result;
    }
}
