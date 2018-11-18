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

@RestController
@RequestMapping("/cartLine")
public class CartLineController {

	@Autowired
	private ICartLineService cartLineService;
	
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<CartLine> findCartLineById(@PathVariable("cartLineId") int cartLineId) {
		return cartLineService.findCartLineById(cartLineId);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CartLine> getCartLines() {
		return cartLineService.findAllCartLines();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCartLine(@RequestBody CartLine cartLine) {
		cartLineService.insertCartLine(cartLine);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCartLine(@RequestBody CartLine cartLine) {
		cartLineService.updateCartLine(cartLine);
	}
	
	@RequestMapping(value = "/delete/{cartLineId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCartLine(@PathVariable("cartLineId") int cartLineId) {
		cartLineService.deleteCartLineById(cartLineId);
	}
}