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

import com.project.sms.utils.DisplayData;

@RestController
@RequestMapping("/packageLine")
public class PackageLineController {

	@Autowired
	private IPackageLineService packageLineService;
	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{packageLineId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<PackageLine> findPackageLineById(@PathVariable("packageLineId") int packageLineId) {
		dataDisplay.printCrudInfo(packageLineId); 
		return packageLineService.findPackageLineById(packageLineId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PackageLine> getPackageLines() {
		dataDisplay.printCrudInfo(); 
		return packageLineService.findAllPackageLines();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertPackageLine(@RequestBody PackageLine packageLine) {
		dataDisplay.printCrudInfo(); 
		packageLineService.insertPackageLine(packageLine);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updatePackageLine(@RequestBody PackageLine packageLine) {
		dataDisplay.printCrudInfo(); 
		packageLineService.updatePackageLine(packageLine);
	}

	@RequestMapping(value = "/delete/{packageLineId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deletePackageLine(@PathVariable("packageLineId") int packageLineId) {
		dataDisplay.printCrudInfo(packageLineId);
		packageLineService.deletePackageLineById(packageLineId);
	}

}
