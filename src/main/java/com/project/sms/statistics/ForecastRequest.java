package com.project.sms.statistics;

import java.util.Map;

public class ForecastRequest {

	private Map<Integer, Double> statisticData;
	private Integer periods;

	// ----- Getters and Setters -----
	public Map<Integer, Double> getStatisticData() {
		return statisticData;
	}

	public Integer getPeriods() {
		return periods;
	}

}
