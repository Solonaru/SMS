package com.project.sms.entities.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.sms.entities.customer.Customer;
import com.project.sms.enums.OrderStatus;

@Entity
@NamedQuery(name = "Order.findAll", query = "SELECT o FROM Orders o")
public class Orders implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
	@SequenceGenerator(name = "order_generator", sequenceName = "order_sequence", initialValue = 300000001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private Date date;
	private OrderStatus status;
	@OneToOne
	@JsonIgnoreProperties(value = "order")
	private Cart cart;
	@OneToOne(mappedBy = "orderz")
	@JsonIgnoreProperties(value = "order")
	private Payment payment;
	@ManyToOne
	@JsonIgnoreProperties(value = "orders")
	private Customer customer;

	// -----Constructors-----
	public Orders() {
		super();
	}

	public Orders(Date date, OrderStatus status) {
		super();
		this.date = date;
		this.status = status;
	}

	// -----Getters and Setters-----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	// ----- Methods -----
	public Double getTotalValue() {
		return this.cart.getTotalValue();
	}

}
