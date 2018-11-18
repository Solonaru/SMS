package com.project.sms.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.project.sms.entities.location.Address;
import com.project.sms.entities.location.City;
import com.project.sms.entities.location.County;
import com.project.sms.entities.location.IAddressService;
import com.project.sms.entities.location.ICityService;
import com.project.sms.entities.location.ICountyService;
import com.project.sms.entities.order.IPaymentService;
import com.project.sms.entities.order.Payment;
import com.project.sms.enums.PaymentType;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ICountyService countyService;
	@Autowired
	private ICityService cityService;
	@Autowired
	private IAddressService addressService;
	@Autowired
	private IPaymentService paymentService;

	@Autowired
	private DisplayData displayData;

	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (isDBEmpty()) {
			loadData();
		}
	}

	private void loadData() {
		displayData.printInfo("Starting data loading...");

		County county = new County("Iasi", "Moldova");

		City city = new City("Iasi");
		city.setCounty(county);

		Address address = new Address("Alexandru cel Bun", 51, 270123);
		address.setCity(city);
		
		Payment payment1 = new Payment(PaymentType.MAESTRO, null);
		Payment payment2 = new Payment(PaymentType.VISA, null);
		Payment payment3 = new Payment(PaymentType.MAESTRO, null);

		countyService.insertCounty(county);

		cityService.insertCity(city);

		addressService.insertAddress(address);
		
		paymentService.insertPayment(payment1);
		paymentService.insertPayment(payment2);
		paymentService.insertPayment(payment3);

		displayData.printInfo("Data successfully loaded.");
	}

	private Boolean isDBEmpty() {
		Boolean isEmpty = false;
		List<County> counties = countyService.findAllCounties();

		if (counties.isEmpty()) {
			displayData.printInfo("The database is empty.");
			isEmpty = true;
		} else {
			displayData.printInfo("The database in NOT empty. No data loading required.");
		}

		return isEmpty;
	}
}
