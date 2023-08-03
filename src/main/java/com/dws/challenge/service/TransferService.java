package com.dws.challenge.service;

import com.dws.challenge.domain.Account;
import com.dws.challenge.domain.Transfer;
import com.dws.challenge.exception.AmountTranferException;
import com.dws.challenge.repository.AccountsRepository;
import com.dws.challenge.repository.AmountTransferRepository;

import lombok.Getter;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

  @Getter
  private final AmountTransferRepository amoutTransferRepository;

  @Autowired
  public TransferService(AmountTransferRepository amoutTransferRepository) {
    this.amoutTransferRepository = amoutTransferRepository;
  }

  @Autowired
  public AccountsRepository accountRepository;

  @Autowired
  public EmailNotificationService emailNotificationService;
  
  
  
  public void CreateTransaction(@Valid Transfer transferData) throws AmountTranferException,Exception {
	     
	    boolean status = false;
	 
	  // Fetching the Account details using input acount IDs  		
		Account fromacount = accountRepository.getAccount(transferData.getAccountFromId());
	  	Account toaccount  =accountRepository.getAccount(transferData.getAccountToId());
	  	
	  	if(fromacount.getBalance().compareTo(BigDecimal.ZERO)>=0 && fromacount.getBalance().compareTo(transferData.getAmount())  >=0)
	  	{
	  		
	  	// AMOUNT TRANSFER FUNCTIONALITY (detecting from FromAccount and adding to ToAccount)	
	  	// amount detecting from FromAccount
	    Account updatefromacc = new Account(fromacount.getAccountId(), (fromacount.getBalance().subtract(transferData.getAmount())));
	    accountRepository.updateAccount(updatefromacc);
	    //amount adding to ToAccount
	    Account updatetoacc = new Account(toaccount.getAccountId(),(toaccount.getBalance().add(transferData.getAmount())));
	    accountRepository.updateAccount(updatefromacc);
	    
	    //storing data for audit
		 amoutTransferRepository.CreateTransaction(transferData);
	
      // Email Notification 
      	 emailNotificationService.notifyAboutTransfer(transferData, "Successfull Transaction");
    
	 
	  	}
	  	else
	  	{
	  		throw new AmountTranferException("Transaction falue due insuffient funds");
	  	}
	 
  }


 
}
