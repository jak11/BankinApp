package com.bank.application.assembler;

import com.bank.application.controlllers.CustomerController;
import com.bank.application.entity.Customer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {

  @Override
  public EntityModel<Customer> toModel(Customer customer) {
    return EntityModel.of(customer, //
        linkTo(methodOn(CustomerController.class).one(customer.getId())).withSelfRel());
  }
}
