package com.bank.application.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {
  private @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  private String name;
  private String email;
  private Citizenship citizenship;
  private Date dob;

  @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
      cascade = CascadeType.ALL)
  @JsonIgnoreProperties
  private Set<Address> addresses;

  public Customer() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Citizenship getCitizenship() {
    return citizenship;
  }

  public void setCitizenship(Citizenship citizenship) {
    this.citizenship = citizenship;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }


  public Set<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(Set<Address> addresses) {
    this.addresses = addresses;
  }

  public Customer(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Customer{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", citizenship=" + citizenship +
        ", dob=" + dob +
        ", addresses=" + addresses +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer)o;
    return Objects.equals(id, customer.id) &&
        Objects.equals(name, customer.name) &&
        Objects.equals(email, customer.email) &&
        citizenship == customer.citizenship &&
        Objects.equals(dob, customer.dob);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, citizenship, dob);
  }
}
