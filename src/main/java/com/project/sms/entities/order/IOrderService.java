package com.project.sms.entities.order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {

	public Optional<Orders> findOrderById(int orderId);
	
	public List<Orders> findAllOrders();

	public void insertOrder(Orders order);

	public void updateOrder(Orders order);

	public void deleteOrderById(int order);
}
