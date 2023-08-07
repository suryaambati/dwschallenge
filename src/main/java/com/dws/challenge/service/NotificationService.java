package com.dws.challenge.service;

import com.dws.challenge.domain.Account;
import com.dws.challenge.domain.Transfer;

public interface NotificationService {

  void notifyAboutTransfer(Account account, String transferDescription);
  void notifyAboutTransfer(Transfer account, String transferDescription);
}
