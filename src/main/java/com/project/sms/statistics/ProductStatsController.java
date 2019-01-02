package com.project.sms.statistics;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.sms.entities.order.CartLine;
import com.project.sms.entities.order.ICartLineService;

@RestController
@RequestMapping("/productStats")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductStatsController {

	@Autowired
	private ICartLineService cartLineService;

	@RequestMapping(value = "/day/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Date, Double> getProductsStatisticDataByDay(@PathVariable("productId") int productId) {
		Map<Date, Double> statisticData = new TreeMap<Date, Double>();

		for (CartLine cartLine : cartLineService.findAllCartLines()) {
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

	@RequestMapping(value = "/month/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, Double> getProductsStatisticDataByMonth(@PathVariable("productId") int productId) {
		Map<Integer, Double> statisticData = new TreeMap<Integer, Double>();
		Calendar cal = Calendar.getInstance();

		for (CartLine cartLine : cartLineService.findAllCartLines()) {
			if (cartLine.getItem().getId().equals(productId)) {
				Double value = cartLine.getValue();
				cal.setTime(cartLine.getCart().getOrder().getDate());
				Integer time = (cal.get(Calendar.YEAR) * 100) + cal.get(Calendar.MONTH);
				System.out.println("TIME: " + time);
				if (statisticData.get(time) != null) {
					value += statisticData.get(time);
				}
				statisticData.put(time, value);
			}
		}

		return statisticData;
	}

}
