package com.project.sms.entities.item;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Component.findAll", query = "SELECT c FROM Component c")
public abstract class Component extends Product {
	private static final long serialVersionUID = 1L;
	
	protected Integer units;

	// ----- Constructors -----
	public Component() {
		super();
	}

	public Component(String name, Date updateDate, Integer units) {
		super(name, updateDate);
		this.units = units;
	}

	// ----- Getters and Setters -----
	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer unitsInStock) {
		this.units = unitsInStock;
	}	
}
