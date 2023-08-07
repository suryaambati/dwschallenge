package com.dws.challenge.repository;

import com.dws.challenge.domain.Account;
import com.dws.challenge.domain.Transfer;
import com.dws.challenge.exception.AmountTranferException;
import com.dws.challenge.exception.DuplicateAccountIdException;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountsRepositoryInMemory implements AccountsRepository {

    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final Map<Integer, Transfer> transaction = new ConcurrentHashMap<>();
	boolean status=false;
	 

    @Override
    public void createAccount(Account account) throws DuplicateAccountIdException {
    	Account previousAccount = accounts.putIfAbsent(account.getAccountId(), account);
        if (previousAccount != null) {
            throw new DuplicateAccountIdException(
                    "Account id "+ " already exists!");
        }
    }

    @Override
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    @Override
    public void clearAccounts() {
        accounts.clear();
    }
    
    @Override
    public void updateAccount(Account account) throws Exception {
    	accounts.replace(account.getAccountId(), account );
    	
    }
    

	@Override
    public boolean createTransaction (Transfer tranferData) throws AmountTranferException
	{		
		Transfer checkStatus = transaction.put(tranferData.hashCode(), tranferData);
	     
		return status;
	}
	 
	
	 
	@Override
	public Transfer getTransferData(int hashcode) {
		Transfer data = transaction.get(hashcode);
		return data;
	}

}
