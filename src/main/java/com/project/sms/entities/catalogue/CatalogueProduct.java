package com.project.sms.entities.catalogue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.project.sms.entities.product.Product;

@Entity
@NamedQuery(name = "CatalogueProduct.findAll", query = "SELECT cp FROM CatalogueProduct cp")
public class CatalogueProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalogue_product_generator")
	@SequenceGenerator(name = "catalogue_product_generator", sequenceName = "catalogue_product_sequence", initialValue = 900000001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private Double price;
	@ManyToOne
	private Catalogue catalogue;
	@ManyToOne
	private Product product;

	// ----- Constructors -----
	public CatalogueProduct() {
		super();
	}

	public CatalogueProduct(Double price) {
		super();
		this.price = price;
	}

	// ----- Getters and Setters -----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Catalogue getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
