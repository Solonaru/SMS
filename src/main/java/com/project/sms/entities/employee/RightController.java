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
@RequestMapping("/right")
@CrossOrigin(origins = "http://localhost:4200")
public class RightController {

	@Autowired
	private IRightService rightService;

	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{rightId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Right> findRightyById(@PathVariable("rightId") int rightId) {
		dataDisplay.printCrudInfo(rightId);
		return rightService.findRightById(rightId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Right> getRights() {
		dataDisplay.printCrudInfo();
		return rightService.findAllRights();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertRight(@RequestBody Right right) {
		dataDisplay.printCrudInfo();
		rightService.insertRight(right);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateRight(@RequestBody Right right) {
		dataDisplay.printCrudInfo();
		rightService.updateRight(right);
	}

	@RequestMapping(value = "/delete/{rightId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteRight(@PathVariable("rightId") int rightId) {
		dataDisplay.printCrudInfo(rightId);
		rightService.deleteRightById(rightId);
	}
}