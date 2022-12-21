package ru.acorn.JavaRushTelegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JavaRushTelegrambotApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaRushTelegrambotApplication.class, args);
	}
}
