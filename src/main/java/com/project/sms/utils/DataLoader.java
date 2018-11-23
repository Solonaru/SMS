package com.project.sms.utils;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.project.sms.entities.customer.Customer;
import com.project.sms.entities.customer.ICustomerService;
import com.project.sms.entities.location.Address;
import com.project.sms.entities.location.City;
import com.project.sms.entities.location.County;
import com.project.sms.entities.location.IAddressService;
import com.project.sms.entities.location.ICityService;
import com.project.sms.entities.location.ICountyService;
import com.project.sms.entities.order.IPaymentService;
import com.project.sms.entities.order.Payment;
import com.project.sms.enums.AccountStatus;
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
	private ICustomerService customerService;

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

		City city1 = new City("Iasi");
		city1.setCounty(county);
		City city2 = new City("Pascani");
		city2.setCounty(county);
		City city3 = new City("Targu Frumos");
		city3.setCounty(county);

		Address address1 = new Address("Alexandru cel Bun", 51, 270123);
		address1.setCity(city1);
		Address address2 = new Address("Marinelor", 11, 280129);
		address2.setCity(city2);
		Address address3 = new Address("Marinelor", 27, 280129);
		address3.setCity(city2);
		Address address4 = new Address("Marin Cordoba", 100, 290111);
		address4.setCity(city3);
		Address address5 = new Address("Cosmopolitan", 127, 270123);
		address5.setCity(city1);
		Address address6 = new Address("Cosmopolitan", 129, 270123);
		address6.setCity(city1);
		

		Customer customer1 = new Customer("Alex", "alex123", "Alexandru", "alex_cozma@gmail.com", "0748974419",
				new Date(System.currentTimeMillis()), AccountStatus.ACTIVE);
		customer1.setAddress(address1);
		customer1.getDeliveryAddresses().add(address5);
		customer1.getDeliveryAddresses().add(address6);
		Customer customer2 = new Customer("Buzzy23", "password", "Maxim", "max_96@yahoo.com", "0742114411",
				new Date(System.currentTimeMillis()), AccountStatus.ACTIVE);
		customer2.setAddress(address2);
		Customer customer3 = new Customer("Alinacika", "saalina", "Alina", "alina_sandra@gmail.com", "0742271410",
				new Date(System.currentTimeMillis()), AccountStatus.ACTIVE);
		customer3.setAddress(address3);
		customer3.getDeliveryAddresses().add(address4);

		Payment payment1 = new Payment(PaymentType.MAESTRO, null);
		Payment payment2 = new Payment(PaymentType.VISA, null);
		Payment payment3 = new Payment(PaymentType.MAESTRO, null);

		countyService.insertCounty(county);

		cityService.insertCity(city1);
		cityService.insertCity(city2);
		cityService.insertCity(city3);

		addressService.insertAddress(address1);
		addressService.insertAddress(address2);
		addressService.insertAddress(address3);
		addressService.insertAddress(address4);
		addressService.insertAddress(address5);
		addressService.insertAddress(address6);

		customerService.insertCustomer(customer1);
		customerService.insertCustomer(customer2);
		customerService.insertCustomer(customer3);

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
