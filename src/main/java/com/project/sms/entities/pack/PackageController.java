package com.project.sms.entities.pack;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/package")
public class PackageController {

	@Autowired
	private IPackageService packageService;

	@RequestMapping(value = "/{packageId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Package> findPackageById(@PathVariable("packageId") int packageId) {
		return packageService.findPackageById(packageId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Package> getPackages() {
		return packageService.findAllPackages();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertPackage(@RequestBody Package pack) {
		packageService.insertPackage(pack);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updatePackage(@RequestBody Package pack) {
		packageService.updatePackage(pack);
	}

	@RequestMapping(value = "/delete/{packageId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deletePackage(@PathVariable("packageId") int packageId) {
		packageService.deletePackageById(packageId);
	}

}
