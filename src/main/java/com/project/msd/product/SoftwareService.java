package com.project.msd.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoftwareService implements ISoftwareService {

	@Autowired
	private ISoftwareRepository softwareRepository;

	public Optional<Software> findSoftwareById(int softwareId) {
		return softwareRepository.findById(softwareId);
	}

	public List<Software> findAllSoftwares() {
		return (List<Software>) softwareRepository.findAll();
	}

	public void insertSoftware(Software software) {
		softwareRepository.save(software);
	}

	public void updateSoftware(Software software) {
		softwareRepository.save(software);
	}

	public void deleteSoftwareById(int softwareId) {
		softwareRepository.deleteById(softwareId);
	}
}