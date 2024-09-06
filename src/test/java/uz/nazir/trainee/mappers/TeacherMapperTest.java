package uz.nazir.trainee.mappers;

import org.junit.jupiter.api.Test;
import uz.nazir.trainee.dto.request.TeacherRequest;
import uz.nazir.trainee.entities.Subject;
import uz.nazir.trainee.entities.Teacher;
import uz.nazir.trainee.mappers.util.TeacherHelper;

import java.time.LocalDateTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TeacherMapperTest {
    private final TeacherMapper mapper = new TeacherMapperImpl(new TeacherHelper());

    @Test
    void dtoToEntity() {
        var expected = TeacherRequest.builder()
                .age(20)
                .firstName("John")
                .lastName("Doe")
                .middleName("Smith")
                .subjectId(1L)
                .build();

        var result = mapper.dtoToEntity(expected);

        assertEquals(expected.getAge(), result.getAge());
        assertEquals(expected.getFirstName(), result.getFirstName());
        assertEquals(expected.getLastName(), result.getLastName());
        assertEquals(expected.getMiddleName(), result.getMiddleName());

        assertEquals(expected.getSubjectId(), result.getSubject().getId());

        assertNull(result.getId());
        assertNull(result.getCreateDate());
        assertNull(result.getUpdateDate());
    }

    @Test
    void entityToDto() {
        LocalDateTime now = LocalDateTime.now();

        var expected = Teacher.builder()
                .id(1L)
                .age(20)
                .firstName("John")
                .lastName("Doe")
                .middleName("Smith")
                .subject(Subject.builder()
                        .id(1L)
                        .name("John")
                        .createDate(now)
                        .updateDate(now)
                        .build())
                .createDate(now)
                .updateDate(now)
                .build();

        var result = mapper.entityToDto(expected);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getAge(), result.getAge());
        assertEquals(expected.getFirstName(), result.getFirstName());
        assertEquals(expected.getLastName(), result.getLastName());
        assertEquals(expected.getMiddleName(), result.getMiddleName());
        assertEquals(expected.getCreateDate(), result.getCreateDate());
        assertEquals(expected.getUpdateDate(), result.getUpdateDate());

        assertEquals(expected.getSubject().getId(), result.getSubject().getId());
        assertEquals(expected.getSubject().getName(), result.getSubject().getName());
        assertEquals(expected.getSubject().getCreateDate(), result.getSubject().getCreateDate());
        assertEquals(expected.getSubject().getUpdateDate(), result.getSubject().getUpdateDate());
    }

    @Test
    void toEntity() {

        var expected = TeacherRequest.builder()
                .age(20)
                .firstName("John")
                .lastName("Doe")
                .middleName("Smith")
                .build();

        LocalDateTime now = LocalDateTime.now();

        var result = Teacher.builder()
                .id(1L)
                .age(20)
                .firstName("John")
                .lastName("Doe")
                .middleName("Smith")
                .createDate(now)
                .updateDate(now)
                .build();

        mapper.toEntity(expected, result);

        assertEquals(expected.getAge(), result.getAge());
        assertEquals(expected.getFirstName(), result.getFirstName());
        assertEquals(expected.getLastName(), result.getLastName());
        assertEquals(expected.getMiddleName(), result.getMiddleName());

        assertNotNull(result.getId());
        assertNotNull(result.getCreateDate());
        assertNotNull(result.getUpdateDate());
    }
}
