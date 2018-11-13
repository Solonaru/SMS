package com.project.msd.customer;

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

import com.project.msd.account.Account;
import com.project.msd.location.Address;
import com.project.msd.subscription.Subscription;

@Entity
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
@DiscriminatorValue("Customer")
public class Customer extends Account implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String type;
	@ManyToMany
	@JoinTable(name = "delivery_addresses",
			   joinColumns = {@JoinColumn(name = "customer_id")},
			   inverseJoinColumns = {@JoinColumn(name = "address_id")})
	private List<Address> addresses = new ArrayList<Address>();
	@ManyToMany
	@JoinTable(name = "customer_subscriptions",
			  joinColumns = {@JoinColumn(name = "customer_id")},
			  inverseJoinColumns = {@JoinColumn(name = "subscription_id")})
	private List<Subscription> subscriptions = new ArrayList<Subscription>();
	@OneToMany(mappedBy = "customer")
	private List<Rating> ratings = new ArrayList<Rating>();
	
	// -----Constructors-----
	public Customer() {
		super();
	}

	public Customer(String username, String password, String name, String email, String phoneNumber, Date creationDate,
			String status, String type) {
		super(username, password, name, email, phoneNumber, creationDate, status);
		this.type = type;
	}

	// -----Getters and Setters-----
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	
	  public List<Subscription> getSubscriptions() { return subscriptions; }
	  
	  public void setSubscriptions(List<Subscription> subscriptions) {
	  this.subscriptions = subscriptions; }
	  
	  public List<Rating> getRatings() { return ratings; }
	  
	  public void setRatings(List<Rating> ratings) { this.ratings = ratings; }
	 
	
	// -----Methods-----
	public String toString() {
		return "Username: " + username;
	}
}
