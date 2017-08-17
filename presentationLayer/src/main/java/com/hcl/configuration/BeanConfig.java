package com.hcl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.hcl.service.AlertService;
import com.hcl.service.impl.AlertServiceImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.hcl")
public class BeanConfig {
	
	
	@Bean(name = "alertService")
	public AlertService getKitDetailService() {
		return new AlertServiceImpl();
	}
}