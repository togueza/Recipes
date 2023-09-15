package com.recipes.api.services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.recipes.api.model.Recipes;

public interface CharacterService {

	List<Recipes> listAll();

	Recipes listById(Long id);

	List<Recipes> listByName(String name);
	
	Recipes add(@Valid Recipes recipes);

	Recipes update(@Valid Recipes recipes);
	
	Recipes partialUpdate(Long id, Map<String, Object> updates);
	
	void remove(Long id);

}
