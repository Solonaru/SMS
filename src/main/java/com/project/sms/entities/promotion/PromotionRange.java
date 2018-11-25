package com.project.sms.entities.promotion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.project.sms.entities.item.Item;

@Entity
@NamedQuery(name = "PromotionRange.findAll", query = "SELECT pr FROM PromotionRange pr")
public class PromotionRange implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion_range_generator")
	@SequenceGenerator(name = "promotion_range_generator", sequenceName = "promotion_range_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private Double fromValue;
	private Double toValue;
	@ManyToOne
	private Promotion promotion;
	@ManyToOne
	private Item item;
	
	// ----- Constructors -----
	public PromotionRange() {
		super();
	}

	public PromotionRange(Double fromValue, Double toValue) {
		super();
		this.fromValue = fromValue;
		this.toValue = toValue;
	}
	
	// ----- Getters and Setters -----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getFromValue() {
		return fromValue;
	}

	public void setFromValue(Double fromValue) {
		this.fromValue = fromValue;
	}

	public Double getToValue() {
		return toValue;
	}

	public void setToValue(Double toValue) {
		this.toValue = toValue;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
