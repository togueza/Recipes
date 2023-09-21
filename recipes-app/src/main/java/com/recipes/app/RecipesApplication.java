package com.recipes.app;

import com.recipes.app.entity.UserTable;
import com.recipes.app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RecipesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipesApplication.class, args);
	}

	@Bean
	protected CommandLineRunner init(final UserRepository userRepository) {

		return args -> {
			UserTable userTable = new UserTable();
			userTable.setName("User_Recipes");
			userTable.setPassword("recipes");
			userTable.setUsername("recipes");

			userRepository.save(userTable);

		};

	}
}
