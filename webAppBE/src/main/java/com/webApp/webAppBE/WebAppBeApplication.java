package com.webApp.webAppBE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableJpaRepositories("com.webApp.webAppBE.Repositories")
@EntityScan("com.webApp.webAppBE.Models")
@SpringBootApplication
@EnableAsync
public class WebAppBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAppBeApplication.class, args);
	}

}
