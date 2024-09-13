package uz.nazir.trainee.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import uz.nazir.trainee.DbTestUtil;
import uz.nazir.trainee.IntegrationTestInitializr;
import uz.nazir.trainee.TestDataGenerator;
import uz.nazir.trainee.dto.request.TeacherRequest;
import uz.nazir.trainee.entities.Teacher;
import uz.nazir.trainee.mappers.TeacherMapper;
import uz.nazir.trainee.repositories.TeacherRepository;

import java.io.IOException;
import java.sql.SQLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static uz.nazir.trainee.TestDataGenerator.RANDOM;

@ContextConfiguration(initializers = IntegrationTestInitializr.class)
@SpringBootTest
@WebAppConfiguration
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TeacherControllerTest {

    private String checkPath = "teachers";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private TeacherRepository teacherRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        mockMvc = webAppContextSetup(webApplicationContext)
                .build();

        DbTestUtil.resetAutoIncrementColumns(context, "_TEACHERS");
        TestDataGenerator.getInstance().createAndGetElements(
                context,
                5,
                TeacherRepository.class,
                TeacherMapper.class,
                () -> Teacher.builder()
                        .age(RANDOM.nextInt(100))
                        .firstName("TEST_NAME" + (RANDOM.nextInt(5000)))
                        .lastName("TEST_LASTNAME" + (RANDOM.nextInt(5000)))
                        .middleName("TEST_MIDDLENAME" + (RANDOM.nextInt(5000)))
                        .build());
    }

    @AfterEach
    public void tearDown() {
        teacherRepository.deleteAll();
    }

    /**
     * 200
     */
    @Test
    void readPaged() throws Exception {
        mockMvc.perform(get("/api/v1/" + checkPath)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 201
     */
    @Test
    void create() throws Exception {
        var request = createBasicStudent();

        mockMvc.perform(post("/api/v1/" + checkPath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(request))
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    /**
     * 200
     */
    @Test
    void readById() throws Exception {
        mockMvc.perform(get("/api/v1/" + checkPath + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 404
     */
    @Test
    void readByIdAndGetNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/" + checkPath + "/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /**
     * 200
     */
    @Test
    void update() throws Exception {
        var request = createBasicStudent();

        mockMvc.perform(patch("/api/v1/" + checkPath + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(request))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 404
     */
    @Test
    void updateAndGetNotFound() throws Exception {
        var request = createBasicStudent();

        mockMvc.perform(patch("/api/v1/" + checkPath + "/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(request))
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /**
     * 204
     */
    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/v1/" + checkPath + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    /**
     * 404
     */
    @Test
    void deleteByIdAndGetNotFound() throws Exception {
        mockMvc.perform(delete("/api/v1/" + checkPath + "/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /**
     * Creates Basic Student - DRY
     */
    private TeacherRequest createBasicStudent() {
        return TeacherRequest.builder()
                .age(20)
                .firstName("John")
                .lastName("Doe")
                .middleName("Smith")
                .build();
    }

    /**
     * Object to json with JACKSON(ObjectMapper)
     */
    private String json(Object o) throws IOException {
        return objectMapper.writeValueAsString(o);
    }
}
