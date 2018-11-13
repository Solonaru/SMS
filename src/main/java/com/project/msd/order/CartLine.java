package com.project.msd.order;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.project.msd.catalogue.Catalogue;
import com.project.msd.product.Product;


@Entity
@NamedQuery(name = "CartLine.findAll", query = "SELECT cl FROM CartLine cl")
public class CartLine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartLine_generator")
	@SequenceGenerator(name = "cartLine_generator", sequenceName = "cartLine_sequence", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private Integer quantity;
	@ManyToOne
	private Product product;
	@ManyToOne
	private Cart cart;
	@ManyToOne
	private Catalogue catalogue;

	// -----Constructors-----
	public CartLine() {
		super();
	}

	public CartLine(Integer quantity) {
		super();
		this.quantity = quantity;
	}

	// -----Getters and Setters-----
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Catalogue getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}

	// -----Methods-----
	public String toString() {
		return "Product: " + product + ". Quantity: " + quantity;
	}

}
