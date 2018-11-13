package com.project.msd.order;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c")
public class Cart implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_generator")
	@SequenceGenerator(name = "cart_generator", sequenceName = "cart_sequence", initialValue = 100000001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
/*	@OneToMany(mappedBy = "cart")
	private List<CartLine> cartLines = new ArrayList<CartLine>();*/
	
	// -----Constructors-----
	public Cart() {
		super();
	}
	
	// -----Getters and Setters-----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public List<CartLine> getCartLines() {
//		return cartLines;
//	}
//
//	public void setCartLines(List<CartLine> cartLines) {
//		this.cartLines = cartLines;
//	}
}
