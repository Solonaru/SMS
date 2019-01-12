package com.project.sms.entities.recipe;

import java.sql.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapKey;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.sms.entities.item.Product;
import com.project.sms.entities.lines.ILine;
import com.project.sms.entities.lines.ILineIterator;
import com.project.sms.enums.ComponentType;

@Entity
@NamedQuery(name = "Recipe.findAll", query = "SELECT r FROM Recipe r")
@DiscriminatorValue("Recipe")
public class Recipe extends Product implements ILineIterator {
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "recipe")
	@MapKey(name = "componentType")
	@JsonIgnoreProperties(value = { "recipe", "category" })
	private Map<ComponentType, RecipeLine> recipeLines = new TreeMap<ComponentType, RecipeLine>();

	// ----- Constructors -----
	public Recipe() {
		super();
	}

	public Recipe(String name, Integer stockQuantity, Date updateDate, String description) {
		super(name, stockQuantity, updateDate, description);
	}

	public Map<ComponentType, RecipeLine> getRecipeLines() {
		return recipeLines;
	}

	public void setRecipeLines(Map<ComponentType, RecipeLine> recipeLines) {
		this.recipeLines = recipeLines;
	}

	// ----- Methods -----
	public void addLine(RecipeLine recipeLine) {
		recipeLines.put(recipeLine.getComponentType(), recipeLine);
		recipeLine.setRecipe(this);
	}

	public Iterator<? extends ILine> createLinesIterator() {
		return recipeLines.values().iterator();
	}

}
