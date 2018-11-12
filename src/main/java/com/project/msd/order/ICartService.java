package com.project.msd.order;

import java.util.List;
import java.util.Optional;

public interface ICartService {

	public Optional<Cart> findCartById(int cartId);
	
	public List<Cart> findAllCarts();

	public void insertCart(Cart cart);

	public void updateCart(Cart cart);

	public void deleteCartById(int cart);
}
