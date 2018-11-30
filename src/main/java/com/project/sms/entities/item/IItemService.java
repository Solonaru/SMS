package com.project.sms.entities.item;

import java.util.List;
import java.util.Optional;

public interface IItemService {
	
	public Optional<Item> findItemById(int itemId);

	public List<Item> findAllItems();

	public void insertItem(Item item);

	public void updateItem(Item item);

	public void deleteItemById(int itemId);
}
