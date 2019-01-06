package com.project.sms.statistics;

import java.util.Map;

class ForecastRequest {

	private Map<Integer, Double> statisticData;
	private Double periods;

	// ----- Getters and Setters -----
	public Map<Integer, Double> getStatisticData() {
		return statisticData;
	}

	public Double getPeriods() {
		return periods;
	}

}
