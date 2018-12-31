package com.project.sms.entities.lines;

import com.project.sms.entities.order.CartLine;

public class CartLineFactory implements ILineAbstractFactory {

	private Integer quantity;

	// ----- Constructors -----
	public CartLineFactory(Integer quantity) {
		this.quantity = quantity;
	}

	// ----- Methods -----
	public ILine createLine() {
		return new CartLine(quantity);
	}

}
