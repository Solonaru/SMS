package com.project.sms.entities.recipe;

import java.util.List;
import java.util.Optional;

public interface IRecipeService {
	
	public Optional<Recipe> findRecipeById(int recipeId);

	public List<Recipe> findAllRecipes();

	public void insertRecipe(Recipe recipe);

	public void updateRecipe(Recipe recipe);

	public void deleteRecipeById(int recipeId);

}
