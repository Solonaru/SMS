package com.project.sms.entities.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService{
	
	@Autowired
	private IAccountRepository accountRepository;

	public Optional<Account> findAccountById(int accountId) {
		return accountRepository.findById(accountId);
	}

	public List<Account> findAllAccounts() {
		return (List<Account>) accountRepository.findAll();
	}

	public void insertAccount(Account account) {
		accountRepository.save(account);
	}

	public void updateAccount(Account account) {
		accountRepository.save(account);
	}

	public void deleteAccountById(int accountId) {
		accountRepository.deleteById(accountId);
	}
	
	public Account findUserByUsernameAndPassword(String username, String password) {
		return accountRepository.findUserByUsernameAndPassword(username, password);
	}
}
