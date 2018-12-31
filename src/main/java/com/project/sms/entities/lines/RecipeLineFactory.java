package com.project.sms.entities.lines;

import com.project.sms.entities.recipe.RecipeLine;

public class RecipeLineFactory implements ILineAbstractFactory {

	private Integer quantity;

	// ----- Constructors -----
	public RecipeLineFactory(Integer quantity) {
		this.quantity = quantity;
	}

	// ----- Methods -----
	public ILine createLine() {
		return new RecipeLine(quantity);
	}

}
