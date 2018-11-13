package com.project.msd.location;

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.project.msd.account.Account;
import com.project.msd.customer.Customer;

@Entity
@NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_generator")
	@SequenceGenerator(name = "address_generator", sequenceName = "address_sequence", initialValue = 2000000001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private String street;
	private Integer nr;
	private Integer zipCode;
	@ManyToOne
	private City city;
	@OneToMany(mappedBy = "address")
	private List<Account> accounts = new ArrayList<Account>();
	@ManyToMany
	@JoinTable(name = "delivery_addresses", joinColumns = { @JoinColumn(name = "address_id") }, inverseJoinColumns = {
			@JoinColumn(name = "customer_id") })
	private List<Customer> customers = new ArrayList<Customer>();

	// -----Constructors-----
	public Address() {
		super();
	}

	public Address(String street, Integer nr, Integer zipCode) {
		super();
		this.street = street;
		this.nr = nr;
		this.zipCode = zipCode;
	}

	// -----Getters and Setters-----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNr() {
		return nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	// -----Methods-----
	public String toString() {
		return "St. " + street + nr + ", " + city + ", " + zipCode;
	}
}