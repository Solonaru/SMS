package com.project.msd.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HardwareService implements IHardwareService{

	@Autowired
	private IHardwareRepository hardwareRepository;

	public Optional<Hardware> findHardwareById(int hardwareId) {
		return hardwareRepository.findById(hardwareId);
	}

	public List<Hardware> findAllHardwares() {
		return (List<Hardware>) hardwareRepository.findAll();
	}

	public void insertHardware(Hardware hardware) {
		hardwareRepository.save(hardware);
	}

	public void updateHardware(Hardware hardware) {
		hardwareRepository.save(hardware);
	}

	public void deleteHardwareById(int hardwareId) {
		hardwareRepository.deleteById(hardwareId);
	}
}
