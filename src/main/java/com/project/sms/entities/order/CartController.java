package com.project.sms.entities.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.sms.utils.DisplayData;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ICartService cartService;
	@Autowired
	private DisplayData dataDisplay;
	
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Cart> findCartById(@PathVariable("cartId") int cartId) {
		dataDisplay.printCrudInfo(cartId); 
		return cartService.findCartById(cartId);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cart> getCarts() {
		dataDisplay.printCrudInfo(); 
		return cartService.findAllCarts();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCart(@RequestBody Cart cart) {
		dataDisplay.printCrudInfo(); 
		cartService.insertCart(cart);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCart(@RequestBody Cart cart) {
		dataDisplay.printCrudInfo(); 
		cartService.updateCart(cart);
	}
	
	@RequestMapping(value = "/delete/{cartId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCart(@PathVariable("cartId") int cartId) {		
		dataDisplay.printCrudInfo(cartId); 
		cartService.deleteCartById(cartId);
	}
}
