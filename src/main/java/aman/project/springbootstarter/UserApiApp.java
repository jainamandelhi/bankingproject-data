package aman.project.springbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApiApp {
	public static void main(String[] args) {
		System.setProperty("server.port", "8080");
		SpringApplication.run(UserApiApp.class, args);

	}
}
 