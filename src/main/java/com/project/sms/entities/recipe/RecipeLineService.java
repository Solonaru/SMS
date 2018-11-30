package com.project.sms.entities.recipe;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeLineService implements IRecipeLineService {

	@Autowired
	private IRecipeLineRepository recipeLineRepository;

	public Optional<RecipeLine> findRecipeLineById(int recipeLineId) {
		return recipeLineRepository.findById(recipeLineId);
	}

	public List<RecipeLine> findAllRecipeLines() {
		return (List<RecipeLine>) recipeLineRepository.findAll();
	}

	public void insertRecipeLine(RecipeLine recipeLine) {
		recipeLineRepository.save(recipeLine);
	}

	public void updateRecipeLine(RecipeLine recipeLine) {
		recipeLineRepository.save(recipeLine);
	}

	public void deleteRecipeLineById(int recipeLineId) {
		recipeLineRepository.deleteById(recipeLineId);
	}

}
