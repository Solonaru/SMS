package com.project.msd.catalogue;

import java.util.List;
import java.util.Optional;

public interface ICatalogueService {
	
	public Optional<Catalogue> findCatalogueById(int catalogueId);

	public List<Catalogue> findAllCatalogues();

	public void insertCatalogue(Catalogue catalogue);

	public void updateCatalogue(Catalogue catalogue);

	public void deleteCatalogueById(int catalogueId);
}
