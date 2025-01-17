package com.bank.application.entity;

public class PaginationInfo {
  private int page_number;
  private int page_size;

  public PaginationInfo() {
    page_number = 0;
    page_size = 10;
  }

  public int getPage_number() {
    return page_number;
  }

  public void setPage_number(int page_number) {
    this.page_number = page_number;
  }

  public int getPage_size() {
    return page_size;
  }

  public void setPage_size(int page_size) {
    this.page_size = page_size;
  }
}
