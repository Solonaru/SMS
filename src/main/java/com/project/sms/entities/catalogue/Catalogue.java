package com.project.sms.entities.catalogue;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.SequenceGenerator;

import com.project.sms.entities.employee.Employee;
import com.project.sms.entities.product.Product;
import com.project.sms.enums.Month;

@Entity
@NamedQuery(name = "Catalogue.findAll", query = "SELECT c FROM Catalogue c")
public class Catalogue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalogue_generator")
	@SequenceGenerator(name = "catalogue_generator", sequenceName = "catalogue_sequence", initialValue = 9001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private Month month;
	private Integer year;
	private Date updateDate;
	@ManyToOne
	private Employee employee;
	@ManyToMany
	@JoinTable(name = "catalogue_products", joinColumns = { @JoinColumn(name = "catalogue_id") }, inverseJoinColumns = {
			@JoinColumn(name = "product_id") })
	private List<Product> products = new ArrayList<Product>();

	// -----Constructors-----
	public Catalogue() {
		super();
	}

	public Catalogue(Month month, Integer year) {
		super();
		this.month = month;
		this.year = year;
	}

	// -----Getters and Setters-----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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
