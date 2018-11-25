package com.project.sms.entities.promotion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.project.sms.enums.PromotionStatus;

@Entity
@NamedQuery(name = "Promotion.findAll", query = "SELECT p FROM Promotion p")
public class Promotion implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion_generator")
	@SequenceGenerator(name = "promotion_generator", sequenceName = "promotion_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private String description;
	private Date startDate;
	private Date endDate;
	private PromotionStatus status;
	@OneToMany(mappedBy = "promotion")
	private List<PromotionRange> promotionRanges = new ArrayList<PromotionRange>();
	
	// ----- Constructors -----
	public Promotion() {
		super();
	}

	public Promotion(String description, Date startDate, Date endDate, PromotionStatus status) {
		super();
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	
	// ----- Getters and Setters -----
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public PromotionStatus getStatus() {
		return status;
	}

	public void setStatus(PromotionStatus status) {
		this.status = status;
	}

	public List<PromotionRange> getPromotionRanges() {
		return promotionRanges;
	}

	public void setPromotionRanges(List<PromotionRange> promotionRanges) {
		this.promotionRanges = promotionRanges;
	}
}
