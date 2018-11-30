package com.project.sms.entities.recipe;

import java.util.List;
import java.util.Optional;

public interface IRecipeLineService {

	public Optional<RecipeLine> findRecipeLineById(int recipeLineId);

	public List<RecipeLine> findAllRecipeLines();

	public void insertRecipeLine(RecipeLine recipeLine);

	public void updateRecipeLine(RecipeLine recipeLine);

	public void deleteRecipeLineById(int recipeLineId);

}
