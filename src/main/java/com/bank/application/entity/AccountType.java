package com.bank.application.entity;

import java.io.Serializable;

public enum AccountType implements Serializable {
  CURRENT,
  SAVINGS,
  CHECKING,
  DEPOSIT;
}
