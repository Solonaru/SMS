package com.project.sms.statistics;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.sms.entities.order.CartLine;
import com.project.sms.entities.order.ICartLineService;
import com.project.sms.utils.UtilMethods;

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

	@RequestMapping(value = "/share/{request}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Double> getProductsShareInCategoryStatisticDataByMonth(@PathVariable("request") int request) {
		Integer categoryId = (int) Math.floor(request / 100);
		Integer month = request % 100;
		Double total = 0.0;

		Map<String, Double> statisticData = new TreeMap<String, Double>();
		Calendar cal = Calendar.getInstance();

		for (CartLine cartLine : cartLineService.findAllCartLines()) {
			cal.setTime(cartLine.getCart().getOrder().getDate());

			if (cartLine.getItem().getCategory().getId().equals(categoryId) && (cal.get(Calendar.MONTH) == month)) {
				Double value = cartLine.getValue();
				total += value;

				if (statisticData.get(cartLine.getItem().getName()) != null) {
					value += statisticData.get(cartLine.getItem().getName());
				}
				statisticData.put(cartLine.getItem().getName(), value);
			}
		}

		for (Map.Entry<String, Double> entry : statisticData.entrySet()) {
			statisticData.put(entry.getKey(), (double) Math.round((entry.getValue() / total) * 10000) / 100);
		}

		return statisticData;
	}

	@RequestMapping(value = "/month/complex", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, Double> getCategoriesComplexStatisticDataByMonth(@RequestBody DataRequest dataRequest) {
		Map<Integer, Double> tempData = new TreeMap<Integer, Double>();
		Map<Integer, Double> statisticData = new TreeMap<Integer, Double>();
		Calendar cal = Calendar.getInstance();

		for (CartLine cartLine : cartLineService.findAllCartLines()) {

			if ((UtilMethods.getMonthFromDate(cartLine.getCart().getOrder().getDate()).ordinal() >= dataRequest
					.getMonthStart().ordinal())
					&& (UtilMethods.getMonthFromDate(cartLine.getCart().getOrder().getDate()).ordinal() <= dataRequest
							.getMonthEnd().ordinal())) {

				if (cartLine.getItem().getCategory().getId().equals(dataRequest.getObjectId())) {
					Double value = cartLine.getValue();
					cal.setTime(cartLine.getCart().getOrder().getDate());
					Integer time = (cal.get(Calendar.YEAR) * 100) + cal.get(Calendar.MONTH);
					if (tempData.get(time) != null) {
						value += tempData.get(time);
					}
					tempData.put(time, value);
				}
			}
		}

		for (Map.Entry<Integer, Double> entry : tempData.entrySet()) {
			if (entry.getValue() <= dataRequest.getMaxValue() && entry.getValue() >= dataRequest.getMinValue()) {
				statisticData.put(entry.getKey(), entry.getValue());
			}
		}

		return statisticData;
	}

	@RequestMapping(value = "/month/top/price", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<Double, String> getTopProductsByCategoryBasedOnPriceStatisticDataByMonth(
			@RequestBody TopProductsDataRequest topProductsDataRequest) {
		int counter = -1;
		Double previousValue = 0.0;
		Map<String, Double> tempData = new TreeMap<String, Double>();
		Map<Double, String> tempSortedData = new TreeMap<Double, String>(new Comparator<Double>() {
			public int compare(Double data1, Double data2) {
				return (data2.compareTo(data1));
			}
		});
		Map<Double, String> statisticData = new TreeMap<Double, String>();

		for (CartLine cartLine : cartLineService.findAllCartLines()) {

			if (cartLine.getItem().getCategory().getId().equals(topProductsDataRequest.getCategoryId())) {

				if (UtilMethods.getMonthFromDate(cartLine.getCart().getOrder().getDate())
						.ordinal() == topProductsDataRequest.getMonth().ordinal()) {
					Double value = cartLine.getValue();

					if (tempData.get(cartLine.getItem().getName()) != null) {
						value += tempData.get(cartLine.getItem().getName());
					}
					tempData.put(cartLine.getItem().getName(), value);
				}
			}
		}

		for (Map.Entry<String, Double> entry : tempData.entrySet()) {
			tempSortedData.put(entry.getValue(), entry.getKey());
		}

		for (Map.Entry<Double, String> entry : tempSortedData.entrySet()) {
			counter++;

			if (topProductsDataRequest.getAmount().equals(counter) && !entry.getKey().equals(previousValue)) {
				break;
			}

			statisticData.put(entry.getKey(), entry.getValue());
			previousValue = entry.getKey();
		}

		return statisticData;
	}

	@RequestMapping(value = "/month/top/quantity", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<Double, String> getTopProductsByCategoryBasedOnQuantityStatisticDataByMonth(
			@RequestBody TopProductsDataRequest topProductsDataRequest) {
		int counter = -1;
		Double previousValue = 0.0;
		Map<String, Double> tempData = new TreeMap<String, Double>();
		Map<Double, String> tempSortedData = new TreeMap<Double, String>(new Comparator<Double>() {
			public int compare(Double data1, Double data2) {
				return (data2.compareTo(data1));
			}
		});
		Map<Double, String> statisticData = new TreeMap<Double, String>();

		for (CartLine cartLine : cartLineService.findAllCartLines()) {

			if (cartLine.getItem().getCategory().getId().equals(topProductsDataRequest.getCategoryId())) {

				if (UtilMethods.getMonthFromDate(cartLine.getCart().getOrder().getDate())
						.ordinal() == topProductsDataRequest.getMonth().ordinal()) {
					Double value = (double) cartLine.getQuantity();

					if (tempData.get(cartLine.getItem().getName()) != null) {
						value += tempData.get(cartLine.getItem().getName());
					}
					tempData.put(cartLine.getItem().getName(), value);
				}
			}
		}

		for (Map.Entry<String, Double> entry : tempData.entrySet()) {
			tempSortedData.put(entry.getValue(), entry.getKey());
		}

		for (Map.Entry<Double, String> entry : tempSortedData.entrySet()) {
			counter++;

			if (topProductsDataRequest.getAmount().equals(counter) && !(entry.getKey().equals(previousValue))) {
				break;
			}

			statisticData.put(entry.getKey(), entry.getValue());
			previousValue = entry.getKey();
		}

		return statisticData;
	}

}
