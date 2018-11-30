package com.project.sms.entities.pack;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageService implements IPackageService {

	@Autowired
	private IPackageRepository packageRepository;

	public Optional<Package> findPackageById(int packageId) {
		return packageRepository.findById(packageId);
	}

	public List<Package> findAllPackages() {
		return (List<Package>) packageRepository.findAll();
	}

	public void insertPackage(Package pack) {
		packageRepository.save(pack);
	}

	public void updatePackage(Package pack) {
		packageRepository.save(pack);
	}

	public void deletePackageById(int packageId) {
		packageRepository.deleteById(packageId);
	}
	
}
