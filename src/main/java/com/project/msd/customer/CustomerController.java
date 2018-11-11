package com.project.msd.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Customer> findCustomerById(@PathVariable("customerId") int customerId) {
		return customerService.findCustomerById(customerId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getCustomers() {
		return customerService.findAllCustomers();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCustomer(@RequestBody Customer customer) {
		customerService.insertCustomer(customer);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCustomer(@RequestBody Customer customer) {
		customerService.updateCustomer(customer);
	}

	@RequestMapping(value = "/delete/{customerId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCustomer(@PathVariable("customerId") int customerId) {
		customerService.deleteCustomerById(customerId);
	}
}
