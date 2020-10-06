package com.bank.application.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class TokenHeaderInterceptor extends HandlerInterceptorAdapter {
  @Value("${api.valid_tokens}")
  private List<String> validApiTokens;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
    String lean_token = request.getHeader("lean-token");
    if(StringUtils.isEmpty(lean_token) && !validApiTokens.contains(lean_token)){
      response.getWriter().write("lean-token header is missing or invalid");
      response.setStatus(403);
      return false;
    }
    return true;
  }
}
