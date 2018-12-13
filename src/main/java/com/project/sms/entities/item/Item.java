package com.project.sms.entities.item;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.project.sms.entities.catalogue.CatalogueItem;
import com.project.sms.entities.category.Category;
import com.project.sms.entities.customer.Comment;
import com.project.sms.entities.customer.Rating;
import com.project.sms.entities.employee.Employee;
import com.project.sms.enums.Month;

@Entity
@NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "ITEM_TYPE")
public abstract class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_generator")
	@SequenceGenerator(name = "item_generator", sequenceName = "item_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	protected Integer id;
	protected String name;
	protected Integer stockQuantity;
	protected Date updateDate;
	protected String description;
	protected String imageUrl;
	@OneToMany(mappedBy = "item")
	protected List<Rating> ratings = new ArrayList<Rating>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
	protected List<CatalogueItem> catalogueItems = new ArrayList<CatalogueItem>();
	@ManyToOne
	protected Category category;
	@OneToMany(mappedBy = "item")
	protected List<Comment> comments = new ArrayList<Comment>();
	@ManyToOne
	protected Employee employee;

	// -----Constructors-----
	public Item() {
		super();
	}

	public Item(String name, Integer stockQuantity, Date updateDate, String description) {
		super();
		this.name = name;
		this.stockQuantity = stockQuantity;
		this.updateDate = updateDate;
		this.description = description;
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

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<CatalogueItem> getCatalogueItems() {
		return catalogueItems;
	}

	public void setCatalogueItems(List<CatalogueItem> catalogueItems) {
		this.catalogueItems = catalogueItems;
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
	public Boolean isListed() {
		for (CatalogueItem catalogueItem : this.getCatalogueItems()) {
			if (this.getMonthFromInt(Calendar.getInstance().get(Calendar.MONTH))
					.equals(catalogueItem.getCatalogue().getMonth())) {
				return true;
			}
		}

		return false;
	}

	// TODO: Move this method inside a utilities class
	private Month getMonthFromInt(int num) {
		Month[] months = { Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY,
				Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER };

		return months[num];
	}

	public String toString() {
		return "Name: " + name;
	}
}
