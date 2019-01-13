package com.project.sms.statistics;

import com.project.sms.enums.Month;

class DataRequest {

	private Integer objectId;
	private Month monthStart;
	private Month monthEnd;
	private Double minValue;
	private Double maxValue;

	// ----- Getters and Setters -----
	public Integer getObjectId() {
		return objectId;
	}

	public Month getMonthStart() {
		return monthStart;
	}

	public Month getMonthEnd() {
		return monthEnd;
	}

	public Double getMinValue() {
		return minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

}
