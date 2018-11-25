package com.project.sms.entities.item;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Component.findAll", query = "SELECT c FROM Component c")
public abstract class Component extends Product {
	private static final long serialVersionUID = 1L;

	// ----- Constructors -----
	public Component() {
		super();
	}

	public Component(String name, Integer stockQuantity, Date updateDate, String description) {
		super(name, stockQuantity, updateDate, description);
	}
}
