package com.bank.application.entity;

import java.io.Serializable;

public enum Citizenship implements Serializable {
  BRITISH(Country.UNITED_KINGDOM),
  INDIAN(Country.INDIA),
  AMERICAN(Country.AMERICA),
  SAUDI(Country.SAUDI);
  private Country country;

  Citizenship(Country country) {
    this.country = country;
  }

  public Country getCountry() {
    return country;
  }
}
