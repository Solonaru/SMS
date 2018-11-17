package com.project.sms.entities.location;

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
@RequestMapping("/county")
public class CountyController {
	
	@Autowired
	private ICountyService countyService;
	
	@RequestMapping(value = "/{countyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<County> findCountyById(@PathVariable("countyId") int countyId) {
		return countyService.findCountyById(countyId);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<County> getCounties() {
		return countyService.findAllCounties();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCounty(@RequestBody County county) {
		countyService.insertCounty(county);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCounty(@RequestBody County county) {
		countyService.updateCounty(county);
	}
	
	@RequestMapping(value = "/delete/{countyId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCounty(@PathVariable("countyId") int countyId) {
		countyService.deleteCountyById(countyId);
	}
}
