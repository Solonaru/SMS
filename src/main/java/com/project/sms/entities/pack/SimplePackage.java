package com.project.sms.entities.pack;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import com.project.sms.entities.item.Item;
import com.project.sms.enums.Month;

@Entity
@NamedQuery(name = "SimplePackage.findAll", query = "SELECT sp FROM SimplePackage sp")
@DiscriminatorValue("Simple package")
public class SimplePackage extends Item {
	private static final long serialVersionUID = 1L;

	@ManyToMany
	@JoinTable(name = "simple_package_items", joinColumns = {
			@JoinColumn(name = "simple_package_id") }, inverseJoinColumns = { @JoinColumn(name = "item_id") })
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

	public Double getPrice() {
		Double price = 0.0;

		for (Item component : packageComponents) {
			price += component.getPrice();
		}

		return price * 0.9;
	}

	public Double getPrice(Month month) {
		Double price = 0.0;

		for (Item component : packageComponents) {
			price += component.getPrice(month);
		}

		return price * 0.9;
	}

}
