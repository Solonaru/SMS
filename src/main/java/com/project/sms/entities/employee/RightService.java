package com.project.sms.entities.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RightService implements IRightService {

	@Autowired
	private IRightRepository rightRepository;

	public Optional<Right> findRightById(int rightId) {
		return rightRepository.findById(rightId);
	}

	public List<Right> findAllRights() {
		return (List<Right>) rightRepository.findAll();
	}

	public void insertRight(Right right) {
		rightRepository.save(right);
	}

	public void updateRight(Right right) {
		rightRepository.save(right);
	}

	public void deleteRightById(int rightId) {
		rightRepository.deleteById(rightId);
	}
}
