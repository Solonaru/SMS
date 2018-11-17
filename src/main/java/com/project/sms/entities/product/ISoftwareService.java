package com.project.sms.entities.product;

import java.util.List;
import java.util.Optional;

public interface ISoftwareService {

	public Optional<Software> findSoftwareById(int softwareId);

	public List<Software> findAllSoftwares();

	public void insertSoftware(Software software);

	public void updateSoftware(Software software);

	public void deleteSoftwareById(int softwareId);
}