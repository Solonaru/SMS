package com.project.msd.location;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountyService implements ICountyService {
	
	@Autowired
	private ICountyRepository countyRepository;

	public Optional<County> findCountyById(int countyId) {
		return countyRepository.findById(countyId);
	}
	
	public List<County> findAllCounties() {
		return (List<County>) countyRepository.findAll();
	}

	public void insertCounty(County county) {
		countyRepository.save(county);		
	}

	public void updateCounty(County county) {
		countyRepository.save(county);		
	}

	public void deleteCountyById(int countyId) {
		countyRepository.deleteById(countyId);		
	}
}
