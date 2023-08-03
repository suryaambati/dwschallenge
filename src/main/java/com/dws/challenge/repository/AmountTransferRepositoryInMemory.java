package com.dws.challenge.repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.dws.challenge.domain.Account;
import com.dws.challenge.domain.Transfer;
import com.dws.challenge.exception.AmountTranferException;
import com.dws.challenge.exception.DuplicateAccountIdException;

public class AmountTransferRepositoryInMemory implements AmountTransferRepository {

	 
	
	 private final Map<Integer, Transfer> Transaction = new ConcurrentHashMap<>();
	 boolean status=false;
	 
	 @Autowired
	 public AccountsRepository accountreposiroty;
	
	 public boolean CreateTransaction (Transfer tranferdata) throws AmountTranferException
	{		
		Transfer checkstatus = Transaction.put(tranferdata.hashCode(), tranferdata);
	     
		return status;
	}
	 
	
	  
	 
	@Override
	public Transfer getTransferData(int hashcode) {
		Transfer data = Transaction.get(hashcode);
		return data;
	}}
