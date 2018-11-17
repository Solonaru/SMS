package com.project.sms.entities.product;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.project.sms.entities.catalogue.Catalogue;
import com.project.sms.entities.customer.Comment;
import com.project.sms.entities.customer.Rating;
import com.project.sms.entities.employee.Employee;

@Entity
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "PRODUCT_TYPE")
public abstract class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
	@SequenceGenerator(name = "product_generator", sequenceName = "product_sequence", initialValue = 8000001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	protected Integer id;
	protected String name;
	protected Integer unitsInStock;
	protected Date updateDate;
	@OneToMany(mappedBy = "product")
	protected List<Rating> ratings = new ArrayList<Rating>();
	@ManyToMany
	@JoinTable(name = "catalogue_products", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = {
			@JoinColumn(name = "catalogue_id") })
	protected List<Catalogue> catalogues = new ArrayList<Catalogue>();
	@ManyToOne
	protected Category category;
	@OneToMany(mappedBy = "product")
	protected List<Comment> comments = new ArrayList<Comment>();
	@ManyToOne
	protected Employee employee;

	// -----Constructors-----
	public Product() {
		super();
	}

	public Product(String name, Integer unitsInStock, Date updateDate) {
		super();
		this.name = name;
		this.unitsInStock = unitsInStock;
		this.updateDate = updateDate;
	}

	// -----Getters and Setters-----
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

	public Integer getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(Integer unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<Catalogue> getCatalogues() {
		return catalogues;
	}

	public void setCatalogues(List<Catalogue> catalogues) {
		this.catalogues = catalogues;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	// -----Methods-----
	public String toString() {
		return "Name: " + name;
	}
}
