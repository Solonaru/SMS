package com.project.sms.entities.recipe;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipeLine")
public class RecipeLineController {

	@Autowired
	private IRecipeLineService recipeLineService;

	@RequestMapping(value = "/{recipeLineId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<RecipeLine> findRecipeLineById(@PathVariable("recipeLineId") int recipeLineId) {
		return recipeLineService.findRecipeLineById(recipeLineId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RecipeLine> getRecipeLines() {
		return recipeLineService.findAllRecipeLines();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertRecipeLine(@RequestBody RecipeLine recipeLine) {
		recipeLineService.insertRecipeLine(recipeLine);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateRecipeLine(@RequestBody RecipeLine recipeLine) {
		recipeLineService.updateRecipeLine(recipeLine);
	}

	@RequestMapping(value = "/delete/{recipeLineId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteRecipeLine(@PathVariable("recipeLineId") int recipeLineId) {
		recipeLineService.deleteRecipeLineById(recipeLineId);
	}

}
