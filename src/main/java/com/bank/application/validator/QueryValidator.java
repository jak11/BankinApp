package com.bank.application.validator;

import org.springframework.stereotype.Component;

@Component
public class QueryValidator {
  public boolean validate(String query){
    if(!query.contains(":")){
      return false;
    }
    return true;
  }
}
