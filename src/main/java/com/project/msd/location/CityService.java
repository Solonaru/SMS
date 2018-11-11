package com.project.msd.location;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {

	@Autowired
	private ICityRepository cityRepository;

	public Optional<City> findCityById(int cityId) {
		return cityRepository.findById(cityId);
	}

	public List<City> findAllCities() {
		return (List<City>) cityRepository.findAll();
	}

	public void insertCity(City city) {
		cityRepository.save(city);
	}

	public void updateCity(City city) {
		cityRepository.save(city);
	}

	public void deleteCityById(int cityId) {
		cityRepository.deleteById(cityId);
	}
}