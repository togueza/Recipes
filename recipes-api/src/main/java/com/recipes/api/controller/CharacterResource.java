package com.recipes.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.recipes.api.model.Recipes;
import com.recipes.api.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipes.api.services.CharacterService;

@RestController
@RequestMapping("/v1/characters")
public class CharacterResource {
	@Autowired
	private CharacterService characterService;
	
	@GetMapping
	public ResponseEntity<Response<List<Recipes>>> listAll() {
		List<Recipes> recipes = characterService.listAll();
		
		if (recipes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return ResponseEntity.ok(new Response<List<Recipes>>(recipes));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<Recipes>> getById(@PathVariable Long id) {
		Recipes recipes = this.characterService.listById(id);
		
		if (recipes == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(new Response<Recipes>(recipes));
	}
	
	@GetMapping("/findByName/{name}")
	public ResponseEntity<Response<List<Recipes>>> getByName(@PathVariable String name) {
		List<Recipes> recipes = this.characterService.listByName(name);
		
		if (recipes == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(new Response<List<Recipes>>(recipes));
	}
		
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Response<Recipes>> add(@Valid @RequestBody Recipes recipes, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Recipes>(erros));
		}
		
		return new ResponseEntity<>(new Response<Recipes>(this.characterService.add(recipes)), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<Recipes>> update(@PathVariable Long id, @Valid @RequestBody Recipes recipes, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Recipes>(erros));
		}
		
		if (this.characterService.listById(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
		
		recipes.setId(id);
		return ResponseEntity.ok(new Response<Recipes>(this.characterService.update(recipes)));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Response<Recipes>> patchUpdate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
		if (this.characterService.listById(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(new Response<Recipes>(this.characterService.partialUpdate(id, updates)));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Integer>> remove(@PathVariable Long id) {
		if (this.characterService.listById(id) == null) {
			return new ResponseEntity<>(new Response<Integer>("No character found for id: " + id), HttpStatus.NOT_FOUND);
		} 
		
		characterService.remove(id);
		return ResponseEntity.ok(new Response<Integer>("Recipes deleted!"));
	}
	
}
