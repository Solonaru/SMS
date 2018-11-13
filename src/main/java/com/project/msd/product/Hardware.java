package com.project.msd.product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Hardware.findAll", query = "SELECT h FROM Hardware h")
@DiscriminatorValue("Hardware")
public class Hardware extends Product {
	private static final long serialVersionUID = 1L;

	// ----- Constructors -----
	public Hardware() {
		super();
	}

	public Hardware(String name, Integer unitsInStock) {
		super(name, unitsInStock);
	}
	
}
