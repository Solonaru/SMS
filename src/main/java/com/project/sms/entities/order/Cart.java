package com.project.sms.entities.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.sms.entities.lines.ILine;
import com.project.sms.entities.lines.ILineIterator;
import com.project.sms.entities.promotion.Promotion;

@Entity
@NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c")
public class Cart implements Serializable, ILineIterator {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_generator")
	@SequenceGenerator(name = "cart_generator", sequenceName = "cart_sequence", initialValue = 100000001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	@OneToOne(mappedBy = "cart")
	@JsonIgnoreProperties(value = "cart")
	private Orders order;
	@OneToMany(mappedBy = "cart")
	@JsonIgnoreProperties(value = "cart")
	private List<CartLine> cartLines = new ArrayList<CartLine>();
	@ManyToOne
	private Promotion promotion;

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

	public List<CartLine> getCartLines() {
		return cartLines;
	}

	public void setCartLines(List<CartLine> cartLines) {
		this.cartLines = cartLines;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	// ----- Methods -----
	public void addLine(CartLine cartLine) {
		cartLines.add(cartLine);
		cartLine.setCart(this);
	}

	public Double getTotalValue() {
		Double total = 0.0;
		Iterator<? extends ILine> linesIterator = this.createLinesIterator();

		while (linesIterator.hasNext()) {
			CartLine cartLine = (CartLine) linesIterator.next();
			total += cartLine.getValue();
		}

		return total;
	}

	public Iterator<? extends ILine> createLinesIterator() {
		return cartLines.iterator();
	}

}
