package com.bank.application.service;

import java.util.Optional;

import com.bank.application.entity.Account;

public interface AccountService {
  Optional<Account> getAccountById(Long id);
}
