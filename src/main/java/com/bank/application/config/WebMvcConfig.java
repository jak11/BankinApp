package com.bank.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Autowired
  private TokenHeaderInterceptor tokenHeaderInterceptor;

  //@Override
  public void addInterceptors(InterceptorRegistry registry){
    registry.addInterceptor(tokenHeaderInterceptor);//.addPathPatterns("/**");
  }
}
