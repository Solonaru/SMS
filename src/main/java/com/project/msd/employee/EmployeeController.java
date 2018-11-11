package com.project.msd.employee;

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
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Employee> findEmployeeById(@PathVariable("employeeId") int employeeId) {
		return employeeService.findEmployeeById(employeeId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getEmployees() {
		return employeeService.findAllEmployees();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertEmployee(@RequestBody Employee employee) {
		employeeService.insertEmployee(employee);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateEmployee(@RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
	}

	@RequestMapping(value = "/delete/{employeeId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteEmployee(@PathVariable("employeeId") int employeeId) {
		employeeService.deleteEmployeeById(employeeId);
	}
}
