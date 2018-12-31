package com.project.sms.entities.order;

import java.util.HashMap;

import com.project.sms.utils.LineCloneFactory;

public class CartLineFactory {

	private static final HashMap<Integer, CartLine> cartLinesByQuantity = new HashMap<Integer, CartLine>();

	public static CartLine getCartLine(Integer quantity) {
		LineCloneFactory lineCloneFactory = new LineCloneFactory();
		CartLine cartLine = (CartLine) cartLinesByQuantity.get(quantity);

		if (cartLine == null) {
			cartLine = new CartLine(quantity);
			cartLinesByQuantity.put(quantity, cartLine);
		} else {
			cartLine = (CartLine) lineCloneFactory.getClone(cartLine);
		}

		return cartLine;
	}
}
