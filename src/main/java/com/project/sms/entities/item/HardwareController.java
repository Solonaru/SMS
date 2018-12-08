package com.project.sms.entities.item;

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
@RequestMapping("/hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {
	
	@Autowired
	private IHardwareService hardwareService;
	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{hardwareId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Hardware> findHardwareById(@PathVariable("hardwareId") int hardwareId) {
		dataDisplay.printCrudInfo(hardwareId); 
		return hardwareService.findHardwareById(hardwareId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hardware> getHardwares() {
		dataDisplay.printCrudInfo(); 
		return hardwareService.findAllHardwares();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCategory(@RequestBody Hardware hardware) {
		dataDisplay.printCrudInfo(); 
		hardwareService.insertHardware(hardware);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateHardware(@RequestBody Hardware hardware) {
		dataDisplay.printCrudInfo(); 
		hardwareService.updateHardware(hardware);
	}

	@RequestMapping(value = "/delete/{hardwareId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteHardware(@PathVariable("hardwareId") int hardwareId) {
		dataDisplay.printCrudInfo(hardwareId); 
		hardwareService.deleteHardwareById(hardwareId);
	}
}
