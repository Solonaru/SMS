package com.project.sms.entities.employee;

import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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

//	@OneToOne(targetEntity = Right.class)
//	private IRight rightType;

	private Right rightType;

	// ----- Constructors -----
	public Employee() {
		super();
	}

	public Employee(String username, String password, String name, String email, String phoneNumber, Date creationDate,
			AccountStatus status, EmployeeStatus employeeStatus, Right rightType) {
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

	public Right getRightType() {
		return rightType;
	}

	public void setRightType(Right rightType) {
		this.rightType = rightType;
	}

	// ----- Methods -----
	public String toString() {
		return username;
	}

}
