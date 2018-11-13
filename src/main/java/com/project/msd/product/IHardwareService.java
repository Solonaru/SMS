package com.project.msd.product;

import java.util.List;
import java.util.Optional;

public interface IHardwareService {

	public Optional<Hardware> findHardwareById(int hardwareId);

	public List<Hardware> findAllHardwares();

	public void insertHardware(Hardware hardware);

	public void updateHardware(Hardware hardware);

	public void deleteHardwareById(int hardwareId);
}
