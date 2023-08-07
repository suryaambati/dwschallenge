package com.dws.challenge.service;

import com.dws.challenge.domain.Account;
import com.dws.challenge.domain.Transfer;
import com.dws.challenge.exception.AmountTranferException;
import com.dws.challenge.repository.AccountsRepository;
import lombok.Getter;

import java.lang.invoke.VolatileCallSite;
import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

  @Getter
  private final AccountsRepository accountsRepository;
  
  @Autowired
  public EmailNotificationService emailNotificationService;

  @Autowired
  public AccountsService(AccountsRepository accountsRepository) {
    this.accountsRepository = accountsRepository;
  }

  public void createAccount(Account account) {
    this.accountsRepository.createAccount(account);
  }

  public Account getAccount(String accountId) {
    return this.accountsRepository.getAccount(accountId);
  }
  
  
  //creteTransfer
  //checking from account balance and debit the amount from FromAccount
  //credited to Toaccount
  
  public void createTransaction(@Valid Transfer transferData) throws AmountTranferException,Exception {
	     
	    boolean status = false;
	 
	  // Fetching the Account details using input acount IDs  		
		Account fromacount = accountsRepository.getAccount(transferData.getAccountFromId());
	  	Account toaccount  =accountsRepository.getAccount(transferData.getAccountToId());
	  	
	   BigDecimal fromBalance=BigDecimal.ZERO;
	   BigDecimal toBalance = BigDecimal.ZERO;
	  
	  	
	  	if(fromacount.getBalance().compareTo(BigDecimal.ZERO)>=0 && fromacount.getBalance().compareTo(transferData.getAmount())  >=0)
	  	{
	  		
	  	// AMOUNT TRANSFER FUNCTIONALITY (detecting from FromAccount and adding to ToAccount)	
	  	// amount detecting from FromAccount
	  		
	  	
	  		synchronized (this) {
	  			fromBalance =fromacount.getBalance().subtract(transferData.getAmount());
	  			toBalance =toaccount.getBalance().add(transferData.getAmount());
			}
	    Account updatefromacc = new Account(fromacount.getAccountId(), fromBalance);
	    accountsRepository.updateAccount(updatefromacc);
	    //amount adding to ToAccount
	    Account updatetoacc = new Account(toaccount.getAccountId(),toBalance);
	    accountsRepository.updateAccount(updatefromacc);
	    
	    //storing data for audit
	    accountsRepository.createTransaction(transferData);
	
    // Email Notification 
    	 emailNotificationService.notifyAboutTransfer(transferData, "Successfull Transaction");
  
	 
	  	}
	  	else
	  	{
	  		throw new AmountTranferException("Transaction falue due insuffient funds");
	  	}
	 
}
  
  
}
