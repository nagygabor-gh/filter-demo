package com.demo.filterdemo.filters;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class ContainerRequestFilter implements jakarta.ws.rs.container.ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		log.info("################# ContainerRequestFilter#filter #################");
	}

}
