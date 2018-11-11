package com.project.msd.employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

	public Optional<Employee> findEmployeeById(int employeeId);

	public List<Employee> findAllEmployees();

	public void insertEmployee(Employee employee);

	public void updateEmployee(Employee employee);

	public void deleteEmployeeById(int employeeId);
}
