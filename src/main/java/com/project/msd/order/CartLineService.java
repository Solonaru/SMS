package com.project.msd.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartLineService implements ICartLineService{

	@Autowired
	private ICartLineRepository cartLineRepository;

	public Optional<CartLine> findCartLineById(int cartLineId) {
		return cartLineRepository.findById(cartLineId);
	}
	
	public List<CartLine> findAllCartLines() {
		return (List<CartLine>) cartLineRepository.findAll();
	}

	public void insertCartLine(CartLine cartLine) {
		cartLineRepository.save(cartLine);		
	}

	public void updateCartLine(CartLine cartLine) {
		cartLineRepository.save(cartLine);		
	}

	public void deleteCartLineById(int cartLineId) {
		cartLineRepository.deleteById(cartLineId);		
	}
}
