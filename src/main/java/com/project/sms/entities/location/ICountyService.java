package com.project.sms.entities.location;

import java.util.List;
import java.util.Optional;

public interface ICountyService {

	public Optional<County> findCountyById(int countyId);
	
	public List<County> findAllCounties();

	public void insertCounty(County county);

	public void updateCounty(County county);

	public void deleteCountyById(int countyId);
}
