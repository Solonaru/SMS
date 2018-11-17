package com.project.sms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import com.project.sms.entities.location.County;

public class CountyTestCase {
	
	County county;

	@Before
	public void setUp() throws Exception {
		county = new County();
	}

	@Test
	public void object_should_be_not_null() {		
		assertNotEquals(county, null);
	}
	
	@Test
	public void getId_should_return_null_if_not_set() {
		assertEquals(county.getId(), null);
	}
	
	@Test
	public void on_setId_getId_should_return_id() {
		Integer id = 1;
		
		county.setId(id);
		
		assertEquals(county.getId(), id);
	}
	
	@Test
	public void getName_should_return_null_if_not_set() {
		assertEquals(county.getName(), null);
	}
	
	@Test
	public void on_setName_getName_should_return_name() {
		String name = "Iasi";
		
		county.setName(name);
		
		assertEquals(county.getName(), name);
	}
	
	@Test
	public void getRegion_should_return_null_if_not_set() {
		assertEquals(county.getRegion(), null);
	}
	
	@Test
	public void on_setRegion_getRegion_should_return_region() {
		String region = "Iasi";
		
		county.setRegion(region);
		
		assertEquals(county.getRegion(), region);
	}
}
