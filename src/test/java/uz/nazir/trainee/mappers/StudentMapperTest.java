package uz.nazir.trainee.mappers;

import org.junit.jupiter.api.Test;
import uz.nazir.trainee.dto.request.StudentRequest;
import uz.nazir.trainee.entities.Student;
import uz.nazir.trainee.entities.Subject;
import uz.nazir.trainee.entities.Teacher;
import uz.nazir.trainee.mappers.util.StudentHelper;

import java.time.LocalDateTime;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StudentMapperTest {

    private final StudentMapper mapper = new StudentMapperImpl(new StudentHelper());

    @Test
    void dtoToEntity() {
        var expected = StudentRequest.builder()
                .age(20)
                .firstName("John")
                .lastName("Doe")
                .middleName("Smith")
                .teacherIds(List.of(1L))
                .build();

        var result = mapper.dtoToEntity(expected);

        assertEquals(expected.getAge(), result.getAge());
        assertEquals(expected.getFirstName(), result.getFirstName());
        assertEquals(expected.getLastName(), result.getLastName());
        assertEquals(expected.getMiddleName(), result.getMiddleName());

        assertEquals(expected.getTeacherIds().get(0), result.getTeachers().get(0).getId());

        assertNull(result.getId());
        assertNull(result.getCreateDate());
        assertNull(result.getUpdateDate());
    }

    @Test
    void entityToDto() {
        LocalDateTime now = LocalDateTime.now();

        var expected = Student.builder()
                .id(1L)
                .age(20)
                .firstName("John")
                .lastName("Doe")
                .middleName("Smith")
                .teachers(List.of(Teacher.builder()
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
                        .build()))
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

        assertEquals(expected.getTeachers().get(0).getId(), result.getTeachers().get(0).getId());
        assertEquals(expected.getTeachers().get(0).getAge(), result.getTeachers().get(0).getAge());
        assertEquals(expected.getTeachers().get(0).getFirstName(), result.getTeachers().get(0).getFirstName());
        assertEquals(expected.getTeachers().get(0).getMiddleName(), result.getTeachers().get(0).getMiddleName());
        assertEquals(expected.getTeachers().get(0).getCreateDate(), result.getTeachers().get(0).getCreateDate());
        assertEquals(expected.getTeachers().get(0).getUpdateDate(), result.getTeachers().get(0).getUpdateDate());

        assertEquals(expected.getTeachers().get(0).getSubject().getId(), result.getTeachers().get(0).getSubject().getId());
        assertEquals(expected.getTeachers().get(0).getSubject().getName(), result.getTeachers().get(0).getSubject().getName());
        assertEquals(expected.getTeachers().get(0).getSubject().getCreateDate(), result.getTeachers().get(0).getSubject().getCreateDate());
        assertEquals(expected.getTeachers().get(0).getSubject().getUpdateDate(), result.getTeachers().get(0).getSubject().getUpdateDate());
    }

    @Test
    void toEntity() {

        var expected = StudentRequest.builder()
                .age(20)
                .firstName("John")
                .lastName("Doe")
                .middleName("Smith")
                .build();

        LocalDateTime now = LocalDateTime.now();

        var result = Student.builder()
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
