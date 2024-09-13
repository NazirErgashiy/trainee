package uz.nazir.trainee.services.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import uz.nazir.trainee.DbTestUtil;
import uz.nazir.trainee.IntegrationTestInitializr;
import uz.nazir.trainee.TestDataGenerator;
import uz.nazir.trainee.dto.request.SubjectRequest;
import uz.nazir.trainee.dto.response.SubjectResponse;
import uz.nazir.trainee.entities.Subject;
import uz.nazir.trainee.mappers.SubjectMapper;
import uz.nazir.trainee.repositories.SubjectRepository;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uz.nazir.trainee.TestDataGenerator.RANDOM;

@ContextConfiguration(initializers = IntegrationTestInitializr.class)
@SpringBootTest
//DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SubjectServiceTest {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ApplicationContext applicationContext;

    private List<SubjectResponse> testData;

    @BeforeEach
    void setUp() throws SQLException {
        DbTestUtil.resetAutoIncrementColumns(applicationContext, "_SUBJECTS");
        testData = TestDataGenerator.getInstance().createAndGetElements(
                applicationContext,
                5,
                SubjectRepository.class,
                SubjectMapper.class,
                () -> Subject.builder()
                        .name("TEST_NAME" + (RANDOM.nextInt(5000)))
                        .build());
    }

    @AfterEach
    void tearDown() {
        subjectRepository.deleteAll();
    }

    @Test
    void readAll() {
        var actual = subjectService.readAll(Pageable.unpaged()).getContent();

        for (int i = 0; i < testData.size(); i++) {
            var actualStudent = actual.get(i);
            var expectedStudent = testData.get(i);

            assertEquals(expectedStudent.getId(), actualStudent.getId());
            assertEquals(expectedStudent.getName(), actualStudent.getName());
            assertEquals(expectedStudent.getCreateDate(), actualStudent.getCreateDate());
            assertEquals(expectedStudent.getUpdateDate(), actualStudent.getUpdateDate());
        }
    }

    @Test
    void readById() {
        var actual = subjectService.readById(2L);
        var expected = testData.get(1);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCreateDate(), actual.getCreateDate());
        assertEquals(expected.getUpdateDate(), actual.getUpdateDate());
    }

    @Test
    void create() {
        var expected = SubjectRequest.builder()
                .name("Smith")
                .build();

        var actual = subjectService.create(expected);

        assertNotNull(actual.getId());
        assertNotNull(actual.getCreateDate());
        assertNotNull(actual.getUpdateDate());

        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void update() {
        var actual1 = subjectRepository.findById(2L).get();
        var expected1 = testData.get(1);

        assertEquals(expected1.getId(), actual1.getId());
        assertEquals(expected1.getName(), actual1.getName());
        assertEquals(expected1.getCreateDate(), actual1.getCreateDate());
        assertEquals(expected1.getUpdateDate(), actual1.getUpdateDate());

        var expected2 = SubjectRequest.builder()
                .name("Smith")
                .build();
        subjectService.update(2L, expected2);
        var actual2 = subjectRepository.findById(2L).get();

        assertEquals(2L, actual2.getId());
        assertEquals(expected2.getName(), actual2.getName());

        assertEquals(actual1.getCreateDate(), actual2.getCreateDate());
        assertNotEquals(expected1.getUpdateDate(), actual2.getUpdateDate());
    }

    @Test
    void deleteById() {
        var actual = subjectRepository.findById(2L);
        assertTrue(actual.isPresent());

        subjectService.deleteById(2L);

        var actual2 = subjectRepository.findById(2L);
        assertFalse(actual2.isPresent());
    }
}
