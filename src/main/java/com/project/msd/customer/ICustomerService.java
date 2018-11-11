package com.project.msd.customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

	public Optional<Customer> findCustomerById(int customerId);

	public List<Customer> findAllCustomers();

	public void insertCustomer(Customer customer);

	public void updateCustomer(Customer customer);

	public void deleteCustomerById(int customerId);
}
