package com.bank.application.controlllers;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import com.bank.application.entity.Customer;
import com.bank.application.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class CustomerController {
  @Autowired
  private CustomerService customerService;

  @GetMapping("/customers/{id}")
  public EntityModel<Customer> one(@PathVariable @NotNull Long id) {
    Optional<Customer> customer = customerService.getCustomerById(id);
    return customer.map(EntityModel::of).orElseThrow(() -> new RuntimeException("Customer doesn't exists with provided Id"));
  }
}
