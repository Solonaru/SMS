package com.project.msd.location;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {

	@Autowired
	private IAddressRepository addressRepository;

	public Optional<Address> findAddressById(int addressId) {
		return addressRepository.findById(addressId);
	}

	public List<Address> findAllAddresses() {
		return (List<Address>) addressRepository.findAll();
	}

	public void insertAddress(Address address) {
		addressRepository.save(address);
	}

	public void updateAddress(Address address) {
		addressRepository.save(address);
	}

	public void deleteAddressById(int addressId) {
		addressRepository.deleteById(addressId);
	}
}
