package com.project.sms.entities.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;

	public Optional<Customer> findCustomerById(int customerId) {
		return customerRepository.findById(customerId);
	}

	public List<Customer> findAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}

	public void insertCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public void updateCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public void deleteCustomerById(int customerId) {
		customerRepository.deleteById(customerId);
	}
	
	public Customer findCustomerByUsernameAndPassword(String username, String password) {
		return customerRepository.findCustomerByUsernameAndPassword(username, password);
	}
}
