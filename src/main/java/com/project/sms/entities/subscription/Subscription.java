package com.project.sms.entities.subscription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.sms.entities.customer.Customer;
import com.project.sms.enums.SubscriptionType;

@Entity
@NamedQuery(name = "Subscription.findAll", query = "SELECT s FROM Subscription s")
public class Subscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_generator")
	@SequenceGenerator(name = "subscription_generator", sequenceName = "subscription_sequence", initialValue = 1101, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private SubscriptionType type;
	@ManyToMany
	@JoinTable(name = "customer_subscriptions", joinColumns = {
			@JoinColumn(name = "subscription_id") }, inverseJoinColumns = { @JoinColumn(name = "customer_id") })
	@JsonIgnoreProperties(value = "subscriptions")
	private List<Customer> customers = new ArrayList<Customer>();

	// -----Constructors-----
	public Subscription() {
		super();
	}

	public Subscription(SubscriptionType type) {
		super();
		this.type = type;
	}

	// -----Getters and Setters-----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SubscriptionType getType() {
		return type;
	}

	public void setType(SubscriptionType type) {
		this.type = type;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	// -----Methods-----
	public String toString() {
		return type.toString();
	}
}
