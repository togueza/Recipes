package com.recipes.app.service;


import com.recipes.app.dto.Results;
import com.recipes.app.entity.Recipes;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface RecipesService {

	ResponseEntity<List<Recipes>> listAll();

	ResponseEntity<Recipes> findById(Long id);

	Recipes add(Recipes recipes);

	List<Recipes> addList(List<Results>recipes);

	ResponseEntity<Recipes> update(Long id, int rate);


}
