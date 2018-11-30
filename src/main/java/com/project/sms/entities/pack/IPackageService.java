package com.project.sms.entities.pack;

import java.util.List;
import java.util.Optional;

public interface IPackageService {

	public Optional<Package> findPackageById(int packageId);

	public List<Package> findAllPackages();

	public void insertPackage(Package pack);

	public void updatePackage(Package pack);

	public void deletePackageById(int packageId);

}
