package com.project.sms.entities.order;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.project.sms.entities.item.Item;

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
	private Item item;
	@ManyToOne
	private Cart cart;

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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	// -----Methods-----
	public String toString() {
		return "Product: " + item + ". Quantity: " + quantity;
	}

}
