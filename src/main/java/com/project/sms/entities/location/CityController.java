package com.project.sms.entities.location;

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
@RequestMapping("/city")
@CrossOrigin(origins = "http://localhost:4200")
public class CityController {
	
	@Autowired
	private ICityService cityService;
	
	@Autowired
	private DisplayData dataDisplay;
	
	@RequestMapping(value = "/{cityId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<City> findCityById(@PathVariable("cityId") int cityId) {
		dataDisplay.printCrudInfo(cityId);
		return cityService.findCityById(cityId);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<City> getCities() {
		dataDisplay.printCrudInfo();
		return cityService.findAllCities();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCity(@RequestBody City city) {
		dataDisplay.printCrudInfo();
		cityService.insertCity(city);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCity(@RequestBody City city) {
		dataDisplay.printCrudInfo();
		cityService.updateCity(city);
	}
	
	@RequestMapping(value = "/delete/{cityId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCity(@PathVariable("cityId") int cityId) {
		dataDisplay.printCrudInfo(cityId);
		cityService.deleteCityById(cityId);
	}
}