package aman.project.springbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@ComponentScan({"aman.project.springbootstarter"})
@SpringBootApplication
public class UserApiApp {
    public static void main(String[] args) {
        SpringApplication.run(UserApiApp.class, args);
    }
}
