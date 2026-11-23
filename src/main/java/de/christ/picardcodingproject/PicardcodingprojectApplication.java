package de.christ.picardcodingproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PicardcodingprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicardcodingprojectApplication.class, args);
	}

}
