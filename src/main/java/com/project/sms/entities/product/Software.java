package com.project.sms.entities.product;

import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Software.findAll", query = "SELECT s FROM Software s")
@DiscriminatorValue("Software")
public class Software extends Product {
	private static final long serialVersionUID = 1L;

	private String version;

	// -----Constructors-----
	public Software() {

		super();
	}

	public Software(String name, Integer unitsInStock, String version, Date updateDate) {
		super(name, unitsInStock, updateDate);
		this.version = version;
	}

	// -----Getters and Setters-----
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	// -----Methods-----
	public String toString() {
		return "Name: " + name;
	}
}