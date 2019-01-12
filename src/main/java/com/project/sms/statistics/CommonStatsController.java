package com.project.sms.statistics;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commonStats")
@CrossOrigin(origins = "http://localhost:4200")
public class CommonStatsController {

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

}
