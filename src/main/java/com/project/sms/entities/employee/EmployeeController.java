package com.project.sms.entities.employee;

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
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Employee> findEmployeeById(@PathVariable("employeeId") int employeeId) {
		dataDisplay.printCrudInfo(employeeId);
		return employeeService.findEmployeeById(employeeId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getEmployees() {
		dataDisplay.printCrudInfo();
		return employeeService.findAllEmployees();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Employee insertEmployee(@RequestBody Employee employee) {
		dataDisplay.printCrudInfo();
		return employeeService.insertEmployee(employee);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateEmployee(@RequestBody Employee employee) {
		dataDisplay.printCrudInfo();
		employeeService.updateEmployee(employee);
	}

	@RequestMapping(value = "/delete/{employeeId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteEmployee(@PathVariable("employeeId") int employeeId) {
		dataDisplay.printCrudInfo(employeeId);
		employeeService.deleteEmployeeById(employeeId);
	}
}
