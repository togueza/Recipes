package com.recipes.app.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.recipes.app.dto.RecipesResponse;
import com.recipes.app.dto.Results;
import com.recipes.app.entity.Recipes;
import com.recipes.app.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class RecipesController {

	private static final String APIKEY = "7945ba8f42e54f8fa4b5ffe6e97ab5f7";

	@Value("${spoonacular.urls.base}${spoonacular.urls.search}")
	private String spoonacularURecipes;

	@Value("${spoonacular.urls.base}${spoonacular.urls.recipeid}")
	private String spoonacularURecipesInformation;


	@Autowired
	private RecipesService recipesService;

	public URI uriBuilderRecipes(String apiKey, String query) {

		return UriComponentsBuilder.fromHttpUrl(spoonacularURecipes)
				.queryParam("apiKey", APIKEY)
				.queryParam("query", query)
				.build()
				.toUri();
	}

	public URI uriBuilderRecipesInformation(String recipe_id) {

		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("recipe_id", recipe_id);

		return UriComponentsBuilder.fromHttpUrl(spoonacularURecipesInformation)
				.queryParam("apiKey", APIKEY)
				.buildAndExpand(urlParams)
				.toUri();
	}


	@GetMapping("recipes/search")
	public ResponseEntity<RecipesResponse> getRecipes(String query) {
		URI uri = uriBuilderRecipes(APIKEY, query);
        System.out.println("Recipes url " + uri);
		ResponseEntity<RecipesResponse> result = new RestTemplate().getForEntity(uri, RecipesResponse.class);
		List<Results> listRecipes =  result.getBody().getResults();
		recipesService.addList(listRecipes);
		return new RestTemplate().getForEntity(uri, RecipesResponse.class);
	}

	@GetMapping("recipes/information")
	public ResponseEntity<Results> getRecipesInformation(String recipe_id) {
		URI uri = uriBuilderRecipesInformation(recipe_id);
		System.out.println("Recipes url " + uri);
		return new RestTemplate().getForEntity(uri, Results.class);
	}

	@GetMapping("recipes/findrecipe")
	public ResponseEntity<Recipes> findRecipeById(Long recipe_id) {
		ResponseEntity<Recipes> recipeFinded = recipesService.findById(recipe_id);
		return recipeFinded;
	}

	@GetMapping("recipes/listallrecipes")
	public ResponseEntity<List<Recipes>> listall() {
		ResponseEntity<List<Recipes>> recipeFinded = recipesService.listAll();
		return recipeFinded;
	}

	@GetMapping("recipes/addrate")
	public ResponseEntity<Recipes> addrate(Long id, int rate) {
		ResponseEntity<Recipes> recipeFinded = recipesService.update(id, rate);
		return recipeFinded;
	}
}
