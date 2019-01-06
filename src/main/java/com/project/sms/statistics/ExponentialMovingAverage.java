package com.project.sms.statistics;

class ExponentialMovingAverage {
	private double alpha;
	private Double oldValue;

	public ExponentialMovingAverage(double alpha) {
		this.alpha = alpha;
	}

	public double getAverage(double value) {
		System.out.println("OLD VALUE: " + oldValue);
		
		if (oldValue == null) {
			oldValue = value;
			return value;
		}

		double newValue = oldValue + alpha * (value - oldValue);
		oldValue = newValue;
		return newValue;
	}
}
