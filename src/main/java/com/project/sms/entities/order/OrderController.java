package com.project.sms.entities.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.sms.utils.DisplayData;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private DisplayData dataDisplay;
	
	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Orders> findOrderById(@PathVariable("orderId") int orderId) {
		dataDisplay.printCrudInfo(orderId); 
		return orderService.findOrderById(orderId);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Orders> getOrders() {
		dataDisplay.printCrudInfo(); 
		return orderService.findAllOrders();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertOrder(@RequestBody Orders order) {
		dataDisplay.printCrudInfo(); 
		orderService.insertOrder(order);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateOrder(@RequestBody Orders order) {
		dataDisplay.printCrudInfo(); 
		orderService.updateOrder(order);
	}
	
	@RequestMapping(value = "/delete/{orderId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteOrder(@PathVariable("orderId") int orderId) {
		dataDisplay.printCrudInfo(orderId);
		orderService.deleteOrderById(orderId);
	}
}
