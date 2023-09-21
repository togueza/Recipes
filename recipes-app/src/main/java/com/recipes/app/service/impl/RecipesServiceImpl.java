package com.recipes.app.service.impl;

import com.recipes.app.dto.Results;
import com.recipes.app.entity.Recipes;
import com.recipes.app.repository.RecipesRepository;
import com.recipes.app.service.RecipesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipesServiceImpl implements RecipesService {

	@Autowired
	private RecipesRepository recipesRepository;

	
	@Override
	public ResponseEntity<List<Recipes>> listAll() {
		try {
			List<Recipes> listRecipes = new ArrayList<Recipes>();
			recipesRepository.findAll().forEach(listRecipes::add);
			return new ResponseEntity<>(listRecipes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Recipes> findById(Long id){
		Optional<Recipes> recipesData = recipesRepository.findById(id);
		if (recipesData.isPresent()) {
			return new ResponseEntity<>(recipesData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Recipes add(Recipes recipes) {
		try {
			return recipesRepository.save(recipes);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Recipes> addList(List<Results> recipeslist) {

		List<Recipes> entities = new ArrayList<>();
		ModelMapper mapper = new ModelMapper();
		recipeslist.forEach(recipe ->
				entities.add(mapper.map(recipe, Recipes.class)));
		return recipesRepository.saveAllAndFlush(entities);
	}

	@Override
	public ResponseEntity<Recipes> update(Long id, int rate) {
		Optional<Recipes> recipesData = recipesRepository.findById(id);
		Recipes recipe = recipesData.get();
		recipe.setRate(rate);
		if (recipesData.isPresent()) {
			recipesRepository.save(recipe);
			return new ResponseEntity<>(recipesData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
