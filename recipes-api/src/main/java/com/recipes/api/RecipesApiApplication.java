package com.recipes.api;

import com.recipes.api.model.User;
import com.recipes.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RecipesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipesApiApplication.class, args);
	}
	
	/**
     * This method creates the admin user
     * */
    @Bean
    protected CommandLineRunner init(final UserRepository userRepository) {

        return args -> {
            User user = new User();
            user.setUsername("test");
            user.setPassword("marvel");
            user.setName("Test");
            userRepository.save(user);

        };
    }

}
