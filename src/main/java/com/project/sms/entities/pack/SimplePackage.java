package com.project.sms.entities.pack;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.project.sms.entities.item.Item;

@Entity
@NamedQuery(name = "SimplePackage.findAll", query = "SELECT sp FROM SimplePackage sp")
@DiscriminatorValue("Simple package")
public class SimplePackage extends Item {
	private static final long serialVersionUID = 1L;

	@OneToMany
	List<Item> packageComponents = new ArrayList<Item>();

	// ------ Constructors -------
	public SimplePackage() {
		super();
	}

	public SimplePackage(String name, Integer stockQuantity, Date updateDate, String description) {
		super(name, stockQuantity, updateDate, description);
	}

	// ----- Getters and Setters -----
	public List<Item> getPackageComponents() {
		return packageComponents;
	}

	public void setPackageComponents(List<Item> packageComponents) {
		this.packageComponents = packageComponents;
	}

	// ----- Methods -----
	public void addComponent(Item component) {
		packageComponents.add(component);
	}

}
