package com.project.msd.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnService implements IReturnService{

	@Autowired
	private IReturnRepository returnRepository;

	public Optional<Return> findReturnById(int returnId) {
		return returnRepository.findById(returnId);
	}
	
	public List<Return> findAllReturns() {
		return (List<Return>) returnRepository.findAll();
	}

	public void insertReturn(Return returned) {
		returnRepository.save(returned);		
	}

	public void updateReturn(Return returned) {
		returnRepository.save(returned);		
	}

	public void deleteReturnById(int returnId) {
		returnRepository.deleteById(returnId);		
	}
}
