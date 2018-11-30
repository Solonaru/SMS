package com.project.sms.entities.catalogue;

import java.util.List;
import java.util.Optional;

public interface ICatalogueItemService {
	
	public Optional<CatalogueItem> findCatalogueItemById(int catalogueItemId);

	public List<CatalogueItem> findAllCatalogueItems();

	public void insertCatalogueItem(CatalogueItem catalogueItem);

	public void updateCatalogueItem(CatalogueItem catalogueItem);

	public void deleteCatalogueItemById(int catalogueItemId);
}