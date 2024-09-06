package uz.nazir.trainee;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Used with annotation ContextConfiguration(initializers = IntegrationTestInitializr.class)
 * Mostly for Controller testing
 */
public class IntegrationTestInitializr implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    /**
     * Init
     */
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment env = applicationContext.getEnvironment();
    }
}
