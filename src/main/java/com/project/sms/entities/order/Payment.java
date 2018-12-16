package com.project.sms.entities.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.sms.enums.PaymentStatus;
import com.project.sms.enums.PaymentType;

@Entity
@NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_generator")
	@SequenceGenerator(name = "payment_generator", sequenceName = "payment_sequence", initialValue = 600000001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private PaymentType type;
	private PaymentStatus paymentStatus;
	private Date date;
	@OneToOne
	private Orders orderz;

	// -----Constructors-----
	public Payment() {
		super();
	}

	public Payment(PaymentType type, PaymentStatus paymentStatus, Date date) {
		super();
		this.type = type;
		this.paymentStatus = paymentStatus;
		this.date = date;
	}

	// -----Getters and Setters-----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@JsonIgnoreProperties(value = "payment")
	public Orders getOrder() {
		return orderz;
	}

	public void setOrder(Orders order) {
		this.orderz = order;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
}
