package uz.nazir.trainee.services.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import uz.nazir.trainee.DbTestUtil;
import uz.nazir.trainee.IntegrationTestInitializr;
import uz.nazir.trainee.TestDataGenerator;
import uz.nazir.trainee.dto.request.StudentRequest;
import uz.nazir.trainee.dto.response.StudentResponse;
import uz.nazir.trainee.dto.response.TeacherResponse;
import uz.nazir.trainee.entities.Student;
import uz.nazir.trainee.mappers.StudentMapper;
import uz.nazir.trainee.repositories.StudentRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uz.nazir.trainee.TestDataGenerator.RANDOM;

@ContextConfiguration(initializers = IntegrationTestInitializr.class)
@SpringBootTest
//@Sql(scripts = "classpath:db/test/drop_tables.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ApplicationContext applicationContext;

    private List<StudentResponse> testData;

    @BeforeEach
    void setUp() throws SQLException {
        DbTestUtil.resetAutoIncrementColumns(applicationContext, "_STUDENTS");
        testData = TestDataGenerator.getInstance().createAndGetElements(
                applicationContext,
                5,
                StudentRepository.class,
                StudentMapper.class,
                () -> Student.builder()
                        .age(RANDOM.nextInt(100))
                        .firstName("TEST_NAME" + (RANDOM.nextInt(5000)))
                        .lastName("TEST_LASTNAME" + (RANDOM.nextInt(5000)))
                        .middleName("TEST_MIDDLENAME" + (RANDOM.nextInt(5000)))
                        .build());
    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    void readAll() {
        var actual = studentService.readAll(Pageable.unpaged()).getContent();
        var sortedActual = new ArrayList<>(actual);

        //sort
        sortedActual.sort(Comparator.comparing(StudentResponse::getId));

        for (int i = 0; i < testData.size(); i++) {
            var actualStudent = sortedActual.get(i);
            var expectedStudent = testData.get(i);

            assertEquals(expectedStudent.getId(), actualStudent.getId());
            assertEquals(expectedStudent.getAge(), actualStudent.getAge());
            assertEquals(expectedStudent.getFirstName(), actualStudent.getFirstName());
            assertEquals(expectedStudent.getLastName(), actualStudent.getLastName());
            assertEquals(expectedStudent.getMiddleName(), actualStudent.getMiddleName());
            assertEquals(expectedStudent.getCreateDate(), actualStudent.getCreateDate());
            assertEquals(expectedStudent.getUpdateDate(), actualStudent.getUpdateDate());
        }
    }

    @Test
    void readById() {
        var actual = studentService.readById(2L);
        var expected = testData.get(1);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAge(), actual.getAge());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getMiddleName(), actual.getMiddleName());
        assertEquals(expected.getCreateDate(), actual.getCreateDate());
        assertEquals(expected.getUpdateDate(), actual.getUpdateDate());
    }

    @Test
    void create() {
        var expected = StudentRequest.builder()
                .age(50)
                .firstName("John")
                .lastName("Doe")
                .middleName("Smith")
                .build();

        var actual = studentService.create(expected);

        assertNotNull(actual.getId());
        assertNotNull(actual.getCreateDate());
        assertNotNull(actual.getUpdateDate());

        assertEquals(expected.getAge(), actual.getAge());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getMiddleName(), actual.getMiddleName());
    }

    @Test
    void update() {
        var actual1 = studentRepository.findById(2L).get();
        var expected1 = testData.get(1);

        assertEquals(expected1.getId(), actual1.getId());
        assertEquals(expected1.getAge(), actual1.getAge());
        assertEquals(expected1.getFirstName(), actual1.getFirstName());
        assertEquals(expected1.getLastName(), actual1.getLastName());
        assertEquals(expected1.getMiddleName(), actual1.getMiddleName());
        assertEquals(expected1.getCreateDate(), actual1.getCreateDate());
        assertEquals(expected1.getUpdateDate(), actual1.getUpdateDate());

        var expected2 = StudentRequest.builder()
                .age(50)
                .firstName("John")
                .lastName("Doe")
                .middleName("Smith")
                .build();
        studentService.update(2L, expected2);
        var actual2 = studentRepository.findById(2L).get();

        assertEquals(2L, actual2.getId());
        assertEquals(expected2.getAge(), actual2.getAge());
        assertEquals(expected2.getFirstName(), actual2.getFirstName());
        assertEquals(expected2.getLastName(), actual2.getLastName());
        assertEquals(expected2.getMiddleName(), actual2.getMiddleName());

        assertEquals(actual1.getCreateDate(), actual2.getCreateDate());
        assertNotEquals(expected1.getUpdateDate(), actual2.getUpdateDate());
    }

    @Test
    void deleteById() {
        var actual = studentRepository.findById(2L);
        assertTrue(actual.isPresent());

        studentService.deleteById(2L);

        var actual2 = studentRepository.findById(2L);
        assertFalse(actual2.isPresent());
    }
}
