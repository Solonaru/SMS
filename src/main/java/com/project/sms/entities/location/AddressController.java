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
@RequestMapping("/address")
@CrossOrigin(origins = "http://localhost:4200")
public class AddressController {

	@Autowired
	private IAddressService addressService;
	
	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{addressId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Address> findAddressById(@PathVariable("addressId") int addressId) {
		dataDisplay.printCrudInfo(addressId);
		return addressService.findAddressById(addressId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Address> getAddresses() {
		dataDisplay.printCrudInfo();
		return addressService.findAllAddresses();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertAddress(@RequestBody Address address) {
		dataDisplay.printCrudInfo();
		addressService.insertAddress(address);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateAddress(@RequestBody Address address) {
		dataDisplay.printCrudInfo();
		addressService.updateAddress(address);
	}

	@RequestMapping(value = "/delete/{addressId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteAddress(@PathVariable("addressId") int addressId) {
		dataDisplay.printCrudInfo(addressId);
		addressService.deleteAddressById(addressId);
	}
}
