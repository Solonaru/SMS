package com.project.sms.entities.catalogue;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogueItemService implements ICatalogueItemService {
	
	@Autowired
	private ICatalogueItemRepository catalogueItemRepository;

	public Optional<CatalogueItem> findCatalogueItemById(int catalogueItemId) {
		return catalogueItemRepository.findById(catalogueItemId);
	}

	public List<CatalogueItem> findAllCatalogueItems() {
		return (List<CatalogueItem>) catalogueItemRepository.findAll();
	}

	public void insertCatalogueItem(CatalogueItem catalogueItem) {
		catalogueItemRepository.save(catalogueItem);
	}

	public void updateCatalogueItem(CatalogueItem catalogueItem) {
		catalogueItemRepository.save(catalogueItem);
	}

	public void deleteCatalogueItemById(int catalogueItemId) {
		catalogueItemRepository.deleteById(catalogueItemId);
	}
}
