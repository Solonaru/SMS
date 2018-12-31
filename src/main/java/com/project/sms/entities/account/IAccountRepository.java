package com.project.sms.entities.account;

import org.springframework.data.repository.CrudRepository;

public interface IAccountRepository extends CrudRepository<Account, Integer> {

	public Account findUserByUsernameAndPassword(String username, String password);

}
