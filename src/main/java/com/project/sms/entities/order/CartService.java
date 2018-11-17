package com.project.sms.entities.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService{

	@Autowired
	private ICartRepository cartRepository;

	public Optional<Cart> findCartById(int cartId) {
		return cartRepository.findById(cartId);
	}
	
	public List<Cart> findAllCarts() {
		return (List<Cart>) cartRepository.findAll();
	}

	public void insertCart(Cart cart) {
		cartRepository.save(cart);		
	}

	public void updateCart(Cart cart) {
		cartRepository.save(cart);		
	}

	public void deleteCartById(int cartId) {
		cartRepository.deleteById(cartId);		
	}
}
