package com.project.sms.entities.pack;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.project.sms.entities.item.Product;

@Entity
@NamedQuery(name = "PackageLine.findAll", query = "SELECT p FROM PackageLine p")
public class PackageLine {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "package_line_generator")
	@SequenceGenerator(name = "package_line_generator", sequenceName = "package_line_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private Integer quantity;
	@ManyToOne
	private Package pack;
	@ManyToOne
	private Product product;

	// ----- Constructors -----
	public PackageLine() {
		super();
	}

	public PackageLine(Integer quantity) {
		super();
		this.quantity = quantity;
	}

	// ----- Getters and Setters -----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Package getPack() {
		return pack;
	}

	public void setPack(Package pack) {
		this.pack = pack;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
