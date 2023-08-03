package com.dws.challenge.service;

import com.dws.challenge.domain.Account;
import com.dws.challenge.domain.Transfer;

import lombok.extern.slf4j.Slf4j;


public class EmailNotificationService implements NotificationService {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EmailNotificationService.class);

	
  @Override
  public void notifyAboutTransfer(Account account, String transferDescription) {
    //THIS METHOD SHOULD NOT BE CHANGED - ASSUME YOUR COLLEAGUE WILL IMPLEMENT IT
    log
      .info("Sending notification to owner of {}: {}", transferDescription);
  }
  
  @Override
  public void notifyAboutTransfer(Transfer transferData, String transferDescription) {
    //THIS METHOD SHOULD NOT BE CHANGED - ASSUME YOUR COLLEAGUE WILL IMPLEMENT IT
    log
      .info("Sending notification to owner of {}: {}", transferDescription);
  }

}
