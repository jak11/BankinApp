package com.bank.application.controlllers;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bank.application.assembler.TransactionModelAssembler;
import com.bank.application.entity.Account;
import com.bank.application.entity.PaginationInfo;
import com.bank.application.entity.Sorting;
import com.bank.application.entity.Transaction;
import com.bank.application.service.AccountService;
import com.bank.application.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Validated
public class AccountController {
  @Autowired
  private AccountService accountService;

  @Autowired
  private TransactionModelAssembler assembler;

  @Autowired
  private TransactionService transactionService;

  @GetMapping("/accounts/{id}")
  public EntityModel<Account> one(@PathVariable @NotNull Long id) {
    Optional<Account> account = accountService.getAccountById(id);
    return account.map(EntityModel::of).orElseThrow(() -> new RuntimeException("Account doesn't exists with provided Id"));
  }

  @GetMapping("/accounts/{id}/transactions")
  public CollectionModel<EntityModel<Transaction>> getTransactionsByAccountId(@PathVariable @NotNull Long id) {
    List<Transaction> transactions = transactionService.getTransactionsByAccountId(id, new PaginationInfo(), Sorting.DESCENDING, null);

    List<EntityModel<Transaction>> entityModels = transactions.stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

    return CollectionModel.of(entityModels, linkTo(methodOn(AccountController.class).getTransactionsByAccountId(id)).withSelfRel());
  }
}
