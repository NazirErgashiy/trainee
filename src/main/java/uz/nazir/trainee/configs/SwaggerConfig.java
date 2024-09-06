package uz.nazir.trainee.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Configuration
 */
@Configuration
@ComponentScan("uz.nazir.trainee.controllers")
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Basic swagger configuration
     *
     * @return Docket
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("uz.nazir.trainee.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * API INFO
     *
     * @return filled api info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("CRUD")
                .version("0.0.1-SNAPSHOT")
                .contact(new Contact("Nazir", "https://github.com/NazirErgashiy", "pikobiznes@gmail.com"))
                .build();
    }
}

