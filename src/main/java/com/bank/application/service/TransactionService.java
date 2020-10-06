package com.bank.application.service;

import java.util.List;
import java.util.Optional;

import com.bank.application.entity.PaginationInfo;
import com.bank.application.entity.Sorting;
import com.bank.application.entity.Transaction;

public interface TransactionService {
  Optional<Transaction> getTransactionById(Long Id);
  List<Transaction> getTransactionsByAccountId(Long accountId, PaginationInfo info, Sorting sortingOrder, String sortBy);
}
