package com.project.sms.entities.recipe;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.project.sms.entities.item.Product;

@Entity
@NamedQuery(name = "Recipe.findAll", query = "SELECT r FROM Recipe r")
@DiscriminatorValue("Recipe")
public class Recipe extends Product {
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "recipe")
	private List<RecipeLine> recipeLines = new ArrayList<RecipeLine>();

	// -------- Constructors ---------
	public Recipe() {
		super();
	}

	public Recipe(String name, Date updateDate) {
		super(name, updateDate);
	}

	// ------- Getters and Setters --------
	public List<RecipeLine> getRecipeLines() {
		return recipeLines;
	}

	public void setRecipeLines(List<RecipeLine> recipeLines) {
		this.recipeLines = recipeLines;
	}

}
