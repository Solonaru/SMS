package com.project.sms.entities.catalogue;

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
@RequestMapping("/catalogueItem")
public class CatalogueItemController {

	@Autowired
	private ICatalogueItemService catalogueItemService;
	
	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{catalogueItemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<CatalogueItem> findCatalogueItemById(@PathVariable("catalogueItemId") int catalogueItemId) {
		dataDisplay.printCrudInfo(catalogueItemId);
		return catalogueItemService.findCatalogueItemById(catalogueItemId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CatalogueItem> getCategories() {
		dataDisplay.printCrudInfo();
		return catalogueItemService.findAllCatalogueItems();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCatalogueItem(@RequestBody CatalogueItem catalogueItem) {
		dataDisplay.printCrudInfo();
		catalogueItemService.insertCatalogueItem(catalogueItem);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCatalogueItem(@RequestBody CatalogueItem catalogueItem) {
		dataDisplay.printCrudInfo();
		catalogueItemService.updateCatalogueItem(catalogueItem);
	}

	@RequestMapping(value = "/delete/{catalogueItemId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCatalogueItem(@PathVariable("catalogueItemId") int catalogueItemId) {
		dataDisplay.printCrudInfo(catalogueItemId);
		catalogueItemService.deleteCatalogueItemById(catalogueItemId);
	}
}