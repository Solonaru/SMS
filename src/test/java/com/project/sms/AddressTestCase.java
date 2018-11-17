package com.project.sms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import com.project.sms.entities.location.Address;

public class AddressTestCase {
	
	Address address;

	@Before
	public void setUp() throws Exception {
		address = new Address();
	}

	@Test
	public void object_should_be_not_null() {		
		assertNotEquals(address, null);
	}
	
	@Test
	public void getId_should_return_null_if_not_set() {
		assertEquals(address.getId(), null);
	}
	
	@Test
	public void on_setId_getId_should_return_id() {
		Integer id = 1;
		
		address.setId(id);
		
		assertEquals(address.getId(), id);
	}
}
