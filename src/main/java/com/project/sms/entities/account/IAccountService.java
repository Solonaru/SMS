package com.project.sms.entities.account;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
	
	public Optional<Account> findAccountById(int accountId);

	public List<Account> findAllAccounts();

	public void insertAccount(Account account);

	public void updateAccount(Account account);

	public void deleteAccountById(int accountId);
}
