package aman.project.springbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@ComponentScan({"aman.project.springbootstarter"})
@SpringBootApplication
@Configuration
@EnableSwagger2
public class UserApiApp {
    public static void main(String[] args) {
        SpringApplication.run(UserApiApp.class, args);
    }

    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/api.*"))
                .apis(RequestHandlerSelectors.basePackage("aman.project")).build()
                .apiInfo(apiDetails());
    }
    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Bank API",
                "Sample API for the project tutorial",
                "1.0",
                "Free to Use",
                new springfox.documentation.service.Contact("Aman Jain","n/a","aman.jain@telekom-digital.com"),
                "API License",
                "n/a",
                Collections.emptyList());
    }
}
