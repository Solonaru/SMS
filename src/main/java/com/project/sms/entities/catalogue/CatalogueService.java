package com.project.sms.entities.catalogue;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogueService implements ICatalogueService{
	
	@Autowired
	private ICatalogueRepository catalogueRepository;

	public Optional<Catalogue> findCatalogueById(int catalogueId) {
		return catalogueRepository.findById(catalogueId);
	}

	public List<Catalogue> findAllCatalogues() {
		return (List<Catalogue>) catalogueRepository.findAll();
	}

	public void insertCatalogue(Catalogue catalogue) {
		catalogueRepository.save(catalogue);
	}

	public void updateCatalogue(Catalogue catalogue) {
		catalogueRepository.save(catalogue);
	}

	public void deleteCatalogueById(int catalogueId) {
		catalogueRepository.deleteById(catalogueId);
	}
}
