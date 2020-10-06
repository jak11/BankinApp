package com.bank.application.service;

import java.util.Optional;

import com.bank.application.entity.Customer;

public interface CustomerService {
  Optional<Customer> getCustomerById(Long Id);
}
