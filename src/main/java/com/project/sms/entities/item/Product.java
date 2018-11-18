package com.project.sms.entities.item;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public abstract class Product extends Item {
	private static final long serialVersionUID = 1L;

	// ----- Constructors -----
	public Product() {
		super();
	}

	public Product(String name, Date updateDate) {
		super(name, updateDate);
	}
}
