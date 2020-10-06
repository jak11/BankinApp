package com.bank.application.service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import com.bank.application.entity.Account;
import com.bank.application.entity.PaginationInfo;
import com.bank.application.entity.Sorting;
import com.bank.application.entity.Transaction;
import com.bank.application.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private AccountService accountService;

  @Override
  public Optional<Transaction> getTransactionById(Long Id) {
    if (Id == null) {
      throw new IllegalArgumentException("Id is mandatory parameter");
    }
    return transactionRepository.findById(Id);
  }

  @Override
  public List<Transaction> getTransactionsByAccountId(Long accountId, PaginationInfo info, Sorting sortingOrder, String sortBy) {
    Optional<Account> optionalAccount = accountService.getAccountById(accountId);
    if (accountId == null || !optionalAccount.isPresent()) {
      throw new InvalidParameterException("accountId is invalid");
    }
    Account account = optionalAccount.get();


    if (info == null) {
      throw new IllegalArgumentException("Pagination Info is required");
    }

    if (sortBy == null || sortBy.isEmpty()) {
      sortBy = "timestamp"; //default sortBy field
    }

    Sort sort = null;
    if (sortingOrder == null || sortingOrder == Sorting.DESCENDING) {
      sort = Sort.by(sortBy).descending(); //default sorting
    } else if (sortingOrder == Sorting.ASCENDING) {
      sort = Sort.by(sortBy).ascending();
    }

    Pageable pageable = PageRequest.of(info.getPage_number(), info.getPage_size(), sort);
    return transactionRepository.findByAccountId(accountId, pageable);
  }
}
