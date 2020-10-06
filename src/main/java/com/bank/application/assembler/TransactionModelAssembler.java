package com.bank.application.assembler;

import com.bank.application.controlllers.TransactionController;
import com.bank.application.entity.Transaction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TransactionModelAssembler implements RepresentationModelAssembler<Transaction, EntityModel<Transaction>> {

  @Override
  public EntityModel<Transaction> toModel(Transaction Transaction) {
    return EntityModel.of(Transaction, //
        linkTo(methodOn(TransactionController.class).one(Transaction.getId())).withSelfRel());
  }
}
