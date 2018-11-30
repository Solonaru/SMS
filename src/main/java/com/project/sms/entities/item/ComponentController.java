package com.project.sms.entities.item;

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
@RequestMapping("/component")
public class ComponentController {

	@Autowired
	private IComponentService componentService;

	@RequestMapping(value = "/{componentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Component> findComponentById(@PathVariable("componentId") int componentId) {
		return componentService.findComponentById(componentId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Component> getComponents() {
		return componentService.findAllComponents();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertComponent(@RequestBody Component component) {
		componentService.insertComponent(component);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateComponent(@RequestBody Component component) {
		componentService.updateComponent(component);
	}

	@RequestMapping(value = "/delete/{componentId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteComponent(@PathVariable("componentId") int componentId) {
		componentService.deleteComponentById(componentId);
	}
}
