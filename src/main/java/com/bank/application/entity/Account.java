package com.bank.application.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {
  private @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  private AccountType accountType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  private BigDecimal accountNumber;
  private String iban;
  private AccountStatus accountStatus;
  private Double balance;
  private Currency currency;

  public Account(AccountType accountType) {
    this.accountType = accountType;
  }

  public Account() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public BigDecimal getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(BigDecimal accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public AccountStatus getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(AccountStatus accountStatus) {
    this.accountStatus = accountStatus;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account)o;
    return Objects.equals(id, account.id) &&
        accountType == account.accountType &&
        Objects.equals(customer, account.customer) &&
        Objects.equals(accountNumber, account.accountNumber) &&
        Objects.equals(iban, account.iban);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, accountType, customer, accountNumber, iban);
  }

  @Override
  public String toString() {
    return "Account{" +
        "id=" + id +
        ", accountType=" + accountType +
        ", accountNumber=" + accountNumber +
        ", iban='" + iban + '\'' +
        ", accountStatus=" + accountStatus +
        ", balance=" + balance +
        ", currency=" + currency +
        '}';
  }
}
