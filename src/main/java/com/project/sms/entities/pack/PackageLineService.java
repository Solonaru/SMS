package com.project.sms.entities.pack;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageLineService implements IPackageLineService {

	@Autowired
	private IPackageLineRepository packageLineRepository;

	public Optional<PackageLine> findPackageLineById(int packageLineId) {
		return packageLineRepository.findById(packageLineId);
	}

	public List<PackageLine> findAllPackageLines() {
		return (List<PackageLine>) packageLineRepository.findAll();
	}

	public void insertPackageLine(PackageLine packageLine) {
		packageLineRepository.save(packageLine);
	}

	public void updatePackageLine(PackageLine packageLine) {
		packageLineRepository.save(packageLine);
	}

	public void deletePackageLineById(int packageLineId) {
		packageLineRepository.deleteById(packageLineId);
	}

}
