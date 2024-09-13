package uz.nazir.trainee;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

/**
 * Used with annotation ContextConfiguration(initializers = IntegrationTestInitializr.class)
 * Mostly for Controller testing
 */
public class IntegrationTestInitializr implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    /**
     * Start Container (TestContainers)
     */
    private static void startContainers() {
        Startables.deepStart(Stream.of(postgres)).join();
        // we can add further containers
        // here like rabbitmq or other databases
    }

    /**
     * Configure environment variables
     *
     * @return Key-Value
     */
    private static Map<String, String> createConnectionConfiguration() {
        return Map.of(
                "spring.datasource.url", postgres.getJdbcUrl(),
                "spring.datasource.username", postgres.getUsername(),
                "spring.datasource.password", postgres.getPassword()
        );
    }

    /**
     * Override context with params
     *
     * @param applicationContext context
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        startContainers();
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        MapPropertySource testcontainers = new MapPropertySource(
                "testcontainers", (Map) createConnectionConfiguration()
        );
        environment.getPropertySources().addFirst(testcontainers);
    }
}
