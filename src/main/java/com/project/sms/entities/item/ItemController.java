package com.project.sms.entities.item;

import java.util.ArrayList;
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

import com.project.sms.entities.category.ICategoryService;
import com.project.sms.utils.DisplayData;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {

	@Autowired
	private IItemService itemService;

	@Autowired
	private ICategoryService categoryService;

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

	@RequestMapping(value = "/all/listed", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Item> getListedItems() {
		dataDisplay.printCrudInfo();
		List<Item> listedItems = new ArrayList<Item>();
		for (Item item : itemService.findAllItems()) {
			if (item.isListed()) {
				listedItems.add(item);
			}
		}

		return listedItems;
	}

	@RequestMapping(value = "/all/listed/{categoryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Item> getListedItemsByCategoryId(@PathVariable("categoryId") int categoryId) {
		dataDisplay.printCrudInfo();
		List<Item> listedItems = new ArrayList<Item>();
		for (Item item : itemService.findAllItems()) {
			if (this.checkItem(item, categoryId)) {
				listedItems.add(item);
			}
		}

		return listedItems;
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

	// ----- Helper methods -----
	private boolean checkItem(Item item, int categoryId) {
		boolean ret_val = false;

		if (item.isListed()) {
			if (item.getCategory().getId().equals(categoryId)) {
				ret_val = true;
			} else if (categoryService.findCategoryById(categoryId).get().getChildCategories()
					.indexOf(item.getCategory()) != -1) {
				ret_val = true;
			}
		}

		return ret_val;
	}
}
