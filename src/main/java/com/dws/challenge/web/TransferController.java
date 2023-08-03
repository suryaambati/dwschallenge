package com.dws.challenge.web;

import com.dws.challenge.domain.Transfer;
import com.dws.challenge.exception.AmountTranferException;
import com.dws.challenge.service.TransferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/transfer")

public class TransferController {
	
	 private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TransferController.class);

	 private final TransferService transferService;
	 
		@Autowired
		public TransferController(TransferService transferService) {
			this.transferService = transferService;
		}
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE )
	 public	 ResponseEntity<Object> TransferAmount(@RequestBody @Valid Transfer transferdata) {
	
    log.info("TransferAmount {}", transferdata);
	 
	 try {
		
		 this.transferService.CreateTransaction(transferdata); 
	 } catch (Exception exp) { 
		 
		 return new ResponseEntity<>(exp.getMessage(), HttpStatus.BAD_REQUEST); 
	 }
	
	
	 return new ResponseEntity<>(HttpStatus.CREATED); }
	 }
