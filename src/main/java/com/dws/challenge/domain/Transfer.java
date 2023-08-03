package com.dws.challenge.domain;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Transfer {
	
	
	 @NotNull
	  @NotEmpty
	  private  String accountFromId ;
	
	  @NotNull
	  @NotEmpty
	  private  String accountToId;
	 
	  @NotNull
	  @Min(value = 1, message = "Transfer amount  must be positive.")
	  private BigDecimal amount;
	  
	  public Transfer(String accontFromId, String accountToId)
	  {
		  this.accountFromId= accountFromId;
		  this.accountToId= accountToId;
		  this.amount= BigDecimal.ZERO;
	  }
	  
	  @JsonCreator
	  public Transfer(@JsonProperty("accountFromId") String accountFromId,
	    @JsonProperty("accountToId") String accountToId,
	    @JsonProperty("amount") BigDecimal amount) {

        this.accountFromId = accountFromId;
		this.accountToId = accountToId;
	    this.amount = amount;
	  }

	public String getAccountFromId() {
		return accountFromId;
	}

	public void setAccountFromId(String accountFromId) {
		this.accountFromId = accountFromId;
	}

	public String getAccountToId() {
		return accountToId;
	}

	public void setAccountToId(String accountToId) {
		this.accountToId = accountToId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
