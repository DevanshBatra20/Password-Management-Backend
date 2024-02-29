package com.example.passwordservice;

import com.example.passwordservice.helper.AesEncryptionHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PasswordServiceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PasswordServiceApplication.class, args);
	}
}
