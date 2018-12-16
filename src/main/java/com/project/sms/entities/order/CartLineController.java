package com.project.sms.entities.order;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.sms.utils.DisplayData;

@RestController
@RequestMapping("/cartLine")
@CrossOrigin(origins = "http://localhost:4200")
public class CartLineController {

	@Autowired
	private ICartLineService cartLineService;

	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{cartLineId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<CartLine> findCartLineById(@PathVariable("cartLineId") int cartLineId) {
		dataDisplay.printCrudInfo(cartLineId);
		return cartLineService.findCartLineById(cartLineId);
	}

	@RequestMapping(value = "/stat/{cartLineId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<CartLine> findCartLineById2(@PathVariable("cartLineId") int cartLineId) {
		dataDisplay.printCrudInfo(cartLineId);
		return cartLineService.findCartLineById(cartLineId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CartLine> getCartLines() {
		dataDisplay.printCrudInfo();
		return cartLineService.findAllCartLines();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCartLine(@RequestBody CartLine cartLine) {
		dataDisplay.printCrudInfo();
		cartLineService.insertCartLine(cartLine);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCartLine(@RequestBody CartLine cartLine) {
		dataDisplay.printCrudInfo();
		cartLineService.updateCartLine(cartLine);
	}

	@RequestMapping(value = "/delete/{cartLineId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCartLine(@PathVariable("cartLineId") int cartLineId) {
		dataDisplay.printCrudInfo(cartLineId);
		cartLineService.deleteCartLineById(cartLineId);
	}

	@RequestMapping(value = "/statisticDataDay/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Date, Double> getProductsStatisticDataByDay(@PathVariable("productId") int productId) {
		Map<Date, Double> statisticData = new TreeMap<Date, Double>();

		for (CartLine cartLine : this.getCartLines()) {
			if (cartLine.getItem().getId().equals(productId)) {
				Double value = cartLine.getValue();
				if (statisticData.get(cartLine.getCart().getOrder().getDate()) != null) {
					value += statisticData.get(cartLine.getCart().getOrder().getDate());
				}
				statisticData.put(cartLine.getCart().getOrder().getDate(), value);
			}
		}

		return statisticData;
	}

	@RequestMapping(value = "/statisticDataMonth/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, Double> getProductsStatisticDataByMonth(@PathVariable("productId") int productId) {
		Map<Integer, Double> statisticData = new TreeMap<Integer, Double>();
		Calendar cal = Calendar.getInstance();

		for (CartLine cartLine : this.getCartLines()) {
			if (cartLine.getItem().getId().equals(productId)) {
				Double value = cartLine.getValue();
				cal.setTime(cartLine.getCart().getOrder().getDate());
				Integer month = cal.get(Calendar.MONTH) + 1;
				if (statisticData.get(month) != null) {
					value += statisticData.get(month);
				}
				statisticData.put(month, value);
			}
		}

		return statisticData;
	}
}
