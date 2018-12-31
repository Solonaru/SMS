package com.project.sms.entities.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.sms.utils.DisplayData;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Account> findAccountById(@PathVariable("accountId") int accountId) {
		dataDisplay.printCrudInfo(accountId);
		return accountService.findAccountById(accountId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAccounts() {
		dataDisplay.printCrudInfo();
		return accountService.findAllAccounts();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertAccount(@RequestBody Account account) {
		accountService.insertAccount(account);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateAccount(@RequestBody Account account) {
		dataDisplay.printCrudInfo();
		accountService.updateAccount(account);
	}

	@RequestMapping(value = "/delete/{accountId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteAccount(@PathVariable("accountId") int accountId) {
		dataDisplay.printCrudInfo(accountId);
		accountService.deleteAccountById(accountId);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Account findUserByUsernameAndPassword(@RequestBody Account account) {
		dataDisplay.printCrudInfo(); 
		return accountService.findUserByUsernameAndPassword(account.getUsername(), account.getPassword());
	}
}
