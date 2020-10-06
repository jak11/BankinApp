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
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "addresses")
@JsonIgnoreProperties({"customer"})
public class Address implements Serializable {
  private @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  private String addressLine1;
  private String addressLine2;
  private String postcode;
  private String state;
  private String city;
  private Country country;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address)o;
    return Objects.equals(id, address.id) &&
        Objects.equals(addressLine1, address.addressLine1) &&
        Objects.equals(addressLine2, address.addressLine2) &&
        Objects.equals(postcode, address.postcode) &&
        Objects.equals(state, address.state) &&
        Objects.equals(city, address.city) &&
        country == address.country;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, addressLine1, addressLine2, postcode, state, city, country);
  }

  @Override
  public String toString() {
    return "Address{" +
        "id=" + id +
        ", addressLine1='" + addressLine1 + '\'' +
        ", addressLine2='" + addressLine2 + '\'' +
        ", postcode='" + postcode + '\'' +
        ", state='" + state + '\'' +
        ", city='" + city + '\'' +
        ", country=" + country +
        ", customer=" + customer +
        '}';
  }
}
