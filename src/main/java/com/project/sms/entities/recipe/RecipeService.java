package com.project.sms.entities.recipe;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sms.entities.recipe.Recipe;
import com.project.sms.entities.recipe.IRecipeRepository;

@Service
public class RecipeService implements IRecipeService {

	@Autowired
	private IRecipeRepository recipeRepository;

	public Optional<Recipe> findRecipeById(int recipeId) {
		return recipeRepository.findById(recipeId);
	}

	public List<Recipe> findAllRecipes() {
		return (List<Recipe>) recipeRepository.findAll();
	}

	public void insertRecipe(Recipe recipe) {
		recipeRepository.save(recipe);
	}

	public void updateRecipe(Recipe recipe) {
		recipeRepository.save(recipe);
	}

	public void deleteRecipeById(int recipeId) {
		recipeRepository.deleteById(recipeId);
	}
	
}
