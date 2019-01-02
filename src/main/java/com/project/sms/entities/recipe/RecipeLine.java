package com.project.sms.entities.recipe;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.sms.entities.item.Component;
import com.project.sms.entities.lines.ILine;
import com.project.sms.enums.ComponentType;

@Entity
@NamedQuery(name = "RecipeLine.findAll", query = "SELECT r FROM RecipeLine r")
public class RecipeLine implements Serializable, ILine {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_line_generator")
	@SequenceGenerator(name = "recipe_line_generator", sequenceName = "recipe_line_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private ComponentType componentType;
	private Integer quantity;
	@ManyToOne
	@JoinColumn(name = "recipe_id")
	@JsonIgnoreProperties(value = "category")
	private Recipe recipe;
	@ManyToOne
	@JsonIgnoreProperties(value = "category")
	private Component component;

	// ------- Constructors ------
	public RecipeLine() {
		super();
	}

	public RecipeLine(ComponentType componentType, Integer quantity) {
		super();
		this.componentType = componentType;
		this.quantity = quantity;
	}

	// ------ Getters and Setters -------
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNeededQuantity() {
		return quantity;
	}

	public void setNeededQuantity(Integer neededQuantity) {
		this.quantity = neededQuantity;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public ComponentType getComponentType() {
		return componentType;
	}

	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	// ----- Methods -----
	public ILine makeCopy() {
		RecipeLine recipeLine = null;

		try {
			recipeLine = (RecipeLine) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return recipeLine;
	}

}
