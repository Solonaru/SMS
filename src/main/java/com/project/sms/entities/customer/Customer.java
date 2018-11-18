package com.project.sms.entities.customer;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.project.sms.entities.account.Account;
import com.project.sms.entities.location.Address;
import com.project.sms.entities.order.Orders;
import com.project.sms.entities.subscription.Subscription;
import com.project.sms.enums.AccountStatus;

@Entity
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
@DiscriminatorValue("Customer")
public class Customer extends Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToMany
	@JoinTable(name = "delivery_addresses", joinColumns = { @JoinColumn(name = "customer_id") }, inverseJoinColumns = {
			@JoinColumn(name = "address_id") })
	private List<Address> addresses = new ArrayList<Address>();
	@ManyToMany
	@JoinTable(name = "customer_subscriptions", joinColumns = {
			@JoinColumn(name = "customer_id") }, inverseJoinColumns = { @JoinColumn(name = "subscription_id") })
	private List<Subscription> subscriptions = new ArrayList<Subscription>();
	@OneToMany(mappedBy = "customer")
	private List<Orders> orders = new ArrayList<Orders>();

	// -----Constructors-----
	public Customer() {
		super();
	}

	public Customer(String username, String password, String name, String email, String phoneNumber, Date creationDate,
			AccountStatus status) {
		super(username, password, name, email, phoneNumber, creationDate, status);
	}

	// -----Getters and Setters-----
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	// -----Methods-----
	public String toString() {
		return username;
	}
}
