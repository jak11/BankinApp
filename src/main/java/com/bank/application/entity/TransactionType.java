package com.bank.application.entity;

import java.io.Serializable;

public enum  TransactionType implements Serializable {
  TRANSFER,
  REFUND,
  DIRECT_DEBIT,
  CARD_PAYMENT;
}
