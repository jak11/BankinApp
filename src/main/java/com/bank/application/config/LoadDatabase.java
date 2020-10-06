package com.bank.application.config;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import com.bank.application.entity.Account;
import com.bank.application.entity.AccountStatus;
import com.bank.application.entity.AccountType;
import com.bank.application.entity.Address;
import com.bank.application.entity.Citizenship;
import com.bank.application.entity.Customer;
import com.bank.application.entity.Transaction;
import com.bank.application.entity.TransactionType;
import com.bank.application.repository.AccountRepository;
import com.bank.application.repository.AddressRepository;
import com.bank.application.repository.CustomerRepository;
import com.bank.application.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

@Configuration
class LoadDatabase {
  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private TransactionRepository transactionRepository;
  /*
  @Bean
  CommandLineRunner initDatabase() throws IOException, ParseException {
    File file = ResourceUtils.getFile("classpath:customers.csv");
    SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        System.out.println("values" + values[0]);

        Citizenship citizenship = Citizenship.valueOf(values[2]);

        Address address = new Address();
        address.setAddressLine1(values[4]);
        address.setPostcode(values[5]);
        address.setCity(values[6]);
        address.setCountry(citizenship.getCountry());
        addressRepository.save(address);

        Set<Address> addresses = new HashSet<>();
        addresses.add(address);
        Customer c = new Customer();
        c.setName(values[0]);
        c.setDob(formatter.parse(values[1]));
        c.setCitizenship(citizenship);
        c.setEmail(values[3]);
        c.setAddresses(addresses);

        customerRepository.save(c);
      }
    }



    *//*return args -> {
      log.info("Preloading " + repository.save(new Customer("Bilbo Baggins")));
      log.info("Preloading " + repository.save(new Customer("Frodo Baggins")));
    };*//*
  }*/

  @Bean
  public CommandLineRunner mappingDemo() {
    return args -> {
      Map<Long, Customer> customerMap = loadCustomers();
      Map<Long, Account> accountMap = loadAccounts(customerMap);
      loadTransactions(accountMap);
    };
  }

  private Map<Long, Customer> loadCustomers() throws IOException, ParseException {
    File file = ResourceUtils.getFile("classpath:customers.csv");
    SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
    Map<Long, Customer> customerMap = new HashMap<>();
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        for (int i = 0; i < values.length; i++) {
          values[i] = values[i].trim();
        }

        Citizenship citizenship = Citizenship.valueOf(values[2]);
        Customer customer = new Customer();
        customer.setName(values[0]);
        customer.setDob(formatter.parse(values[1]));
        customer.setCitizenship(citizenship);
        customer.setEmail(values[3]);
        //Customer savedCust = customerRepository.save(customer);
        Customer savedCust = customerRepository.save(customer);
        log.info("Loaded Customer {}", savedCust);
        customerMap.put(savedCust.getId(), savedCust);
        Address address = new Address();
        address.setAddressLine1(values[4]);
        address.setPostcode(values[5]);
        address.setCity(values[6]);
        address.setCountry(citizenship.getCountry());
        address.setCustomer(customer);
        //Address savedAddr = addressRepository.save(address);
        Address savedAddr = addressRepository.save(address);
        log.info("Loaded Address {}", savedAddr);
      }
    }
    return customerMap;
  }
  private void loadTransactions(Map<Long, Account> accountMap) throws IOException, ParseException {
    File file = ResourceUtils.getFile("classpath:transaction.csv");
    SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        for (int i = 0; i < values.length; i++) {
          values[i] = values[i].trim();
        }
        Transaction transaction = new Transaction();
        Account account = accountMap.get(Long.valueOf(values[1]));
        transaction.setAccount(account);
        transaction.setTransactionType(TransactionType.valueOf(values[2].toUpperCase()));
        transaction.setSummary(values[3]);
        transaction.setAmount(Double.valueOf(values[4]));
        transaction.setCurrency(Currency.getInstance(values[5]));
        transaction.setTimestamp(formatter.parse(values[6]));
        log.info("Loaded transaction {}", transactionRepository.save(transaction));
      }
    }
  }

  private Map<Long, Account> loadAccounts(Map<Long, Customer> customerMap) throws IOException{
    Map<Long, Account> accountMap = new HashMap<>();
    File file = ResourceUtils.getFile("classpath:account.csv");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        for (int i = 0; i < values.length; i++) {
          values[i] = values[i].trim();
        }
        Customer customer = customerMap.get(Long.valueOf(values[1]));
        Account account = new Account();
        account.setAccountType(AccountType.valueOf(values[2].toUpperCase()));
        account.setAccountNumber(new BigDecimal(values[3]));
        account.setIban(values[4]);
        account.setAccountStatus(AccountStatus.valueOf(values[5].toUpperCase()));
        account.setBalance(Double.valueOf(values[6]));
        account.setCurrency(Currency.getInstance(values[7]));
        account.setCustomer(customer);
        Account createdAcc = accountRepository.save(account);
        log.info("created account {}",createdAcc);
        accountMap.put(createdAcc.getId(), createdAcc);
      }
    }
    return accountMap;
  }
}
