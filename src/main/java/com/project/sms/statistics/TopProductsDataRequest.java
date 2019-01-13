package com.project.sms.statistics;

import com.project.sms.enums.Month;

class TopProductsDataRequest {

	private Integer categoryId;
	private Month month;
	private Integer amount;

	// ----- Getters and Setters -----
	public Integer getCategoryId() {
		return categoryId;
	}

	public Month getMonth() {
		return month;
	}

	public Integer getAmount() {
		return amount;
	}

}
