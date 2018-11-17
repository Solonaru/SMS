package com.project.sms.entities.location;

import java.util.List;
import java.util.Optional;

public interface IAddressService {
public Optional<Address> findAddressById(int addressId);
	
	public List<Address> findAllAddresses();

	public void insertAddress(Address address);

	public void updateAddress(Address address);

	public void deleteAddressById(int addressId);
}
