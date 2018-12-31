package com.project.sms.entities.customer;

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
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Customer> findCustomerById(@PathVariable("customerId") int customerId) {
		dataDisplay.printCrudInfo(customerId); 
		return customerService.findCustomerById(customerId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getCustomers() {
		dataDisplay.printCrudInfo(); 
		return customerService.findAllCustomers();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCustomer(@RequestBody Customer customer) {
		dataDisplay.printCrudInfo(); 
		customerService.insertCustomer(customer);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCustomer(@RequestBody Customer customer) {
		dataDisplay.printCrudInfo(); 
		customerService.updateCustomer(customer);
	}

	@RequestMapping(value = "/delete/{customerId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCustomer(@PathVariable("customerId") int customerId) {
		dataDisplay.printCrudInfo(customerId); 
		customerService.deleteCustomerById(customerId);
	}
	
}
