package com.project.sms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.project.sms.entities.location.City;
import com.project.sms.entities.location.County;

public class CityTestCase {
	
	City city;

	@Before
	public void setUp() throws Exception {
		city = new City();
	}

	@Test
	public void object_should_be_not_null() {		
		assertNotEquals(city, null);
	}
	
	@Test
	public void getId_should_return_null_if_not_set() {
		assertEquals(city.getId(), null);
	}
	
	@Test
	public void on_setId_getId_should_return_id() {
		Integer id = 1;
		
		city.setId(id);
		
		assertEquals(city.getId(), id);
	}
	
	@Test
	public void getName_should_return_null_if_not_set() {
		assertEquals(city.getName(), null);
	}
	
	@Test
	public void on_setName_getName_should_return_name() {
		String name = "Iasi";
		
		city.setName(name);
		
		assertEquals(city.getName(), name);
	}
	
	@Test
	public void getCounty_should_return_null_if_not_set() {
		assertEquals(city.getCounty(), null);
	}
	
	@Test
	public void on_setCounty_getCounty_should_return_county() {
		County county = new County("Iasi", "Iasi");
		
		city.setCounty(county);
		
		assertEquals(city.getCounty(), county);
	}

}
