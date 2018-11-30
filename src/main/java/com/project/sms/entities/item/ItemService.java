package com.project.sms.entities.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService implements IItemService{
	
	@Autowired
	private IItemRepository itemRepository;

	public Optional<Item> findItemById(int itemId) {
		return itemRepository.findById(itemId);
	}

	public List<Item> findAllItems() {
		return (List<Item>) itemRepository.findAll();
	}

	public void insertItem(Item item) {
		itemRepository.save(item);
	}

	public void updateItem(Item item) {
		itemRepository.save(item);
	}

	public void deleteItemById(int itemId) {
		itemRepository.deleteById(itemId);
	}
}
