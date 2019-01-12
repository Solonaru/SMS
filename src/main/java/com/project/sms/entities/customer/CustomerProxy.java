package com.project.sms.entities.customer;

import java.sql.Date;
import java.util.List;

import com.project.sms.entities.location.Address;
import com.project.sms.entities.order.Orders;
import com.project.sms.entities.subscription.Subscription;
import com.project.sms.enums.AccountStatus;

public class CustomerProxy implements ICustomer {

	private Customer customer;

	public CustomerProxy(Customer customer) {
		this.customer = customer;
	}

	public Integer getId() {
		return customer.getId();
	}

	public String getName() {
		return customer.getName();
	}

	public String getEmail() {
		return customer.getEmail();
	}

	public String getPhoneNumber() {
		return customer.getPhoneNumber();
	}

	public Date getCreationDate() {
		return customer.getCreationDate();
	}

	public AccountStatus getStatus() {
		return customer.getStatus();
	}

	public Address getAddress() {
		return customer.getAddress();
	}

	public List<Address> getDeliveryAddresses() {
		return customer.getDeliveryAddresses();
	}

	public List<Subscription> getSubscriptions() {
		return customer.getSubscriptions();
	}

	public List<Orders> getOrders() {
		return customer.getOrders();
	}

}
