package com.project.sms.entities.order;

import java.util.List;
import java.util.Optional;

public interface ICartLineService {

public Optional<CartLine> findCartLineById(int cartLineId);
	
	public List<CartLine> findAllCartLines();

	public void insertCartLine(CartLine cartLine);

	public void updateCartLine(CartLine cartLine);

	public void deleteCartLineById(int cartLine);
}
