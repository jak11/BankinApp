package com.bank.application.exception;

public class QueryFormatException extends RuntimeException{
  public QueryFormatException() {
    super("query request param is expected in format language:<language_value> example for searching Go query=language:Go");
  }
}
