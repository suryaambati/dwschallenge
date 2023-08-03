package com.dws.challenge.repository;


import com.dws.challenge.domain.Transfer;
import com.dws.challenge.exception.AmountTranferException;

public interface AmountTransferRepository {
	
 public boolean CreateTransaction (Transfer tranferdata) throws AmountTranferException;
 
  Transfer getTransferData(int hashcode);



}
