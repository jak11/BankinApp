package com.bank.application.controlllers;

import javax.validation.constraints.NotNull;

import java.util.Optional;

import com.bank.application.entity.Transaction;
import com.bank.application.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class TransactionController {
  @Autowired
  private TransactionService transactionService;

  @GetMapping("/transactions/{id}")
  public EntityModel<Transaction> one(@PathVariable @NotNull Long id) {
    Optional<Transaction> transaction = transactionService.getTransactionById(id);
    return transaction.map(EntityModel::of).orElseThrow(() -> new RuntimeException("Transaction doesn't exists with provided Id"));
  }
}
