package com.bank.application.repository;

import java.util.List;

import com.bank.application.entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findByAccountId(@Param("account_id") Long account_id, Pageable pageable);
}
