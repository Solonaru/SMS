package com.project.sms.entities.customer;

import java.sql.Date;
import java.util.List;

import com.project.sms.entities.location.Address;
import com.project.sms.entities.order.Orders;
import com.project.sms.entities.subscription.Subscription;
import com.project.sms.enums.AccountStatus;

public interface ICustomer {

	public Integer getId();

	public String getName();

	public String getEmail();

	public String getPhoneNumber();

	public Date getCreationDate();

	public AccountStatus getStatus();

	public Address getAddress();

	public List<Address> getDeliveryAddresses();

	public List<Subscription> getSubscriptions();

	public List<Orders> getOrders();

}
