package com.project.sms.entities.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeRepository employeeRepository;

	public Optional<Employee> findEmployeeById(int employeeId) {
		return employeeRepository.findById(employeeId);
	}

	public List<Employee> findAllEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}

	public Employee insertEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void deleteEmployeeById(int employeeId) {
		employeeRepository.deleteById(employeeId);
	}
}
