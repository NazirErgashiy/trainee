package uz.nazir.trainee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Check context loading
 */
@ContextConfiguration(initializers = IntegrationTestInitializr.class)
@SpringBootTest
class TraineeApplicationTests {

    /**
     * Environment
     */
    @Autowired
    Environment env;

    /**
     * Environment should not be null
     */
    @Test
    void contextLoads() {
        assertNotNull(env);
    }
}
