package com.example.passwordservice;

import com.example.passwordservice.helper.AesEncryptionHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.passwordservice"})
@EntityScan(basePackages = {"com.example.passwordservice"})
@EnableMongoRepositories(basePackages = {"com.example.passwordservice"})
public class PasswordServiceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PasswordServiceApplication.class, args);
	}
}
