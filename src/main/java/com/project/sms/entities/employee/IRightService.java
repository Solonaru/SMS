package com.project.sms.entities.employee;

import java.util.List;
import java.util.Optional;

public interface IRightService {
	
	public Optional<Right> findRightById(int rightId);

	public List<Right> findAllRights();

	public void insertRight(Right right);

	public void updateRight(Right right);

	public void deleteRightById(int rightId);
}