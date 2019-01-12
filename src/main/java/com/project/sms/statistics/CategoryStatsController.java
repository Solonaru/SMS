package com.project.sms.statistics;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
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
@RequestMapping("/categoryStats")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryStatsController {

	@Autowired
	private ICartLineService cartLineService;

	@RequestMapping(value = "/month/{categoryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, Double> getCategoriesStatisticDataByMonth(@PathVariable("categoryId") int categoryId) {
		Map<Integer, Double> statisticData = new TreeMap<Integer, Double>();
		Calendar cal = Calendar.getInstance();

		for (CartLine cartLine : cartLineService.findAllCartLines()) {
			if (cartLine.getItem().getCategory().getId().equals(categoryId)) {
				Double value = cartLine.getValue();
				cal.setTime(cartLine.getCart().getOrder().getDate());
				Integer time = (cal.get(Calendar.YEAR) * 100) + cal.get(Calendar.MONTH);
				if (statisticData.get(time) != null) {
					value += statisticData.get(time);
				}
				statisticData.put(time, value);
			}
		}

		return statisticData;
	}

	@RequestMapping(value = "/month/complete/{categoryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, Double> getCompleteCategoriesStatisticDataByMonth(@PathVariable("categoryId") int categoryId) {
		Map<Integer, Double> statisticData = this.getCategoriesStatisticDataByMonth(categoryId);

		List<Integer> months = Arrays.asList(201801, 201802, 201803, 201804, 201805, 201806, 201807, 201808, 201809,
				201810, 201811, 201900);

		for (Integer month : months) {
			if (statisticData.get(month) == null) {
				statisticData.put(month, 0.0);
			}
		}

		return statisticData;
	}

}
