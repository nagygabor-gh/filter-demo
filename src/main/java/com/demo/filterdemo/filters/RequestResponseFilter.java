package com.demo.filterdemo.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestResponseFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("################# RequestResponseFilter#init #################");
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		log.info("################# RequestResponseFilter#doFilter #################");
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		log.info("Incoming request  {} : {}", req.getMethod(), req.getRequestURI());
		filterChain.doFilter(servletRequest, servletResponse);
		log.info("Outgoing response :{}", servletResponse.getContentType());
	}

	@Override
	public void destroy() {
		log.info("################# RequestResponseFilter#destroy #################");
		Filter.super.destroy();
	}

}
