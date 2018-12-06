package com.project.sms.entities.item;

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
@RequestMapping("/item")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {

	@Autowired
	private IItemService itemService;
	
	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{itemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Item> findItemById(@PathVariable("itemId") int itemId) {
		dataDisplay.printCrudInfo(itemId); 
		return itemService.findItemById(itemId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Item> getItems() {
		dataDisplay.printCrudInfo(); 
		return itemService.findAllItems();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertItem(@RequestBody Item item) {
		dataDisplay.printCrudInfo(); 
		itemService.insertItem(item);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateItem(@RequestBody Item item) {
		dataDisplay.printCrudInfo(); 
		itemService.updateItem(item);
	}

	@RequestMapping(value = "/delete/{itemId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteItem(@PathVariable("itemId") int itemId) {
		dataDisplay.printCrudInfo(itemId); 
		itemService.deleteItemById(itemId);
	}
}
