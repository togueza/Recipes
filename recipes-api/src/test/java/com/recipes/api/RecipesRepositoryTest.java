package com.recipes.api;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import com.recipes.api.model.Recipes;
import com.recipes.api.repository.CharacterRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipesRepositoryTest {
	@Autowired
    private CharacterRepository characterRepository;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShouldPersistData() {
        Recipes recipes = new Recipes("Bucky", "Capitain's old friend", "Strength, Steel arm");
        this.characterRepository.save(recipes);
        assertThat(recipes.getId()).isNotNull();
        assertThat(recipes.getName()).isEqualTo("Bucky");
        assertThat(recipes.getDescription()).isEqualTo("Capitain's old friend");
        assertThat(recipes.getSuperPowers()).isEqualTo("Strength, Steel arm");
    }

    @Test
    public void deleteShouldRemoveData() {
    	Recipes recipes = new Recipes("Bucky", "Capitain's old friend", "Strength, Steel arm");
        this.characterRepository.save(recipes);
        characterRepository.delete(recipes);
        Assertions.assertThat(characterRepository.findOne(recipes.getId())).isNull();
    }

    @Test
    public void updateShouldChangeAndPersistData() {
    	Recipes recipes = new Recipes("Bucky", "Capitain's old friend", "Strength, Steel arm");
        this.characterRepository.save(recipes);
        recipes.setName("Bucky222");
        recipes.setDescription("Capitain's best friend");
        this.characterRepository.save(recipes);
        recipes = this.characterRepository.findOne(recipes.getId());
        assertThat(recipes.getName()).isEqualTo("Bucky222");
        assertThat(recipes.getDescription()).isEqualTo("Capitain's best friend");
    }

    /*@Test
    public void findByNameIgnoreCaseContainingShouldIgnoreCase() {
    	Recipes character = new Recipes("Bucky", "Capitain's old friend", "Strength, Steel arm");
        this.characterRepository.save(character);
        Recipes returnCharacter = (Recipes) characterRepository.findByNameIgnoreCaseContaining("bucky");
        assertThat(returnCharacter.getName()).isEqualTo("Bucky");
    }*/

    @Test
    public void createWhenNameIsNullShouldThrowConstraintViolationException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Name attribute is mandatory");
        this.characterRepository.save(new Recipes());
    }
}
