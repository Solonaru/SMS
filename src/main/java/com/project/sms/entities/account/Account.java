package com.project.sms.entities.account;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.sms.entities.location.Address;
import com.project.sms.enums.AccountStatus;

@Entity
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "ACCOUNT_TYPE")
@JsonDeserialize(using = AccountDeserializer.class)
public abstract class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
	@SequenceGenerator(name = "account_generator", sequenceName = "account_sequence", initialValue = 400000001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	protected Integer id;
	protected String username;
	protected String password;
	protected String name;
	protected String email;
	protected String phoneNumber;
	protected Date creationDate;
	protected AccountStatus status;
	@ManyToOne
	protected Address address;

	// -----Constructors-----
	public Account() {
		super();
	}

	public Account(String username, String password, String name, String email, String phoneNumber) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	// -----Getters and Setters-----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	// -----Methods-----
	public String toString() {
		return username;
	}
}
