package com.project.sms.entities.employee;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import com.project.sms.entities.account.Account;
import com.project.sms.enums.AccountStatus;

@Entity
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
@DiscriminatorValue("Employee")
public class Employee extends Account {
	private static final long serialVersionUID = 1L;

	@ManyToMany
	@JoinTable(name = "employee_permissions", joinColumns = {
			@JoinColumn(name = "employee_id") }, inverseJoinColumns = { @JoinColumn(name = "permission_id") })
	private List<Permission> permissions = new ArrayList<Permission>();

	// -----Constructors-----
	public Employee() {
		super();
	}

	public Employee(String username, String password, String name, String email, String phoneNumber, Date creationDate,
			AccountStatus status) {
		super(username, password, name, email, phoneNumber, creationDate, status);

	}

	// ----- Getters and Setters -----

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	// ----- Methods -----
	public String toString() {
		return username;
	}

}