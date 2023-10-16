package com.demo.filterdemo;

import com.demo.filterdemo.filters.RequestResponseFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FilterDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilterDemoApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<RequestResponseFilter> requestResponseFilterFilterRegistrationBean(){
		FilterRegistrationBean<RequestResponseFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new RequestResponseFilter());
//		TODO you can use URL filtering
//		registrationBean.addUrlPatterns("/demo/ping");
//		TODO only used if other filters are set with order annotation
		registrationBean.setOrder(3);
		return registrationBean;
	}

}
