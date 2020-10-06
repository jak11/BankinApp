package com.bank.application.service;

import java.util.Optional;

import com.bank.application.entity.Customer;
import com.bank.application.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Optional<Customer> getCustomerById(Long Id) {
    if(Id == null){
      throw new IllegalArgumentException("Id is mandatory parameter");
    }
    return customerRepository.findById(Id);
  }
}
