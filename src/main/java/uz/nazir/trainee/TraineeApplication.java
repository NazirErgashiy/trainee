package uz.nazir.trainee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import uz.nazir.trainee.util.StartupHelper;

/**
 * Application start point...
 */
@SpringBootApplication
public class TraineeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TraineeApplication.class, args);
        StartupHelper.logApplicationStartup(run.getEnvironment());
    }

}
