package com.project.sms.entities.order;

import java.util.List;
import java.util.Optional;

public interface IReturnService {

	public Optional<Return> findReturnById(int returnId);
	
	public List<Return> findAllReturns();

	public void insertReturn(Return returned);

	public void updateReturn(Return returned);

	public void deleteReturnById(int returned);
}
