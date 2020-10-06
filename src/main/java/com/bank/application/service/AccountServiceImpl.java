package com.bank.application.service;

import java.util.Optional;

import com.bank.application.entity.Account;
import com.bank.application.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{
  @Autowired
  private AccountRepository accountRepository;

  @Override
  public Optional<Account> getAccountById(Long Id) {
    if(Id == null){
      throw new IllegalArgumentException("Id is mandatory parameter");
    }
    return accountRepository.findById(Id);
  }
}
