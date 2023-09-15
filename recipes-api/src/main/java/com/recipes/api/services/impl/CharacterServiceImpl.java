package com.recipes.api.services.impl;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.recipes.api.model.Recipes;
import com.recipes.api.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipes.api.services.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService {

	@Autowired
	private CharacterRepository characterRepository;
	
	@Override
	public List<Recipes> listAll() {
		return characterRepository.findAll();
	}

	@Override
	public Recipes listById(Long id) {
//		return characterRepository.findById(id).get();
		return characterRepository.findOne(id);
	}
	
	@Override
	public List<Recipes> listByName(String name) {
		return characterRepository.findByNameIgnoreCaseContaining(name);
	}

	@Override
	public Recipes add(@Valid Recipes recipes) {
		return characterRepository.save(recipes);
	}

	@Override
	public Recipes update(@Valid Recipes recipes) {
		return characterRepository.save(recipes);
	}
	
	@Override
	public Recipes partialUpdate(Long id, Map<String, Object> updates) {
		Recipes recipes = listById(id);
		
		updates.forEach((key, value) -> {
			switch (key) {
			case "name":
				recipes.setName(value.toString());
				break;

			case "description":
				recipes.setDescription(value.toString());
				break;

			case "superPowers":
				recipes.setSuperPowers(value.toString());
				break;
			}
		});
		
		return characterRepository.save(recipes);
	}

	@Override
	public void remove(Long id) {
//		characterRepository.deleteById(id);
		characterRepository.delete(id);
	}

}
