package com.recipes.app.repository;

import com.recipes.app.entity.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipesRepository extends JpaRepository<Recipes, Long> {

}
