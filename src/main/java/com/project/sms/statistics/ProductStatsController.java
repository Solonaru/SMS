package com.project.sms.statistics;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
				if (statisticData.get(time) != null) {
					value += statisticData.get(time);
				}
				statisticData.put(time, value);
			}
		}

		return statisticData;
	}

	@RequestMapping(value = "/month/complete/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, Double> getCompleteProductsStatisticDataByMonth(@PathVariable("productId") int productId) {
		Map<Integer, Double> statisticData = this.getProductsStatisticDataByMonth(productId);

		List<Integer> months = Arrays.asList(201801, 201802, 201803, 201804, 201805, 201806, 201807, 201808, 201809,
				201810, 201811, 201900);

		for (Integer month : months) {
			if (statisticData.get(month) == null) {
				statisticData.put(month, 0.0);
			}
		}

		return statisticData;
	}

	@RequestMapping(value = "/forecast/movingAverage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, Double> getMovingAverageForecast(@RequestBody ForecastRequest forecastRequest) {
		Map<Integer, Double> forecast = new TreeMap<Integer, Double>();
		Integer lastMonth = 0;
		Double size = forecastRequest.getPeriods();

		for (int i = 0; i + size <= forecastRequest.getStatisticData().size(); i++) {
			Double sum = 0.0;
			for (int j = i; j < i + size; j++) {
				lastMonth = (Integer) forecastRequest.getStatisticData().keySet().toArray()[j];
				sum += forecastRequest.getStatisticData().get(lastMonth);
			}

			forecast.put(lastMonth, Math.floor(sum / size));
		}

		return forecast;
	}

	@RequestMapping(value = "/forecast/weightedMovingAverage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, Double> getWeightedMovingAverageForecast(@RequestBody ForecastRequest forecastRequest) {
		Map<Integer, Double> forecast = new TreeMap<Integer, Double>();
		Integer lastMonth = 0;
		Double size = forecastRequest.getPeriods();

		for (int i = 0; i + size <= forecastRequest.getStatisticData().size(); i++) {
			Double sum = 0.0;
			Integer weight = 0;
			Integer totalWeight = 0;
			for (int j = i; j < i + size; j++) {
				weight++;
				totalWeight += weight;
				lastMonth = (Integer) forecastRequest.getStatisticData().keySet().toArray()[j];
				sum += weight * forecastRequest.getStatisticData().get(lastMonth);
			}

			forecast.put(lastMonth, Math.floor(sum / totalWeight));
		}

		return forecast;
	}

	@RequestMapping(value = "/forecast/exponentialMovingAverage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, Double> getExponentialMovingAverageForecast(@RequestBody ForecastRequest forecastRequest) {
		Map<Integer, Double> forecast = new TreeMap<Integer, Double>();
		Double alpha = forecastRequest.getPeriods();
		System.out.println("ALPHA: " + alpha);
		Double average = 0.0;

		ExponentialMovingAverage ema = new ExponentialMovingAverage(alpha);

		for (Map.Entry<Integer, Double> entry : forecastRequest.getStatisticData().entrySet()) {
			average = ema.getAverage(entry.getValue());
			forecast.put(entry.getKey(), average);
			System.out.println("AVERAGE: " + average);
		}

		return forecast;
	}

	@RequestMapping(value = "/month/price/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Double, Double> getProductsStatisticDataBasedOnPriceByMonth(@PathVariable("productId") int productId) {
		Map<Double, Double> statisticData = new TreeMap<Double, Double>();

		for (CartLine cartLine : cartLineService.findAllCartLines()) {
			if (cartLine.getItem().getId().equals(productId)) {
				Double price = cartLine.getValue();
				Double value = price;
				if (statisticData.get(price) != null) {
					value += statisticData.get(price);
				}
				statisticData.put(price, value);
			}
		}

		return statisticData;
	}

	@RequestMapping(value = "/average", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Double getAverageFromStatisticData(@RequestBody Map<Integer, Double> statisticData) {
		Double total = 0.0;
		Integer count = 0;

		for (Map.Entry<Integer, Double> entry : statisticData.entrySet()) {
			total += entry.getValue();
			count++;
		}

		return total / count;
	}

}
