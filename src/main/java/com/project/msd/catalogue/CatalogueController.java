package com.project.msd.catalogue;

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
@RequestMapping("/catalogue")
public class CatalogueController {

	@Autowired
	private ICatalogueService catalogueService;

	@RequestMapping(value = "/{catalogueId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Catalogue> findCatalogueById(@PathVariable("catalogueId") int catalogueId) {
		return catalogueService.findCatalogueById(catalogueId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Catalogue> getCatalogues() {
		return catalogueService.findAllCatalogues();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCatalogue(@RequestBody Catalogue catalogue) {
		catalogueService.insertCatalogue(catalogue);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCatalogue(@RequestBody Catalogue catalogue) {
		catalogueService.updateCatalogue(catalogue);
	}

	@RequestMapping(value = "/delete/{catalogueId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCatalogue(@PathVariable("catalogueId") int catalogueId) {
		catalogueService.deleteCatalogueById(catalogueId);
	}
}
