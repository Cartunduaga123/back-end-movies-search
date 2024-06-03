package com.unir.movie_app_search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MovieAppSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieAppSearchApplication.class, args);
	}

}
