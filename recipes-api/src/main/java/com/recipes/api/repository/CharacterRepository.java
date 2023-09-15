package com.recipes.api.repository;

import com.recipes.api.model.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Recipes, Long> {
	/**
	 * Find character by Name
	 * 
	 * @param name
	 * @return character
	 */
	List<Recipes> findByNameIgnoreCaseContaining(String name);
}
