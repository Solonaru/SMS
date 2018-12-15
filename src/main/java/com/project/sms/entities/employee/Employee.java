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
import com.project.sms.enums.EmployeeStatus;

@Entity
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
@DiscriminatorValue("Employee")
public class Employee extends Account {
	private static final long serialVersionUID = 1L;

	private EmployeeStatus employeeStatus;
	private IRight rightType;

	@ManyToMany
	@JoinTable(name = "employee_permissions", joinColumns = {
			@JoinColumn(name = "employee_id") }, inverseJoinColumns = { @JoinColumn(name = "permission_id") })
	private List<Permission> permissions = new ArrayList<Permission>();

	// -----Constructors-----
	public Employee() {
		super();
	}

	public Employee(String username, String password, String name, String email, String phoneNumber, Date creationDate,
			AccountStatus status, EmployeeStatus employeeStatus, IRight rightType) {
		super(username, password, name, email, phoneNumber, creationDate, status);
		this.employeeStatus = employeeStatus;
		this.rightType = rightType;
	}

	// ----- Getters and Setters -----
	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public IRight getRightType() {
		return rightType;
	}

	public void setRightType(IRight rightType) {
		this.rightType = rightType;
	}

	// ----- Methods -----
	public String toString() {
		return username;
	}

}
