package com.project.sms.entities.lines;

import com.project.sms.entities.recipe.RecipeLine;
import com.project.sms.enums.ComponentType;

public class RecipeLineFactory implements ILineAbstractFactory {

	private ComponentType componentType;
	private Integer quantity;

	// ----- Constructors -----
	public RecipeLineFactory(ComponentType componentType, Integer quantity) {
		this.componentType = componentType;
		this.quantity = quantity;
	}

	// ----- Methods -----
	public ILine createLine() {
		return new RecipeLine(componentType, quantity);
	}

}
