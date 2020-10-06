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
import java.util.Currency;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {
  private @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", nullable = false)
  private Account account;

  private TransactionType transactionType;
  private Double amount;
  private Currency currency;
  private Date timestamp;
  private String summary;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction that = (Transaction)o;
    return Objects.equals(id, that.id) &&
        Objects.equals(account, that.account) &&
        transactionType == that.transactionType &&
        Objects.equals(amount, that.amount) &&
        Objects.equals(currency, that.currency) &&
        Objects.equals(timestamp, that.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, account, transactionType, amount, currency, timestamp);
  }

  @Override
  public String toString() {
    return "Transaction{" +
        "id=" + id +
        ", transactionType=" + transactionType +
        ", amount=" + amount +
        ", currency=" + currency +
        ", timestamp=" + timestamp +
        ", summary='" + summary + '\'' +
        '}';
  }
}
