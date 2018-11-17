package com.project.sms.entities.employee;

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

import com.project.sms.enums.PermissionType;

@Entity
@NamedQuery(name = "Permission.findAll", query = "SELECT p FROM Permission p")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_generator")
	@SequenceGenerator(name = "permission_generator", sequenceName = "permission_sequence", initialValue = 401, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private PermissionType type;
	@ManyToMany
	@JoinTable(name = "employee_permissions", joinColumns = {
			@JoinColumn(name = "permission_id") }, inverseJoinColumns = { @JoinColumn(name = "employee_id") })
	private List<Employee> employees = new ArrayList<Employee>();

	// -----Constructors-----
	public Permission() {
		super();
	}

	public Permission(PermissionType type) {
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

	public PermissionType getType() {
		return type;
	}

	public void setType(PermissionType type) {
		this.type = type;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	// -----Methods-----
	public String toString() {
		return type.toString();
	}
}
