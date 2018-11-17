package com.project.msd.product;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.project.msd.employee.Employee;

@Entity
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
	@SequenceGenerator(name = "category_generator", sequenceName = "category_sequence", initialValue = 8001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private String name;
	private Date updateDate;
	@ManyToOne
	private Employee employee;
	@OneToMany(mappedBy = "category")
	private List<Product> products = new ArrayList<Product>();

	// ----- Constructors -----
	public Category() {
		super();
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	// ----- Getters and Setters -----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}